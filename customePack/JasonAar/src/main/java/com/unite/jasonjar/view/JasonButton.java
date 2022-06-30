package com.unite.jasonjar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.EventLog;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.LogUtil;

public class JasonButton extends androidx.appcompat.widget.AppCompatButton
{

    private JasonView jasonView;

    public JasonButton(@NonNull Context context)
    {
        super(context);
        jasonView = new JasonView(this);
    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        jasonView = new JasonView(this, attrs);
    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        jasonView = new JasonView(this, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean flag = super.onTouchEvent(event);
        jasonView.onTouchEvent(event);
        return flag;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(jasonView.geMeasureWidth(widthMeasureSpec),jasonView.geMeasureHeight(heightMeasureSpec));
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        setBackground(null);
        jasonView.drawBackground(canvas);
        jasonView.drawText(canvas);
    }


}
