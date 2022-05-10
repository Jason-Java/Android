package com.jason.exampelrxjava;

import android.app.ActivityManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import util.LogUtil;

public class MyRelativeLayout extends RelativeLayout {
    float oldX = 0, oldY = 0, newX = 0, newY = 0;
    private OnMoverViewListener onMoverViewListener;



    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                LogUtil.i("我是按下事件 旧X " + oldX + " 旧Y " + oldY + " 新X " + newX + " 新Y " + newY);
                break;
            case MotionEvent.ACTION_MOVE:
                if (onMoverViewListener != null) {
                    onMoverViewListener.onMoverView(oldX, oldY, newX, newY);
                }
                newX = event.getX();
                newY = event.getY();
                LogUtil.i("我是移动事件 旧X " + oldX + " 旧Y " + oldY + " 新X " + newX + " 新Y " + newY);

                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("我是抬起事件 旧X " + oldX + " 旧Y " + oldY + " 新X " + newX + " 新Y " + newY);
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

    public void setOnMoverViewListener(OnMoverViewListener listener) {
        this.onMoverViewListener = listener;
    }


    public interface OnMoverViewListener {
        void onMoverView(float oldX, float oldY, float newX, float newY);
    }


}
