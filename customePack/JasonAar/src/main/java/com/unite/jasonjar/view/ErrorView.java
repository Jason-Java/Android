package com.unite.jasonjar.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * 错误提示框
 */
public class ErrorView extends View {

    /**
     * 绘制感叹号
     */
    private ValueAnimator mshockAnimation;
    /**
     * 绘制感叹号震动
     */
    private ValueAnimator mCommaAnimation;

    private RectF mRectF = new RectF();
    private Paint commaPaint = new Paint();

    /**
     * 画笔宽度
     */
    private int strokeWidth = 20;
    /**
     *
     */
    private float radius = 0;
    //0画圆,1抛出方块,2下落变粗,挤压圆形,3,绘制三叉，圆形恢复,4,绿色勾,5,红色感叹号出现，6,红色感叹号震动
    private int status = 0;

    /**
     * 感叹号
     */
    private PathMeasure commaPathMeasure1;
    private PathMeasure commaPathMeasure2;

    /**
     * 感叹号百分比
     *
     * @param context
     */
    float commaPrecent = 0;
    /**
     * 震动百分比
     *
     * @param context
     */
    int shockPrecent = 0;


    /**
     * 震动角度
     */
    private int shockDir = 10;

    public ErrorView(Context context) {
        super(context);
        init();
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 4 - strokeWidth;
        mRectF.set(new RectF(radius + strokeWidth, radius + strokeWidth, 3 * radius + strokeWidth, 3 * radius + strokeWidth));

        //感叹号路径
        Path commaPath1 = new Path();
        Path commaPath2 = new Path();
        commaPath1.moveTo(2f * radius + strokeWidth, 1.25f * radius + strokeWidth);
        commaPath1.lineTo(2f * radius + strokeWidth, 2.25f * radius + strokeWidth);
        commaPath2.moveTo(2f * radius + strokeWidth, 2.75f * radius + strokeWidth);
        commaPath2.lineTo(2f * radius + strokeWidth, 2.5f * radius + strokeWidth);
        commaPathMeasure1 = new PathMeasure(commaPath1, false);
        commaPathMeasure2 = new PathMeasure(commaPath2, false);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSpecSize + 10 * strokeWidth, heightSpecSize + 10 * strokeWidth);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void init() {
        commaPaint.setAntiAlias(true);
        commaPaint.setColor(Color.argb(255, 229, 57, 53));
        commaPaint.setStrokeWidth(strokeWidth);
        commaPaint.setStyle(Paint.Style.STROKE);

        //感叹号动画
        mCommaAnimation = ValueAnimator.ofFloat(0f, 1f);
        mCommaAnimation.setDuration(300);
        mCommaAnimation.setInterpolator(new AccelerateInterpolator());
        mCommaAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                commaPrecent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        mCommaAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                status = 1;
                mshockAnimation.start();
            }
        });

        //震动动画
        mshockAnimation = ValueAnimator.ofInt(-1, 0, 1, 0, -1, 0, 1, 0);
        mshockAnimation.setDuration(500);

        mshockAnimation.setInterpolator(new LinearInterpolator());
        mshockAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                shockPrecent = (int) animation.getAnimatedValue();
                invalidate();
            }

        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (status) {
            case 0:
                drawComma(canvas);
                break;
            case 1:
                drawShockComma(canvas);
                break;
        }
    }

    /**
     * 绘制感叹号
     */
    private void drawComma(Canvas canvas) {
        Path path1 = new Path();
        commaPathMeasure1.getSegment(0, commaPrecent * commaPathMeasure1.getLength(), path1, true);
        path1.rLineTo(0, 0);
        Path path2 = new Path();
        commaPathMeasure2.getSegment(0, commaPrecent * commaPathMeasure2.getLength(), path2, true);
        path2.rLineTo(0, 0);
        canvas.drawPath(path1, commaPaint);
        canvas.drawPath(path2, commaPaint);
        canvas.drawArc(mRectF, 0, 360, false, commaPaint);
    }

    /**
     * 绘制震动效果
     *
     * @param canvas
     */
    private void drawShockComma(Canvas canvas) {
        Path path1 = new Path();
        commaPathMeasure1.getSegment(0, commaPathMeasure1.getLength(), path1, true);
        path1.rLineTo(0, 0);
        Path path2 = new Path();
        commaPathMeasure2.getSegment(0, commaPathMeasure2.getLength(), path2, true);
        path2.rLineTo(0, 0);

        if (shockPrecent != 0) {
            canvas.save();
            if (shockPrecent == 1)
                canvas.rotate(shockDir, 2 * radius, 2 * radius);
            else if (shockPrecent == -1)
                canvas.rotate(-shockDir, 2 * radius, 2 * radius);
        }
        canvas.drawPath(path1, commaPaint);
        canvas.drawPath(path2, commaPaint);
        canvas.drawArc(mRectF, 0, 360, false, commaPaint);
        if (shockPrecent != 0) {
            canvas.restore();
        }
    }


    /**
     * 开始完成动画
     */
    public void start() {
        status = 0;
        post(new Runnable() {
            @Override
            public void run() {
                mCommaAnimation.start();
            }
        });
    }

}
