package com.unite.jasonjar.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return getLayoutView(inflater, container, savedInstanceState);
    }

    public abstract View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected <T extends View> T findViewById(@IdRes int viewId) {
        return getView().findViewById(viewId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniData();
        iniEvent();
    }


    protected abstract void iniData();

    protected abstract void iniEvent();

    protected FragmentActivity getMyActivity() {
        return getActivity();
    }

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
}
