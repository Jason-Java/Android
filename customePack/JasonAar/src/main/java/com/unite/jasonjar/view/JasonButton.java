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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.LogUtil;

public class JasonButton extends JasonBaseView
{

    public JasonButton(@NonNull Context context)
    {
        super(context);
        init();

    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
        initAttributes(attrs);
    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init()
    {
        super.init();
        this.setEnabled(true);
        this.setClickable(true);
    }


    @Override
    protected void initAttributes(AttributeSet attrs)
    {
        super.initAttributes(attrs);
        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.JasonBaseView);

        try
        {
            //默认不可用状态
            enabled = attr.getBoolean(R.styleable.JasonBaseView_ja_enable, true);
            if (enabled)
            {
                super.setEnabled(true);
            }
            else
            {
                super.setEnabled(false);
            }
            clickable = attr.getBoolean(R.styleable.JasonBaseView_ja_clickable, true);
            if (clickable)
            {
                super.setClickable(true);
            }
            else
            {
                super.setClickable(false);
            }

        } catch (Exception e)
        {
            LogUtil.e("报错了");
        } finally
        {
            attr.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        drawBackground(canvas);
        drawText(canvas);
    }

    protected void drawBackground(Canvas canvas)
    {
        //自定义路径
        Path path = new Path();
        Rect rect = new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        RectF rectF = new RectF(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);

            /*//设置画笔颜色
            bgPaint.setColor(bgColor);
            bgPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, bgPaint);*/
        canvas.clipPath(path);
        canvas.drawBitmap(((BitmapDrawable) bgDrawable).getBitmap(),null,rectF,bgPaint);


        //绘制边框
        if (strokeWidth > 0)
        {
            strokePaint.setColor(strokeColor);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, strokePaint);
        }

    }


}
