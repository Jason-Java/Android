package com.unite.customdrawable;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class LoadingView extends View {


    private Paint paint = null;
    private PointF middlePoint = null;
    private float width, height;
    private RectF rectF = new RectF();
    private int angle = 0;



    public LoadingView(Context context) {
        super(context);

    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                angle = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getWidth() * 0.08f);
        width = getWidth();
        height = getHeight();

        rectF.top = width * 0.2f;
        rectF.left = width * 0.2f;
        rectF.bottom=height*0.8f;
        rectF.right = height * 0.8f;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();

        paint.setARGB(255, 239, 239, 239);
        canvas.drawArc(rectF, 0 ,  360, false, paint);
        paint.setARGB(255, 209, 209, 209);
        canvas.drawArc(rectF, -35+angle ,  angle, false, paint);
    }
}
