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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.DensityUtil;
import com.unite.jasonjar.util.LogUtil;

public class JasonTextView extends androidx.appcompat.widget.AppCompatTextView
{
    private JasonView jasonView;

    public JasonTextView(@NonNull Context context)
    {
        super(context);
        jasonView = new JasonView(this);

    }

    public JasonTextView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        jasonView = new JasonView(this, attrs);
    }

    public JasonTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean flag = super.onTouchEvent(event);
        jasonView.onTouchEvent(event);
        return flag;
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        //绘制背景
        setBackground(null);
        jasonView.drawBackground(canvas);
        super.onDraw(canvas);
    }


}
