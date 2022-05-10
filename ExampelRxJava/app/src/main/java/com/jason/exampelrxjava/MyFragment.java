package com.jason.exampelrxjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jason.exampelrxjava.R;

import util.LogUtil;

public class MyFragment extends Fragment {

    private View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("我是fragment的OnResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i("我是fragment的OnStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i("我是fragment的OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("我是fragment的OnDestroy");
    }
}
