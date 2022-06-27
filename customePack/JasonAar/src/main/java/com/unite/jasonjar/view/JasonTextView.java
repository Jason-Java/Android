package com.unite.jasonjar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.DensityUtil;
import com.unite.jasonjar.util.LogUtil;

public class JasonTextView extends JasonBaseView
{





    public JasonTextView(@NonNull Context context)
    {
        super(context);
        init();
    }

    public JasonTextView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
        initAttributes(attrs);
    }

    public JasonTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init()
    {
        super.init();
        super.setEnabled(false);
        super.setEnabled(false);

    }

    @Override
    protected void initAttributes(AttributeSet attrs)
    {
        super.initAttributes(attrs);

        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.JasonBaseView);
        if (attr == null)
        {
            return;
        }
        //背景相关
        try
        {

        } catch (Exception e)
        {
            LogUtil.e("报错了");

        } finally
        {
            attr.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //绘制背景
        drawBackground(canvas);
        //绘制文字
        drawText(canvas);
    }





}
