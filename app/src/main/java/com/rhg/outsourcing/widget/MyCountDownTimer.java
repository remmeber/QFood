package com.rhg.outsourcing.widget;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * desc:倒计时
 * author：remember
 * time：2016/6/21 10:20
 * email：1013773046@qq.com
 */
public class MyCountDownTimer extends CountDownTimer {
    TextView countDownText;

    public MyCountDownTimer(long millisInFuture, long countDownInterval, View view) {
        super(millisInFuture, countDownInterval);
        if (view instanceof TextView) {
            countDownText = (TextView) view;
        }
        Log.i("RHG", "" + countDownText);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (countDownText != null) {
            String time = String.format("剩余%s", millisUntilFinished / 1000 + "s");
            countDownText.setText(time);
        }
    }

    @Override
    public void onFinish() {
        cancel();
        countDownText.setClickable(true);
        countDownText.setText("获取验证码");
    }
}
