package com.unite.customepack;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import util.LogUtil;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint paint;
    private float textX;
    private int height;
    int width;
    int leftPadding;
    int rightPadding;
    float textWidth;
    int duration;
    String text;
    ValueAnimator animatorFirst;
    ValueAnimator animatorSecond;


    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = this.getPaint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
        height = this.getMeasuredHeight();
        leftPadding = this.getPaddingLeft();
        rightPadding = this.getPaddingRight();
        textX = leftPadding;
        if(textWidth<=width)
            return;
        animatorFirst();
        animatorSecond();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect();
        text = this.getText().toString();
        textWidth = paint.measureText(text);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int y = (int) (height / 2 + Math.abs(fontMetrics.ascent + fontMetrics.descent) / 2);
        canvas.drawText(text, textX, y, paint);
    }

    private void animatorFirst() {
        animatorFirst = ObjectAnimator.ofFloat(0, textWidth + width);
        animatorFirst.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textX = (float) animation.getAnimatedValue();
                textX = leftPadding - textX;
                invalidate();
            }
        });
        animatorFirst.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSecond.start();
            }
        });
        int time = 2000 + (int) (textWidth * 5);
        animatorFirst.setDuration(time);
        animatorFirst.setRepeatCount(0);
        animatorFirst.start();
    }

    private void animatorSecond() {

        animatorSecond = ValueAnimator.ofFloat(width, -(textWidth + width));
        animatorSecond.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textX = (float) animation.getAnimatedValue();
                LogUtil.i((float) animation.getAnimatedValue() + "  " + width);
                postInvalidate();
            }
        });
        int time = 2000 + (int) (textWidth * 5);
        animatorSecond.setDuration(time);
        animatorSecond.setInterpolator(new LinearInterpolator());
        animatorSecond.setRepeatCount(-1);
    }

}
