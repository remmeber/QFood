package com.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.bean.ShopDetailUriBean;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.impl.SwipeRefreshListener;
import com.rhg.outsourcing.mvp.presenter.ShopDetailPresenter;
import com.rhg.outsourcing.mvp.presenter.ShopDetailPresenterImpl;
import com.rhg.outsourcing.ui.FragmentController;
import com.rhg.outsourcing.widget.LoadingDialog;
import com.rhg.outsourcing.widget.VerticalTabLayout;

import java.util.List;

/**
 * desc:店铺详情的食物类型fm，里面{@link FoodTypeFragment}展示店铺中的商品
 * author：remember
 * time：2016/5/28 16:48
 * email：1013773046@qq.com
 */
public class ShopDetailFoodFragment extends SuperFragment {
    VerticalTabLayout verticalTabLayout;
    FragmentController fragmentController;
    Fragment[] fragments;

    List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList;
    ShopDetailPresenter shopDetailPresenter;
    LoadingDialog loadingDialog;

    String merchantId;

    public ShopDetailFoodFragment() {
        shopDetailPresenter = new ShopDetailPresenterImpl(this);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.shop_detail_fm1_content;
    }

    @Override
    protected void initView(View view) {
        verticalTabLayout = (VerticalTabLayout) view.findViewById(R.id.vt_selector);
    }


    @Override
    protected void initData() {
        verticalTabLayout.setTitles(AppConstants.SHOP_TITLES);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.KEY_MERCHANT_ID, merchantId);
        fragments = new Fragment[5];
        for (int i = 0; i < fragments.length; i++) {
            FoodTypeFragment fragment = new FoodTypeFragment();
            fragment.setArguments(bundle);
            fragments[i] = fragment;
        }
        fragmentController = new FragmentController(getChildFragmentManager(), fragments, R.id.fl_shop_detail);
        verticalTabLayout.setOnVerticalTabClickListener(new VerticalTabLayout.VerticalTabClickListener() {
            @Override
            public void onVerticalTabClick(int position) {
                fragmentController.showFragment(position);
            }
        });
    }


    @Override
    public void receiveData(Bundle arguments) {
        merchantId = arguments.getString(AppConstants.KEY_MERCHANT_ID);
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        shopDetailBeanList = (List<ShopDetailUriBean.ShopDetailBean>) o;
        /*((FoodTypeFragment) fragments[verticalTabLayout.getCurrentPosition()])
                .setShopDetailBeanList(shopDetailBeanList);*/
    }

}
