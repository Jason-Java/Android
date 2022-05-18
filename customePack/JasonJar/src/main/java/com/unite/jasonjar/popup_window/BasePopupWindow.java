package com.unite.jasonjar.popup_window;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


public abstract class BasePopupWindow extends PopupWindow {

    protected Activity activity = null;
    public BasePopupWindow(Activity activity) {
        super(activity);
        this.activity = activity;

        LayoutInflater inflater = LayoutInflater.from(activity);

        setContentView(getView(inflater));
        //设置页面的宽和高
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);

        //pupWindow全屏显示
        setClippingEnabled(false);

        //设置背景颜色
        ColorDrawable drawable = new ColorDrawable(0X80000000);
        setBackgroundDrawable(drawable);

        //获取焦点
        setFocusable(true);
        //设置可以点击
        setTouchable(true);
        setOnDismissListener();
    }

    public abstract View getView(LayoutInflater inflater);


    /**
     * 显示窗口 显示在屏幕的中间
     */
    public void showPopWindow() {
        this.showAtLocation(getParentView(), Gravity.CENTER, 0, 0);
    }

    /**
     * 显示窗口
     *
     * @param offsetX X方向相对屏幕的偏移量
     * @param offsetY Y方向相对屏幕的偏移量
     */
    public void showPopWindow(int offsetX, int offsetY) {
        this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY);
    }


    private void setOnDismissListener() {

      this.setOnDismissListener(new OnDismissListener() {
          @Override
          public void onDismiss() {
              onDestroy();
          }
      });
    }

    //寻找Activity的根布局
    public View getParentView() {
        //寻找Activity的根布局
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        return viewGroup.getChildAt(0);
    }


    public void onDestroy() {
        activity = null;
    }
}
