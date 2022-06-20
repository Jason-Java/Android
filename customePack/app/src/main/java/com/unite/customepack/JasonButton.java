package com.unite.customepack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import util.DensityUtil;
import util.LogUtil;

public class JasonButton extends androidx.appcompat.widget.AppCompatTextView {


    private int bgColorDefault = 0X80000000;
    private int bgColorPress = 0X80000000;
    private int color = 0X80000000;

    private float leftTopRadius = 0;
    private float leftBottomRadius = 0;
    private float rightTopRadius = 0;
    private float rightBottomRadius = 0;

    //边框
    private float strokeWidth = 0;
    private int strokeColor = 0XFF000000;

    private int textColorDefault = 0XFF000000;
    private int textColorPress = 0XFF000000;
    private int texColor = 0XFF000000;
    private float textSize = 20;


    private Paint paint;


    public JasonButton(@NonNull Context context) {
        super(context);
        init();

    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JasonButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(bgColorDefault);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(color);
        this.setClickable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);

        switch (event.getAction()) {
            //按下效果
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                color = bgColorPress;
                texColor=textColorPress;
                this.invalidate();

                break;

            case MotionEvent.ACTION_UP:
                color = bgColorDefault;
                texColor = textColorPress;
                invalidate();
                break;
        }
        return flag;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        drawBackground(canvas);

        float width=super.getMeasuredWidth();
        float height = super.getMeasuredHeight();
        String textValue=super.getText().toString();
        paint.setTextSize(super.getPaint().getTextSize());
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(texColor);
        
        float textWidth = paint.measureText(textValue);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        LogUtil.e(height+"  "+fontMetrics.ascent);

        canvas.drawText(textValue, (width - textWidth) / 2.0f, (height - fontMetrics.descent-fontMetrics.ascent) / 2.0f,paint);
    }


    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {

        //自定义路径
        Path path = new Path();
        RectF rectF = new RectF(0, 0, super.getMeasuredWidth(), super.getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
       //设置画笔颜色
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

        //绘制边框
        if (strokeWidth > 0) {
            paint.setColor(strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, paint);
        }

    }

    /**
     * 设置四个角的圆角
     *
     * @param radius
     */
    public void setRadius(float radius) {
        radius = DensityUtil.dp2px(getContext(), radius);
        this.leftTopRadius = radius;
        this.leftBottomRadius = radius;
        this.rightTopRadius = radius;
        this.rightBottomRadius = radius;
        invalidate();
    }

    //设置左上角圆角
    public void setLeftTopRadius(float radius) {
        radius = DensityUtil.dp2px(getContext(), radius);
        this.leftTopRadius = radius;
        invalidate();
    }

    /**
     * 设置左下角圆角
     *
     * @param radius
     */
    public void setLeftBottomRadius(float radius) {
        radius = DensityUtil.dp2px(getContext(), radius);
        this.leftBottomRadius = radius;
        invalidate();
    }

    /**
     * 设置右上角圆角
     *
     * @param radius
     */
    public void setRightTopRadius(float radius) {
        radius = DensityUtil.dp2px(getContext(), radius);
        this.rightTopRadius = radius;
        invalidate();
    }

    /**
     * 设置右下角圆角
     *
     * @param radius
     */
    public void setRightBottomRadius(float radius) {
        radius = DensityUtil.dp2px(getContext(), radius);
        this.rightBottomRadius = radius;
        invalidate();
    }


    /**
     * 设置按下背景颜色
     *
     * @param color
     */
    public void setBgColorPress(int color) {
        this.bgColorPress = color;
        invalidate();
    }

    /**
     * 设置当前背景颜色
     *
     * @param color
     */
    public void setBgColorDefault(int color) {
        this.bgColorDefault = color;
        this.color = color;
        invalidate();
    }

    /**
     * 设置背景颜色
     * @param Color
     */
    public void setBackground(int Color) {
        this.color = color;
        invalidate();
    }


    /**
     * 设置
     * @param textColorDefault
     */
    public void setTextColorDefault(int textColorDefault) {
        this.textColorDefault = textColorDefault;
    }

    public void setTextColorPress(int color) {
        this.textColorPress = color;
    }

    /**
     * 设置文字大小
     * @param size
     */
    public void setTextSize(float size) {
        size = DensityUtil.dp2px(getContext(), size);
       // this.textSize = size;
        super.setTextSize(size);
        invalidate();
    }

    /**
     * 设置描边宽度
     * @param strokeWidth
     */
    public void setStrokeWidth(float strokeWidth) {
        strokeWidth = DensityUtil.dp2px(getContext(), strokeWidth);
        this.strokeWidth = strokeWidth;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }
}
