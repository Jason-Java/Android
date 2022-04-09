package com.unite.jasonjar.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class SuccessView extends View {

    /**
     * 打钩
     */
    private ValueAnimator mTickAnimation;

    private RectF mRectF = new RectF();

    private Paint tickPaint = new Paint();
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
     * 测量打钩
     */
    private PathMeasure tickPathMeasure;

    /**
     * 打钩百分比
     *
     * @param context
     */
    float tickPrecent = 0;


    public SuccessView(Context context) {
        super(context);
        init();
    }

    public SuccessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 4 - strokeWidth;
        mRectF.set(new RectF(radius + strokeWidth, radius + strokeWidth, 3 * radius + strokeWidth, 3 * radius + strokeWidth));

        //初始化打钩路径
        Path tickPath = new Path();
        tickPath.moveTo(1.5f * radius + strokeWidth, 2 * radius + strokeWidth);
        tickPath.lineTo(1.5f * radius + 0.3f * radius + strokeWidth, 2 * radius + 0.3f * radius + strokeWidth);
        tickPath.lineTo(2 * radius + 0.5f * radius + strokeWidth, 2 * radius - 0.3f * radius + strokeWidth);
        tickPathMeasure = new PathMeasure(tickPath, false);
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

        tickPaint.setColor(Color.argb(255, 0, 150, 136));
        tickPaint.setAntiAlias(true);
        tickPaint.setStrokeWidth(strokeWidth);
        tickPaint.setStyle(Paint.Style.STROKE);

        //打钩动画
        mTickAnimation = ValueAnimator.ofFloat(0f, 1f);
        mTickAnimation.setStartDelay(1000);
        mTickAnimation.setDuration(500);
        mTickAnimation.setInterpolator(new AccelerateInterpolator());
        mTickAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tickPrecent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (status) {
            case 0:
                drawTick(canvas);
                break;
        }
    }


    /**
     * 绘制打钩
     *
     * @param canvas
     */
    private void drawTick(Canvas canvas) {
        Path path = new Path();
        /*
         * On KITKAT and earlier releases, the resulting path may not display on a hardware-accelerated Canvas.
         * A simple workaround is to add a single operation to this path, such as dst.rLineTo(0, 0).
         */
        tickPathMeasure.getSegment(0, tickPrecent * tickPathMeasure.getLength(), path, true);
        path.rLineTo(0, 0);
        canvas.drawPath(path, tickPaint);
        canvas.drawArc(mRectF, 0, 360, false, tickPaint);
    }


    /**
     * 开始完成动画
     */
    public void start() {
        status = 0;
        post(new Runnable() {
            @Override
            public void run() {
                mTickAnimation.start();
            }
        });
    }

}
