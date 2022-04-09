package com.unite.customepack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unite.customepack.databinding.PopupWaitBinding;
import com.unite.jasonjar.popup_window.BasePopupWindow;
import com.unite.jasonjar.view.WaitingView;

public class WaitPopup extends BasePopupWindow {

    private PopupWaitBinding mBinding ;
    private WaitingView waitingView ;

    public WaitPopup(Activity activity) {
        super(activity);
    }

    @Override
    public View getView(LayoutInflater inflater) {
        mBinding = PopupWaitBinding.inflate(inflater, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void showPopWindow() {
        super.showPopWindow();
        mBinding.waitingView.start();
    }

    @Override
    public void initData() {


    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        waitingView.stop();
        activity=null;
        waitingView=null;
    }
}
