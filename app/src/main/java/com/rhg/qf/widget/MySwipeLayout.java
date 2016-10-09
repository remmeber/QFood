package com.rhg.qf.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * desc: 暂时未使用
 * author：remember
 * time：2016/5/28 17:03
 * email：1013773046@qq.com
 */
public class MySwipeLayout extends SwipeRefreshLayout {
    int lastX;
    int lastY;
    boolean isHorizontalSwipe;


    public MySwipeLayout(Context context) {
        super(context);
    }

    public MySwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int currentX = (int) e.getRawX();
        int currentY = (int) e.getRawY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isHorizontalSwipe = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(currentY - lastY) <= 1) {
                    if (Math.abs(currentX - lastX) > 2)
                        isHorizontalSwipe = true;
                    break;
                }
                if (Math.abs(currentX - lastX) / Math.abs(currentY - lastY) > 2) {
                    isHorizontalSwipe = true;
                } else {
                    isHorizontalSwipe = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        lastX = currentX;
        lastY = currentY;
        super.onInterceptTouchEvent(e);
        return isHorizontalSwipe;
    }
}
