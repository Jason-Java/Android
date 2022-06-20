package com.unite.jasonjar.popup_window;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import util.DensityUtil;


public abstract class BasePopupWindow extends PopupWindow {

    protected Activity activity = null;
    private View parentView;
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


    /**
     * 以attachView 的左上角坐标为基准线,如果 attachView下面的空间可以放下当前popWindow,则在控件下面显示否则在上面
     * @param attachView
     */
    public void show(View attachView) {
        View contentView = this.getContentView();
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        int measureHeight = contentView.getMeasuredHeight();
        int measureWidth = contentView.getMeasuredWidth();

        int attachViewHeight = attachView.getMeasuredHeight();
        int attachViewWidth = attachView.getMeasuredWidth();

        Point screenPoint = DensityUtil.getDisplay(activity);

        int[] location = new int[2];
        attachView.getLocationInWindow(location);
        if (location[1] + attachViewHeight + measureHeight > screenPoint.y) {
            location[1] = location[1] - measureHeight;
        } else {
            location[1] = location[1] + attachViewHeight;
        }

        if (location[0] + measureWidth > screenPoint.x) {
            location[0] = screenPoint.x - measureWidth;
        }
        super.showAtLocation(getParentView(), Gravity.NO_GRAVITY, location[0], location[1]);
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
        if (this.parentView != null) {
            return this.parentView;
        }
        //寻找Activity的根布局
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        return viewGroup.getChildAt(0);
    }

    //设置父VIew
    public void setParentView(View parentView) {
        this.parentView = parentView;
    }


    public void onDestroy() {
        activity = null;
        parentView = null;
    }
}
