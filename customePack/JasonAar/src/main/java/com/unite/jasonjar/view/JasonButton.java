package com.unite.jasonjar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class JasonButton extends Button {

    public final JasonView jasonView;

    public JasonButton(@NonNull Context context) {
        super(context);
        jasonView = new JasonView(this);
    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        jasonView = new JasonView(this, attrs);
    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        jasonView = new JasonView(this, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);
        jasonView.onTouchEvent(event);
        return flag;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(jasonView.getMeasureWidth(widthMeasureSpec), jasonView.getMeasureHeight(heightMeasureSpec));
    }


    int drawableWidth;
    int drawableHeight;
    int viewWidth;
    int viewHeight;
    Drawable drawable;
    Bitmap bitmap;
    @Override
    protected void onDraw(Canvas canvas) {
        setBackground(null);
        jasonView.drawBackground(canvas);
        jasonView.drawText(canvas);

        /*
        drawable=this.getCompoundDrawables()[0];
        if (drawable != null) {
            bitmap=((BitmapDrawable) drawable).getBitmap();
            drawableWidth = bitmap.getWidth();
            drawableHeight = bitmap.getHeight();
            canvas.drawBitmap(bitmap, 10, (getMeasuredHeight()-drawableHeight)/2, jasonView.bgPaint);
        }
        drawable=this.getCompoundDrawables()[1];
        if (drawable != null) {
            bitmap=((BitmapDrawable) drawable).getBitmap();
            drawableWidth = bitmap.getWidth();
            drawableHeight = bitmap.getHeight();
            canvas.drawBitmap(bitmap, (getMeasuredWidth()-drawableWidth)/2, 10, jasonView.bgPaint);
        }

        drawable = this.getCompoundDrawables()[2];
        if (drawable != null) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            drawableWidth = bitmap.getWidth();
            drawableHeight = bitmap.getHeight();
            canvas.drawBitmap(bitmap, getMeasuredWidth()-drawableWidth-10, (getMeasuredHeight()-drawableHeight)/2, jasonView.bgPaint);
        }
        drawable = this.getCompoundDrawables()[3];
        if (drawable != null) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            drawableWidth = bitmap.getWidth();
            drawableHeight = bitmap.getHeight();
            canvas.drawBitmap(bitmap, (getMeasuredWidth()-drawableWidth)/2, (getMeasuredHeight()-drawableHeight)-10, jasonView.bgPaint);
        }
        */


    }
}
