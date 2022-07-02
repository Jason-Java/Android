package com.unite.jasonjar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class JasonRadioGroup extends RadioGroup
{
    public final JasonView jasonView;

    public JasonRadioGroup(Context context)
    {
        super(context);
        jasonView = new JasonView(this);
    }

    public JasonRadioGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        jasonView = new JasonView(this, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        setBackground(null);
        jasonView.drawBackground(canvas);
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }
}
