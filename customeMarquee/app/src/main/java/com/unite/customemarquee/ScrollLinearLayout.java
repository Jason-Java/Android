package com.unite.customemarquee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import util.LogUtil;

public class ScrollLinearLayout extends LinearLayout {

    public ScrollLinearLayout(Context context) {
        super(context);
    }

    private float downX;
    private float downY;
    private float moveX;
    private float moveY;

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("我是按下");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("我是移动");
                int move=(int)(moveY-downY);
                scrollTo(0,-move);
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("我是抬起");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            LogUtil.i("我是拦截按下");
            downX = ev.getX();
            downY = ev.getY();
        }
        if (action == MotionEvent.ACTION_MOVE) {

            moveX = ev.getX();
            moveY = ev.getY();
            LogUtil.i("我是拦截移动 downX  moveX " +downX+" "+moveX+"  "+    (int) (moveX - downX));
            if (Math.abs(moveX - downX) > 5) {

                return true;
            }
        }
        if (action == MotionEvent.ACTION_UP) {
            LogUtil.i("我是拦截抬起");
        }
        return super.onInterceptTouchEvent(ev);
    }
}
