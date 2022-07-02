package com.unite.jasonjar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        setMeasuredDimension(jasonView.getMeasureWidth(widthMeasureSpec),jasonView.getMeasureHeight(heightMeasureSpec));
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        setBackground(null);
        jasonView.drawBackground(canvas);
        jasonView.drawText(canvas);
    }


}
