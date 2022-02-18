package com.unite.customemarquee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MarqueeTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int SPEED = 5000;
    private int scrollDistance = 0;
    private int initialPosition = 0;
    private int textWidth = 0;
    private ObjectAnimator firstAnimator;
    private ObjectAnimator animator;
    private boolean isRunAnimator = true;
    private String TAG = "jason";


    public MarqueeTextView(@NonNull Context context) {
        super(context);
    }

    public MarqueeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public MarqueeTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void init() {
        this.setHorizontalFadingEdgeEnabled(true);
        scrollDistance = (int) this.getPaint().measureText(this.getText().toString());

        initFirstAnimator();

        initAnimator();
    }


    //初始化第一个动画
    private void initFirstAnimator() {
        int starPosition = MarqueeTextView.this.getScrollX() - MarqueeTextView.this.getPaddingLeft();

        int endPosition = starPosition + scrollDistance;
        Log.i(TAG, "我是开始和结束 " + starPosition + "  " + endPosition);
        firstAnimator = ObjectAnimator.ofInt(this, "ScrollX", starPosition, endPosition);
        firstAnimator.setInterpolator(new LinearInterpolator());
        firstAnimator.setDuration(SPEED);
        firstAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                MarqueeTextView.this.setGravity(Gravity.CENTER);
                //MarqueeTextView.this.postInvalidate();
                MarqueeTextView.this.measure(MarqueeTextView.this.getMeasuredWidth(), MarqueeTextView.this.getMeasuredHeight());
            }
        });
        //本次动画结束 启动第二个动画
        firstAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.i(TAG, "onAnimationEnd: 开始");
                animator.start();
            }
        });
    }

    private void initAnimator() {

        int starPosition = -this.getWidth();
        int endPosition = scrollDistance;
        animator = ObjectAnimator.ofInt(MarqueeTextView.this, "ScrollX", starPosition, endPosition);
        animator.setDuration(SPEED);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate: " + (int) animation.getAnimatedValue());

                MarqueeTextView.this.setGravity(Gravity.BOTTOM);

            }
        });
    }

    // 开始
    public void statMarquee() {
        isRunAnimator = false;
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
                firstAnimator.start();
            }
        }, 300);

        this.setScrollX(getScrollX()-2);
//
//
//        this.invalidate();
        //Log.i(TAG, "x y " + this.getScrollX() + " " + this.getScrollY());

        //this.setScrollX(getScrollX() - 2);


     //   MarqueeTextView.this.measure(MarqueeTextView.this.getMeasuredWidth(), MarqueeTextView.this.getMeasuredHeight());
      //  Log.i(TAG, "x y " + this.getScrollX() + " " + this.getScrollY());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void stopMarquee() {
    }

    /**
     * 设置跑马灯的速度
     * 速度从1到20越来越慢
     *
     * @param speed
     */
    private void setSpeed(int speed) {
        if (speed <= 0) {
            speed = 1;
        }
        this.SPEED = speed * 1000;
    }
}
