package com.unite.customdrawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MarqueeTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int SPEED=5000;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void init() {
        this.setHorizontalFadingEdgeEnabled(true);
        scrollDistance=(int)this.getPaint().measureText(this.getText().toString());

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
        firstAnimator.setRepeatMode(ObjectAnimator.RESTART);
        firstAnimator.setRepeatCount(10);
        firstAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                MarqueeTextView.this.measure(MarqueeTextView.this.getWidth(), MarqueeTextView.this.getHeight());
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
        int[] location = new int[2];
        this.getLocationInWindow(location);
        int starPosition = (location[0]) + this.getWidth();
        int endPosition = starPosition - scrollDistance;
        animator=ObjectAnimator.ofInt(MarqueeTextView.this,"ScrollX",MarqueeTextView.this.getScrollX(),MarqueeTextView.this.getScrollX()-100);
        animator.setDuration(SPEED);
//        animator.setRepeatMode(ObjectAnimator.RESTART);
//        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate: " + (int) animation.getAnimatedValue());
                MarqueeTextView.this.measure(MarqueeTextView.this.getWidth(), MarqueeTextView.this.getHeight());
            }
        });
    }

    // 开始
    public void statMarquee()
    {
        isRunAnimator = false;
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
                animator.start();

            }
        }, 300);
    }

    public void stopMarquee() {

    }

    /**
     * 设置跑马灯的速度
     * 速度从1到20越来越慢
     * @param speed
     */
    private void setSpeed(int speed) {
        if (speed <= 0) {
            speed = 1;
        }
        this.SPEED = speed * 1000;
    }

}
