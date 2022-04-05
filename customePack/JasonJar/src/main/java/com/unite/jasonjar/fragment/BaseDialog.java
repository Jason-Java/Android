package com.unite.jasonjar.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;


public abstract class BaseDialog extends DialogFragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutView(inflater, container, savedInstanceState);
    }

    protected abstract View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniData();
        iniEvent();
    }


    protected abstract void iniData();

    protected abstract void iniEvent();


    private CountDownTimer timer = new CountDownTimer(1000 * 60, 1000) {
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

    //开始倒计时
    public void startTimer() {
        timer.start();
    }

    //取消倒计时
    public void cancelTimer() {
        timer.cancel();
    }

    //结束倒计时
    public void finishTimer() {

    }

    public void downTimer(long downTimer) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    protected FragmentActivity getMyActivity() {
        return getActivity();
    }
}
