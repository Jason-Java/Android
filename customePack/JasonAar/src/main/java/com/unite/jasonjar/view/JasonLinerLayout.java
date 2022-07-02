package com.unite.jasonjar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.DensityUtil;

/**
 * @author Jason
 * @version 1.0.0
 * @data 2022/6/30
 */
public class JasonLinerLayout extends LinearLayout
{

    public final JasonView jasonView;


    public JasonLinerLayout(Context context)
    {
        super(context);
        jasonView = new JasonView(this);
    }

    public JasonLinerLayout(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        jasonView = new JasonView(this, attrs);
    }

    public JasonLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
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
    protected void dispatchDraw(Canvas canvas)
    {
        jasonView.drawBackground(canvas);
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        setBackground(null);
        super.onDraw(canvas);
    }
}
