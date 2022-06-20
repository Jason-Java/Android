package com.unite.customepack;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.customepack.databinding.DialogWaitBinding;
import com.unite.jasonjar.fragment.BaseDialog;


public class WaitDialog extends BaseDialog {

    private DialogWaitBinding mBinding;
    private String message;


    @Override
    protected View getLayoutView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        mBinding = DialogWaitBinding.inflate(layoutInflater, viewGroup, false);
        return mBinding.getRoot();
    }

    @Override
    protected void iniData() {
        mBinding.waitingView.start();
        mBinding.tvMesaage.setText(message);
    }

    public void setMessage(String mag) {
        message = mag;
    }

    @Override
    protected void iniEvent() {
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        mBinding.waitingView.stop();
        super.onDestroy();
        mBinding.getRoot().removeAllViews();
    }
}
