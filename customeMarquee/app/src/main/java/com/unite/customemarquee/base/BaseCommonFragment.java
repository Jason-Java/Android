package com.unite.customemarquee.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public abstract class BaseCommonFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container, @Nullable   Bundle savedInstanceState)
    {
        return getLayoutView(inflater,container,savedInstanceState);
    }

    public abstract View getLayoutView(@NonNull  LayoutInflater inflater, @Nullable   ViewGroup container, @Nullable   Bundle savedInstanceState);

    protected <T extends View> T findViewById(@IdRes int viewId)
    {
        return getView().findViewById(viewId);
    }

    @Override
    public void onViewCreated(@NonNull   View view, @Nullable   Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        iniView();
        iniData();
        iniEvent();
    }

    protected void iniView()
    {

    }

    protected void iniData()
    {

    }

    protected void iniEvent()
    {

    }


}
