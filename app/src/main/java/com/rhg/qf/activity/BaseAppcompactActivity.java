package com.rhg.qf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.rhg.qf.application.InitApplication;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.locationservice.LocationService;
import com.rhg.qf.locationservice.MyLocationListener;
import com.rhg.qf.mvp.view.BaseView;
import com.rhg.qf.utils.KeyBoardUtil;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/*
 *desc
 *author rhg
 *time 2016/7/7 10:36
 *email 1013773046@qq.com
 */
public abstract class BaseAppcompactActivity extends AppCompatActivity implements BaseView {


    boolean isFirstLoc;
    //TODO 百度地图
    private LocationService locationService;
    private MyLocationListener mLocationListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initData();
    }

    protected abstract void initData();

    protected abstract int getLayoutResId();

    public void loadingData() {

    }

    protected boolean isNeedFirstLoc() {
        return false;
    }

    private void startLoc() {
        if ((locationService = GetMapService()) != null) {
            if ((mLocationListener = getLocationListener()) != null) {
                locationService.registerListener(mLocationListener);
                locationService.setLocationOption(locationService.getDefaultLocationClientOption());
                mLocationListener.getLocation(locationService);
            }
        }
    }

    public void reStartLocation() {
        if (isFirstLoc) {
            startLoc();
            isFirstLoc = false;
            return;
        }
        if (locationService == null)
            locationService = GetMapService();
        if (mLocationListener == null) {
            Log.d("RHG", "Location listener is null");
            locationService.registerListener(mLocationListener = getLocationListener());
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else {
            mLocationListener.getLocation(locationService);
        }
        if (AppConstants.DEBUG)
            Log.i("RHG", "重启定位");
    }

    public MyLocationListener getLocationListener() {
        return null;
    }

    /*public void getLocation(LocationService locationService, MyLocationListener mLocationListener) {
    }*/


    /*默认不定位，如果需要定位，子类需要重写该方法*/
    public LocationService GetMapService() {
        return null;
    }

    public void dataReceive(Intent intent) {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View focusView = getCurrentFocus();
            if (isShouldHideKeyBoard(focusView, ev)) {
                KeyBoardUtil.closeKeybord((EditText) focusView, this);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyBoard(View focusView, MotionEvent ev) {
        if (focusView != null && focusView instanceof EditText) {
            int[] p = {0, 0};
            focusView.getLocationInWindow(p);
            int left = p[0];
            int top = p[1];
            int bottom = top + focusView.getHeight();
            int right = left + focusView.getWidth();
            return ev.getX() < left || ev.getX() > right || ev.getY() < top || ev.getY() > bottom;
        } else return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus() && this.getCurrentFocus() instanceof EditText) {

            return KeyBoardUtil.closeKeybord((EditText) this.getCurrentFocus(), this);
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this);
        RefWatcher refWatcher = InitApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showData(Object o) {
        if (o instanceof String) {
            String _str = (String) o;
            if (_str.contains("location")) {
                String[] location_str = _str.split(",");
                if (location_str[1].equals("error"))
                    showLocFailed(location_str[2]);
                else {
                    if (locationService != null) {
                        locationService.stop();
                    }
                    showLocSuccess(location_str[2]);
                }
            } else {
                showSuccess(_str);
            }
            return;
        }
        showSuccess(o);

    }

    public void showLocSuccess(String s) {
    }

    public void showLocFailed(String s) {
    }

    protected abstract void showSuccess(Object s);

    protected abstract void showError(Object s);

}
