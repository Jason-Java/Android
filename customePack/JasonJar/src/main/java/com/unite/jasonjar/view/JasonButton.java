package com.unite.jasonjar.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;

import util.DensityUtil;
import util.LogUtil;

public class JasonButton extends View
{

    //背景相关
    private int bgColorDefault = 0X80000000;
    private int bgColorPress = 0X80000000;
    private int bgColor = 0X80000000;
    //圆角相关
    private float leftTopRadius = 0;
    private float leftBottomRadius = 0;
    private float rightTopRadius = 0;
    private float rightBottomRadius = 0;
    private float radius = 0;

    //边框
    private float strokeWidth = 0;
    private int strokeColor = 0XFF000000;
    private int strokeColorPress = 0XFF000000;
    private int strokeColorDefault = 0XFF000000;

    //文字相关
    private int textColorDefault = 0XFF000000;
    private int textColorPress = 0XFF000000;
    private int texColor = 0XFF000000;
    private float textSize = 20;
    private String text;
    private Paint paint;

    //是否可用
    boolean enabled = true;
    int gravity;

    //内边距相关
    private float padding;
    private float paddingLeft;
    private float paddingRight;
    private float paddingTop;
    private float paddingBottom;


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

    private void init()
    {
        paint = new Paint();
        paint.setColor(bgColorDefault);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(bgColor);
        this.setClickable(true);
    }

    private void initAttributes(AttributeSet attrs)
    {
        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.JasonButton);
        if (attr == null)
        {
            return;
        }
        //背景相关
        try
        {
            bgColorDefault = attr.getColor(R.styleable.JasonButton_ja_bgColorDefault, 0);
            bgColorPress = attr.getColor(R.styleable.JasonButton_ja_bgColorPress, 0);
            bgColor = attr.getColor(R.styleable.JasonButton_ja_bgColor, 0X80000000);
            if (bgColorDefault != 0)
            {
                bgColor = bgColorDefault;
            }

            if (bgColorPress == 0)
            {
                bgColorPress = bgColor;
            }
            if (bgColorDefault == 0)
            {
                bgColorDefault = bgColor;
            }

            //圆角相关
            leftTopRadius = attr.getDimension(R.styleable.JasonButton_ja_leftTopRadius, 0);
            leftBottomRadius = attr.getDimension(R.styleable.JasonButton_ja_leftBottomRadius, 0);
            rightTopRadius = attr.getDimension(R.styleable.JasonButton_ja_RightTopRadius, 0);
            rightBottomRadius = attr.getDimension(R.styleable.JasonButton_ja_RightBottomRadius, 0);
            radius = attr.getDimension(R.styleable.JasonButton_ja_radius, 0);
            if (radius != 0)
            {
                leftTopRadius = radius;
                leftBottomRadius = radius;
                rightTopRadius = radius;
                rightBottomRadius = radius;
            }
            //边框
            strokeWidth = attr.getDimension(R.styleable.JasonButton_ja_strokeWidth, DensityUtil.px2dp(0));
            if (strokeWidth > DensityUtil.px2dp(7))
            {
                strokeWidth = DensityUtil.px2dp(7);
            }
            strokeColorPress = attr.getColor(R.styleable.JasonButton_ja_strokeColorPress, 0);
            strokeColorDefault = attr.getColor(R.styleable.JasonButton_ja_strokeColorDefault, 0);
            strokeColor = attr.getColor(R.styleable.JasonButton_ja_strokeColor, 0);
            if (strokeColorPress == 0)
            {
                strokeColorPress = strokeColor;
            }
            if (strokeColorDefault == 0)
            {
                strokeColorDefault = strokeColor;
            }
            //文字相关
            textSize = attr.getDimension(R.styleable.JasonButton_ja_textSize, DensityUtil.sp2px(15));
            text = attr.getString(R.styleable.JasonButton_ja_text);
            textColorDefault = attr.getColor(R.styleable.JasonButton_ja_textColorDefault, 0);
            textColorPress = attr.getColor(R.styleable.JasonButton_ja_textColorPress, 0);
            texColor = attr.getColor(R.styleable.JasonButton_ja_textColor, 0XFF000000);
            if (textColorDefault == 0)
            {
                textColorDefault = texColor;
            }
            if (textColorPress == 0)
            {
                textColorPress = texColor;
            }

            //默认可用状态
            enabled = attr.getBoolean(R.styleable.JasonButton_ja_enable, true);
            if (!enabled)
            {
                this.setClickable(false);
            }
            //位置状态
            gravity = attr.getInt(R.styleable.JasonButton_ja_gravity, 01);


            //内边距相关
            padding = attr.getDimension(R.styleable.JasonButton_ja_padding, 2);
            paddingLeft = attr.getDimension(R.styleable.JasonButton_ja_paddingLeft, 0);
            paddingRight = attr.getDimension(R.styleable.JasonButton_ja_paddingRight, 0);
            paddingTop = attr.getDimension(R.styleable.JasonButton_ja_paddingTop, 0);
            paddingBottom = attr.getDimension(R.styleable.JasonButton_ja_paddingBottom, 0);
            if (paddingLeft == 0)
            {
                paddingLeft = padding;
            }
            if (paddingRight == 0)
            {
                paddingRight = padding;
            }
            if (paddingTop == 0)
            {
                paddingTop = padding;
            }
            if (paddingBottom == 0)
            {
                paddingBottom = padding;
            }

        } finally
        {
            attr.recycle();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean flag = super.onTouchEvent(event);

        switch (event.getAction())
        {
            //按下效果
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                bgColor = bgColorPress;
                texColor = textColorPress;
                strokeColor = strokeColorPress;
                this.invalidate();
                break;

            case MotionEvent.ACTION_UP:
                bgColor = bgColorDefault;
                texColor = textColorDefault;
                strokeColor = strokeColorDefault;
                invalidate();
                break;
        }
        return flag;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        //绘制背景
        drawBackground(canvas);
        //绘制文字
        drawText(canvas);
    }


    /**
     * 绘制背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas)
    {

        //自定义路径
        Path path = new Path();
        RectF rectF = new RectF(0, 0, super.getMeasuredWidth(), super.getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
        //设置画笔颜色
        paint.setColor(bgColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

        //绘制边框
        if (strokeWidth > 0)
        {
            paint.setColor(strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, paint);
        }

    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas)
    {
        float width = super.getMeasuredWidth();
        float height = super.getMeasuredHeight();
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(texColor);
        float textWidth = paint.measureText(text);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        LogUtil.e(height + "  " + fontMetrics.ascent);
        switch (gravity)
        {
            //center
            case 0X01:
                canvas.drawText(text, (width - -textWidth) / 2.0f, paddingTop + (height - fontMetrics.descent - fontMetrics.ascent) / 2.0f, paint);
                break;
            //center_vertical
            case 0X02:
                canvas.drawText(text, paddingLeft, (height - fontMetrics.descent - fontMetrics.ascent) / 2.0f, paint);
                break;
            //center_horizontal
            case 0X03:
                canvas.drawText(text, (width - textWidth) / 2.0f, -fontMetrics.ascent, paint);
                break;
                //center
        }


    }

    /**
     * 设置四个角的圆角
     *
     * @param radius
     */
    public void setRadius(float radius)
    {
        radius = DensityUtil.dp2px(radius);
        this.leftTopRadius = radius;
        this.leftBottomRadius = radius;
        this.rightTopRadius = radius;
        this.rightBottomRadius = radius;
        invalidate();
    }

    //设置左上角圆角
    public void setLeftTopRadius(float radius)
    {
        radius = DensityUtil.dp2px(radius);
        this.leftTopRadius = radius;
        invalidate();
    }

    /**
     * 设置左下角圆角
     *
     * @param radius
     */
    public void setLeftBottomRadius(float radius)
    {
        radius = DensityUtil.dp2px(radius);
        this.leftBottomRadius = radius;
        invalidate();
    }

    /**
     * 设置右上角圆角
     *
     * @param radius
     */
    public void setRightTopRadius(float radius)
    {
        radius = DensityUtil.dp2px(radius);
        this.rightTopRadius = radius;
        invalidate();
    }

    /**
     * 设置右下角圆角
     *
     * @param radius
     */
    public void setRightBottomRadius(float radius)
    {
        radius = DensityUtil.dp2px(radius);
        this.rightBottomRadius = radius;
        invalidate();
    }


    /**
     * 设置按下背景颜色
     *
     * @param color
     */
    public void setBgColorPress(int color)
    {
        this.bgColorPress = color;
        invalidate();
    }

    /**
     * 设置当前背景颜色
     *
     * @param color
     */
    public void setBgColorDefault(int color)
    {
        this.bgColorDefault = color;
        this.bgColor = color;
        invalidate();
    }

    /**
     * 设置背景颜色
     *
     * @param Color
     */
    public void setBackground(int Color)
    {
        this.bgColor = bgColor;
        invalidate();
    }


    /**
     * 设置
     *
     * @param textColorDefault
     */
    public void setTextColorDefault(int textColorDefault)
    {
        this.textColorDefault = textColorDefault;
    }

    public void setTextColorPress(int color)
    {
        this.textColorPress = color;
    }

    /**
     * 设置文字大小
     *
     * @param size
     */
    public void setTextSize(float size)
    {
        size = DensityUtil.sp2px(size);
        this.textSize = size;
        invalidate();
    }

    /**
     * 设置描边宽度
     *
     * @param strokeWidth
     */
    public void setStrokeWidth(float strokeWidth)
    {
        strokeWidth = DensityUtil.dp2px(strokeWidth);
        this.strokeWidth = strokeWidth;
        invalidate();
    }

    /**
     * 设置边框按压效果
     *
     * @param strokeColor
     */
    public void setStrokeColorPress(int strokeColor)
    {
        this.strokeColorPress = strokeColor;
        invalidate();
    }

    /**
     * 设置边框默认效果
     *
     * @param strokeColor
     */
    public void setStrokeColorDefault(int strokeColor)
    {
        this.strokeColorDefault = strokeColor;
        this.strokeColor = strokeColorDefault;
        invalidate();
    }


}
