package com.unite.jasonjar.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.unite.jasonjar.R;
import com.unite.jasonjar.view.JasonTextView;

import java.time.Duration;

public class JasonToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public JasonToast(Context context) {
        super(context);
    }


    /**
     * 错误提示框
     * @param context
     * @param message
     * @return
     */
    public static JasonToast makeError(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.jason_toast, null, false);
        JasonTextView textView=view.findViewById(R.id.message);
        textView.setJaText(message);
        textView.setJaBackground(0XFFEB432E);
        JasonToast jasonToast = new JasonToast(context);
        jasonToast.setView(view);
        jasonToast.setGravity(Gravity.TOP, 0, 100);
        jasonToast.setDuration(Toast.LENGTH_LONG);
        return jasonToast;
    }

    /**
     * 正确提示框
     * @param context
     * @param message
     * @return
     */
    public static JasonToast makeSuccess(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.jason_toast, null, false);
        JasonTextView textView=view.findViewById(R.id.message);
        textView.setJaText(message);
        textView.setJaBackground(0XFF40AC5B);
        JasonToast jasonToast = new JasonToast(context);
        jasonToast.setView(view);
        jasonToast.setGravity(Gravity.TOP, 0, 100);
        jasonToast.setDuration(Toast.LENGTH_LONG);
        return jasonToast;
    }

}
