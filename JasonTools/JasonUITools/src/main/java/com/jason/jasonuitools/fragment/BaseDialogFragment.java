package com.jason.jasonuitools.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年10月11日
 */
public abstract class BaseDialogFragment extends DialogFragment {
    public View view;
    private CountDownTimer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = getLayoutView(inflater, container, savedInstanceState);
        return view;
    }

    protected abstract View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniData();
        iniEvent();
    }


    protected void iniData() {

    }

    protected void iniEvent() {

    }

    public void startTimer(long time) {
        cancelTimer();
        if (timer == null) {
            timer = new CountDownTimer(time, 1000) {
                long downTimer;

                @Override
                public void onTick(long millisUntilFinished) {
                    downTimer = (millisUntilFinished / 1000);
                    downTimer(downTimer);
                }

                @Override
                public void onFinish() {
                    finishTimer();
                }
            };
        }
    }

    //开始倒计时
    public void startTimer() {
        startTimer(1000 * 60);
    }


    //取消倒计时
    public void cancelTimer() {
        if (timer != null)
            timer.cancel();
    }

    //结束倒计时
    public void finishTimer() {

    }

    public void downTimer(long downTimer) {

    }

    @Override
    public void onDestroy() {
        cancelTimer();
        super.onDestroy();
    }
}

