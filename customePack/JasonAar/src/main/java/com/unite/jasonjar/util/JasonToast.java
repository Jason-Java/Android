package com.unite.jasonjar.util;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.unite.jasonjar.R;
import com.unite.jasonjar.view.JasonTextView;



public class JasonToast {

    private  Toast toast;
    private Context context;
    private JasonToast() { }

    private  static class SingleJasonToast {
        private static JasonToast jasonToast = new JasonToast();
    }

    public static JasonToast getInstance() {
        return SingleJasonToast.jasonToast;
    }

    public void init(Context context) {
        this.context = context;
    }




    /**
     * 错误提示框

     * @param message
     * @return
     */
    public  void makeError(String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.jason_toast, null, false);
        JasonTextView textView = view.findViewById(R.id.message);
        textView.setText(message);
       // textView.setJaBackground(0XFFEB432E);

        if (toast != null) {
            toast.cancel();
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                toast = new Toast(context);
                toast.setView(view);
                toast.setGravity(Gravity.TOP, 0, 100);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    /**
     * 正确提示框
     *
     * @param message
     * @return
     */
    public  void makeSuccess( String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.jason_toast, null, false);
        JasonTextView textView = view.findViewById(R.id.message);
        textView.setText(message);
//        textView.setJaBackground(0XFF40AC5B);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


}
