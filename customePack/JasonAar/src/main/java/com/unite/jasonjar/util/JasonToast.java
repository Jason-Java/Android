package com.unite.jasonjar.util;


import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.unite.jasonjar.R;
import com.unite.jasonjar.view.JasonButton;
import com.unite.jasonjar.view.JasonTextView;


public class JasonToast {

    private Toast toast;
    private Application context;

    private JasonToast() {
    }

    private static class SingleJasonToast {
        private static JasonToast jasonToast = new JasonToast();
    }

    public static JasonToast getInstance() {
        return SingleJasonToast.jasonToast;
    }

    public void init(Application context) {
        this.context = context;
    }


    /**
     * 主线程toast提示框
     *
     * @param message
     * @return
     */
    public void makeError(String message) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                View view = LayoutInflater.from(context).inflate(R.layout.jason_toast, null, false);
                JasonButton textView = view.findViewById(R.id.message);
                textView.jasonView.setJaText(message);
                textView.jasonView.setJaBackground(0XFFEB432E);
                if (toast != null) {
                    toast.cancel();
                }
                JasonToast.this.toast = new Toast(context);
                JasonToast.this.toast.setView(view);
                JasonToast.this.toast.setGravity(Gravity.TOP, 0, 100);
                JasonToast.this.toast.setDuration(Toast.LENGTH_LONG);
                JasonToast.this.toast.show();
            }
        });

    }


    /**
     * 主线程 正确提示框
     *
     * @param message
     * @return
     */
    public void makeSuccess(String message) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                View view = LayoutInflater.from(context).inflate(R.layout.jason_toast, null, false);
                JasonButton textView = view.findViewById(R.id.message);
                textView.jasonView.setJaText(message);
                textView.jasonView.setJaBackground(0XFF40AC5B);
                if (toast != null) {
                    toast.cancel();
                }

                JasonToast.this.toast = new Toast(context);
                JasonToast.this.toast.setView(view);
                JasonToast.this.toast.setGravity(Gravity.TOP, 0, 100);
                JasonToast.this.toast.setDuration(Toast.LENGTH_LONG);
                JasonToast.this.toast.show();
            }
        });
        return;

    }


}
