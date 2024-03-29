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

import com.unite.jasonjar.util.LogUtil;

/**
 * 等待对话框
 */
public class WaitingView extends View {
    private volatile boolean flag = false;

    /**
     * 当前进度
     */
    private int progress = 0;
    /**
     * 最大进度
     */
    private static final int maxProgress = 100;
    /**
     * 小方块抛出动画
     */
    private ValueAnimator mRotateAnimation;
    /**
     * 小方块下落
     */
    private ValueAnimator mDownAnimation;
    /**
     * 小方块下落
     */
    private ValueAnimator mForkAnimation;


    private int mWidth, mHeight;
    private RectF mRectF = new RectF();
    private Paint circlePaint = new Paint();
    private Paint smallRectPaint = new Paint();
    private Paint downRectPaint = new Paint();

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
     * 测量下落路径
     */
    private PathMeasure downPathMeasure1;
    private PathMeasure downPathMeasure2;
    /**
     * 测量分叉
     */
    private PathMeasure forkPathMeasure1;
    private PathMeasure forkPathMeasure2;
    private PathMeasure forkPathMeasure3;


    /**
     * 下落百分比
     *
     * @param context
     */
    float downPrecent = 0;
    /**
     * 分叉百分比
     *
     * @param context
     */
    float forkPrecent = 0;


    /**
     * 是否loading成功
     */
    public boolean isSuccess = false;
    /**
     * 起始角度
     */
    private static final float startAngle = -90;
    /**
     * 扫过角度
     */
    private float curSweepAngle = 0;
    /**
     * 震动角度
     */
    private int shockDir = 10;

    public WaitingView(Context context) {
        super(context);
        init();
    }

    public WaitingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaitingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 4 - strokeWidth;
        mRectF.set(new RectF(radius + strokeWidth, radius + strokeWidth, 3 * radius + strokeWidth, 3 * radius + strokeWidth));
        //初始化下落路径
        Path downPath1 = new Path();
        downPath1.moveTo(2 * radius + strokeWidth, strokeWidth);
        downPath1.lineTo(2 * radius + strokeWidth, radius + strokeWidth);
        Path downPath2 = new Path();
        downPath2.moveTo(2 * radius + strokeWidth, strokeWidth);
        downPath2.lineTo(2 * radius + strokeWidth, 2 * radius + strokeWidth);
        downPathMeasure1 = new PathMeasure(downPath1, false);
        downPathMeasure2 = new PathMeasure(downPath2, false);
        //初始化分叉路径
        Path forkpath1 = new Path();
        forkpath1.moveTo(2 * radius + strokeWidth, 2 * radius + strokeWidth);
        forkpath1.lineTo(2 * radius + strokeWidth, 3 * radius + strokeWidth);
        float sin60 = (float) Math.sin(Math.PI / 3);
        float cos60 = (float) Math.cos(Math.PI / 3);
        Path forkpath2 = new Path();
        forkpath2.moveTo(2 * radius + strokeWidth, 2 * radius + strokeWidth);
        forkpath2.lineTo(2 * radius - radius * sin60 + strokeWidth, 2 * radius + radius * cos60 + strokeWidth);
        Path forkpath3 = new Path();
        forkpath3.moveTo(2 * radius + strokeWidth, 2 * radius + strokeWidth);
        forkpath3.lineTo(2 * radius + radius * sin60 + strokeWidth, 2 * radius + radius * cos60 + strokeWidth);
        forkPathMeasure1 = new PathMeasure(forkpath1, false);
        forkPathMeasure2 = new PathMeasure(forkpath2, false);
        forkPathMeasure3 = new PathMeasure(forkpath3, false);


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

    private float endAngle;

    private void init() {
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.argb(255, 48, 63, 159));
        circlePaint.setStrokeWidth(strokeWidth);
        circlePaint.setStyle(Paint.Style.STROKE);

        smallRectPaint.setAntiAlias(true);
        smallRectPaint.setColor(Color.argb(255, 48, 63, 159));
        smallRectPaint.setStrokeWidth(strokeWidth / 2);
        smallRectPaint.setStyle(Paint.Style.STROKE);

        downRectPaint.setAntiAlias(true);
        downRectPaint.setColor(Color.argb(255, 48, 63, 159));
        downRectPaint.setStrokeWidth(strokeWidth);
        downRectPaint.setStyle(Paint.Style.FILL);


        //抛出动画
        endAngle = (float) Math.atan(4f / 3);
        mRotateAnimation = ValueAnimator.ofFloat(0f, endAngle * 0.9f);
        mRotateAnimation.setDuration(500);
        mRotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mRotateAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curSweepAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        mRotateAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                curSweepAngle = 0;
                if (isSuccess) {
                    status = 2;
                    mDownAnimation.start();
                }
            }
        });

        //下落动画
        mDownAnimation = ValueAnimator.ofFloat(0f, 1f);
        mDownAnimation.setDuration(500);
        mDownAnimation.setInterpolator(new AccelerateInterpolator());
        mDownAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                downPrecent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        mDownAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                status = 3;
                mForkAnimation.start();
            }
        });

        //分叉动画
        mForkAnimation = ValueAnimator.ofFloat(0f, 1f);
        mForkAnimation.setDuration(100);
        mForkAnimation.setInterpolator(new LinearInterpolator());
        mForkAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                forkPrecent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        mForkAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });


    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (status) {
            case 0:
                float precent = 1.0f * progress / maxProgress;
                canvas.drawArc(mRectF, startAngle - 270 * precent, -(60 + precent * 300), false, circlePaint);
                break;
            case 1:
                drawSmallRectFly(canvas);
                break;
            case 2:
                drawRectDown(canvas);
                break;
            case 3:
                drawFork(canvas);
                break;
        }
    }

    /**
     * 抛出小方块
     *
     * @param canvas
     */
    private void drawSmallRectFly(Canvas canvas) {
        canvas.save();
        canvas.translate(radius / 2 + strokeWidth, 2 * radius + strokeWidth);//将坐标移动到大圆圆心
        float bigRadius = 5 * radius / 2;//大圆半径
        float x1 = (float) (bigRadius * Math.cos(curSweepAngle));
        float y1 = -(float) (bigRadius * Math.sin(curSweepAngle));
        float x2 = (float) (bigRadius * Math.cos(curSweepAngle + 0.05 * endAngle + 0.1 * endAngle * (1 - curSweepAngle / 0.9 * endAngle)));//
        float y2 = -(float) (bigRadius * Math.sin(curSweepAngle + 0.05 * endAngle + 0.1 * endAngle * (1 - curSweepAngle / 0.9 * endAngle)));
        canvas.drawLine(x1, y1, x2, y2, smallRectPaint);
        canvas.restore();
        circlePaint.setColor(Color.argb(255, 48, 63, 159));
        canvas.drawArc(mRectF, 0, 360, false, circlePaint);
    }

    /**
     * 绘制下落过程
     *
     * @param canvas
     */
    private void drawRectDown(Canvas canvas) {
        //下落方块的起始端坐标
        float pos1[] = new float[2];
        float tan1[] = new float[2];
        downPathMeasure1.getPosTan(downPrecent * downPathMeasure1.getLength(), pos1, tan1);
        //下落方块的末端坐标
        float pos2[] = new float[2];
        float tan2[] = new float[2];
        downPathMeasure2.getPosTan(downPrecent * downPathMeasure2.getLength(), pos2, tan2);
        //椭圆形区域
        Rect mRect = new Rect(Math.round(mRectF.left), Math.round(mRectF.top + mRectF.height() * 0.1f * downPrecent),
                Math.round(mRectF.right), Math.round(mRectF.bottom - mRectF.height() * 0.1f * downPrecent));

        //非交集
        Region region1 = new Region(Math.round(pos1[0]) - strokeWidth / 4, Math.round(pos1[1]), Math.round(pos2[0] + strokeWidth / 4), Math.round(pos2[1]));
        region1.op(mRect, Region.Op.DIFFERENCE);
        drawRegion(canvas, region1, downRectPaint);

        //交集
        Region region2 = new Region(Math.round(pos1[0]) - strokeWidth / 2, Math.round(pos1[1]), Math.round(pos2[0] + strokeWidth / 2), Math.round(pos2[1]));
        boolean isINTERSECT = region2.op(mRect, Region.Op.INTERSECT);
        drawRegion(canvas, region2, downRectPaint);

        //椭圆形区域
        if (isINTERSECT) {//如果有交集
            float extrusionPrecent = (pos2[1] - radius) / radius;
            RectF rectF = new RectF(mRectF.left, mRectF.top + mRectF.height() * 0.1f * extrusionPrecent, mRectF.right, mRectF.bottom - mRectF.height() * 0.1f * extrusionPrecent);
            canvas.drawArc(rectF, 0, 360, false, circlePaint);
        } else {
            canvas.drawArc(mRectF, 0, 360, false, circlePaint);
        }
    }

    /**
     * 绘制分叉
     *
     * @param canvas
     */
    private void drawFork(Canvas canvas) {
        float pos1[] = new float[2];
        float tan1[] = new float[2];
        forkPathMeasure1.getPosTan(forkPrecent * forkPathMeasure1.getLength(), pos1, tan1);
        float pos2[] = new float[2];
        float tan2[] = new float[2];
        forkPathMeasure2.getPosTan(forkPrecent * forkPathMeasure2.getLength(), pos2, tan2);
        float pos3[] = new float[2];
        float tan3[] = new float[2];
        forkPathMeasure3.getPosTan(forkPrecent * forkPathMeasure3.getLength(), pos3, tan3);

        canvas.drawLine(2 * radius + strokeWidth, radius + strokeWidth, 2 * radius + strokeWidth, 2 * radius + strokeWidth, downRectPaint);
        canvas.drawLine(2 * radius + strokeWidth, 2 * radius + strokeWidth, pos1[0], pos1[1], downRectPaint);
        canvas.drawLine(2 * radius + strokeWidth, 2 * radius + strokeWidth, pos2[0], pos2[1], downRectPaint);
        canvas.drawLine(2 * radius + strokeWidth, 2 * radius + strokeWidth, pos3[0], pos3[1], downRectPaint);
        //椭圆形区域
        RectF rectF = new RectF(mRectF.left, mRectF.top + mRectF.height() * 0.1f * (1 - forkPrecent),
                mRectF.right, mRectF.bottom - mRectF.height() * 0.1f * (1 - forkPrecent));
        canvas.drawArc(rectF, 0, 360, false, circlePaint);
    }


    /**
     * 绘制区域
     *
     * @param canvas
     * @param rgn
     * @param paint
     */
    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect r = new Rect();
        while (iter.next(r)) {
            canvas.drawRect(r, paint);
        }
    }

    /**
     * 开始完成动画
     */
    private void myPost() {
        post(new Runnable() {
            @Override
            public void run() {
                mRotateAnimation.start();
            }
        });
    }

    public void setProgress(int progress) {
        this.progress = Math.min(progress, maxProgress);
        postInvalidate();
        if (progress == 0) {
            status = 0;
        }
    }

    public int getProgress() {
        return progress;
    }

    /**
     * loading成功后调用
     */
    public void finishSuccess() {
        setProgress(maxProgress);
        this.isSuccess = true;
        status = 1;
        myPost();
    }

    Thread thread;

    public void start() {
        flag = true;
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (flag) {
                        setProgress(0);
                        while (getProgress() < 100) {
                            Thread.sleep(10);
                            setProgress(getProgress() + 1);
                        }
                        finishSuccess();
                        Thread.sleep(1500);
                    }
                } catch (Exception e) {
                    LogUtil.e("睡眠中断");
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void stop() {
        this.flag = false;
        thread.interrupt();
    }

}
