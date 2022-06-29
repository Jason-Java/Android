package com.unite.jasonjar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.CaseMap;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.DensityUtil;
import com.unite.jasonjar.util.LogUtil;

public class JasonBaseView extends View
{

    //是否可用
    protected boolean enabled = true;
    //是否可点击
    protected boolean clickable = true;

    protected Paint bgPaint;
    protected Paint textPaint;
    protected Paint strokePaint;


    //背景相关
    protected int bgColorDefault = -1;
    protected int bgColorPress = -1;
    protected int bgColor = 0X80000000;
    protected Drawable bgDrawableDefault;
    protected Drawable bgDrawablePress;
    protected Drawable bgDrawable;
    //圆角相关
    protected float leftTopRadius = 0;
    protected float leftBottomRadius = 0;
    protected float rightTopRadius = 0;
    protected float rightBottomRadius = 0;
    protected float radius = 0;

    //边框
    protected float strokeWidth = 0;
    protected int strokeColor = 0XFF000000;
    protected int strokeColorPress = 0XFF000000;
    protected int strokeColorDefault = 0XFF000000;


    //文字相关
    protected int textColorDefault = 0XFF000000;
    protected int textColorPress = 0XFF000000;
    protected int texColor = 0XFF000000;
    protected float textSize = 20;
    protected String text;


    protected int gravity;
    protected final static int center = 1;
    protected final static int center_vertical = 2;
    protected final static int center_horizontal = 3;

    //内边距相关
    protected float padding;
    protected float paddingLeft;
    protected float paddingRight;
    protected float paddingTop;
    protected float paddingBottom;


    public JasonBaseView(@NonNull Context context)
    {
        super(context);
        init();

    }

    public JasonBaseView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
        initAttributes(attrs);
    }

    public JasonBaseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    protected void init()
    {
        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);

        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setColor(texColor);


        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setAntiAlias(true);

        this.setEnabled(false);
        this.setClickable(false);

    }

    protected void initAttributes(AttributeSet attrs)
    {
        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.JasonBaseView);
        if (attr == null)
        {
            return;
        }
        //背景相关
        try
        {
            //默认背景
            Drawable drawable = attr.getDrawable(R.styleable.JasonBaseView_ja_bgColorDefault);
            if (drawable instanceof ColorDrawable)
            {
                bgColorDefault = ((ColorDrawable) drawable).getColor();
            }
            else
            {
                bgDrawableDefault = drawable;
            }
            //按压背景
            drawable = attr.getDrawable(R.styleable.JasonBaseView_ja_bgColorPress);
            if (drawable instanceof ColorDrawable)
            {
                bgColorPress = ((ColorDrawable) drawable).getColor();
            }
            else
            {
                bgDrawablePress = drawable;
            }

            drawable = attr.getDrawable(R.styleable.JasonBaseView_ja_bgColor);
            if (drawable instanceof ColorDrawable)
            {
                bgColor = ((ColorDrawable) drawable).getColor();
            }
            else
            {
                bgDrawable = drawable;
            }


            if (bgColorDefault != -1)
            {
                bgColor = bgColorDefault;
            }

            if (bgColorPress == -1)
            {
                bgColorPress = bgColor;
            }
            if (bgColorDefault == -1)
            {
                bgColorDefault = bgColor;
            }
            if (bgDrawableDefault != null)
            {
                bgDrawable = bgDrawableDefault;
            }

            if (bgDrawablePress == null)
            {
                bgDrawablePress = bgDrawable;
            }

            if (bgDrawableDefault == null)
            {
                bgDrawableDefault = bgDrawable;
            }

            //圆角相关
            leftTopRadius = attr.getDimension(R.styleable.JasonBaseView_ja_leftTopRadius, 0);
            leftBottomRadius = attr.getDimension(R.styleable.JasonBaseView_ja_leftBottomRadius, 0);
            rightTopRadius = attr.getDimension(R.styleable.JasonBaseView_ja_RightTopRadius, 0);
            rightBottomRadius = attr.getDimension(R.styleable.JasonBaseView_ja_RightBottomRadius, 0);
            radius = attr.getDimension(R.styleable.JasonBaseView_ja_radius, 0);
            if (radius != 0)
            {
                leftTopRadius = radius;
                leftBottomRadius = radius;
                rightTopRadius = radius;
                rightBottomRadius = radius;
            }
            //边框
            strokeWidth = attr.getDimension(R.styleable.JasonBaseView_ja_strokeWidth, DensityUtil.px2dp(0));
            if (strokeWidth > DensityUtil.px2dp(7))
            {
                strokeWidth = DensityUtil.px2dp(7);
            }
            strokeColorPress = attr.getColor(R.styleable.JasonBaseView_ja_strokeColorPress, 0);
            strokeColorDefault = attr.getColor(R.styleable.JasonBaseView_ja_strokeColorDefault, 0);
            strokeColor = attr.getColor(R.styleable.JasonBaseView_ja_strokeColor, 0);
            if (strokeColorPress == 0)
            {
                strokeColorPress = strokeColor;
            }
            if (strokeColorDefault == 0)
            {
                strokeColorDefault = strokeColor;
            }
            //文字相关
            textSize = attr.getDimension(R.styleable.JasonBaseView_ja_textSize, DensityUtil.sp2px(15));

            text = attr.getString(R.styleable.JasonBaseView_ja_text);
            textColorDefault = attr.getColor(R.styleable.JasonBaseView_ja_textColorDefault, 0);
            textColorPress = attr.getColor(R.styleable.JasonBaseView_ja_textColorPress, 0);
            texColor = attr.getColor(R.styleable.JasonBaseView_ja_textColor, 0XFF000000);
            if (textColorDefault == 0)
            {
                textColorDefault = texColor;
            }
            if (textColorPress == 0)
            {
                textColorPress = texColor;
            }

            //默认不可用状态
            enabled = attr.getBoolean(R.styleable.JasonBaseView_ja_enable, false);
            if (!enabled)
            {
                super.setEnabled(false);
            }
            else
            {
                super.setEnabled(true);
            }
            clickable = attr.getBoolean(R.styleable.JasonBaseView_ja_clickable, false);
            if (!clickable)
            {
                super.setClickable(false);
            }
            else
            {
                super.setClickable(true);
            }


            //位置状态  默认居中
            gravity = attr.getInt(R.styleable.JasonBaseView_ja_gravity, 01);

            //内边距相关
            padding = attr.getDimension(R.styleable.JasonBaseView_ja_padding, 0);
            paddingLeft = attr.getDimension(R.styleable.JasonBaseView_ja_paddingLeft, 0);
            paddingRight = attr.getDimension(R.styleable.JasonBaseView_ja_paddingRight, 0);
            paddingTop = attr.getDimension(R.styleable.JasonBaseView_ja_paddingTop, 0);
            paddingBottom = attr.getDimension(R.styleable.JasonBaseView_ja_paddingBottom, 0);
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
                if (enabled && clickable)
                {
                    bgColor = bgColorPress;
                    bgDrawable = bgDrawablePress;
                    texColor = textColorPress;
                    strokeColor = strokeColorPress;
                    this.invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:

                bgColor = bgColorDefault;
                bgDrawable = bgDrawableDefault;
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
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        float widthSize = MeasureSpec.getSize(widthMeasureSpec);
        float heightSize = MeasureSpec.getSize(heightMeasureSpec);
        textPaint.setTextSize(textSize);
        float textWidth = textPaint.measureText(text == null ? "" : text);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        if (widthMode != MeasureSpec.EXACTLY)
        {
            widthSize = paddingLeft + paddingRight + textWidth;
        }
        if (heightMode != MeasureSpec.EXACTLY)
        {
            heightSize = paddingTop + paddingBottom + (fontMetrics.bottom - fontMetrics.top);
        }
        setMeasuredDimension((int) widthSize, (int) heightSize);
    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    protected void drawBackground(Canvas canvas)
    {

        Path path = new Path();
        RectF rectF = new RectF(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
        //阴影

        RadialGradient radialGradient = new RadialGradient((float) (this.getMeasuredWidth() / 2f), (float) this.getMeasuredHeight() / 2f, (float) this.getMeasuredWidth() / 2f, new int[]{0XFFFFFFFF, 0XFF000000}, 1, Shader.TileMode.MIRROR);



        Shader shader = linearGradient;
        bgPaint.setShader(shader);
        canvas.drawPath(path, bgPaint);





        /*//自定义路径
        Path path = new Path();
        RectF rectF = new RectF(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
        canvas.clipPath(path);
        if (bgDrawable != null)
        {
            Bitmap bitmap = ((BitmapDrawable) bgDrawable).getBitmap();
            bgPaint.setAlpha(255);
            canvas.drawBitmap(bitmap, null, rectF, bgPaint);
        }
        else
        {
            //设置画笔颜色
            bgPaint.setColor(bgColor);
            bgPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, bgPaint);
        }

        //绘制边框
        if (strokeWidth > 0)
        {
            strokePaint.setColor(strokeColor);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, strokePaint);
        }
*/

    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    protected void drawText(Canvas canvas)
    {

        float width = this.getMeasuredWidth();
        float height = this.getMeasuredHeight();
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(texColor);
        text = text == null ? "" : text;
        float textWidth = textPaint.measureText(text);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        switch (gravity)
        {
            //center
            case 0X01:
                canvas.drawText(text, (paddingLeft + width - textWidth - paddingRight) / 2.0f, (paddingTop + height - fontMetrics.bottom - fontMetrics.top - paddingBottom) / 2.0f, textPaint);
                break;
            //center_vertical
            case 0X02:
                canvas.drawText(text, paddingLeft - paddingRight, (paddingTop + height - fontMetrics.bottom - fontMetrics.top - paddingBottom) / 2.0f, textPaint);
                break;
            //center_horizontal
            case 0X03:
                canvas.drawText(text, (paddingLeft + width - textWidth - paddingRight) / 2.0f, paddingTop - fontMetrics.top - paddingBottom, textPaint);
                break;
        }

    }

    /**
     * 设置四个角的圆角
     *
     * @param radius
     */
    public void setJaRadius(float radius)
    {
        radius = DensityUtil.dp2px(radius);
        this.leftTopRadius = radius;
        this.leftBottomRadius = radius;
        this.rightTopRadius = radius;
        this.rightBottomRadius = radius;
        invalidate();
    }

    //设置左上角圆角
    public void setJaLeftTopRadius(float radius)
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
    public void setJaLeftBottomRadius(float radius)
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
    public void setJaRightTopRadius(float radius)
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
    public void setJaRightBottomRadius(float radius)
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
    public void setJaBgColorPress(int color)
    {
        this.bgColorPress = color;
        invalidate();
    }

    /**
     * 设置当前背景颜色
     *
     * @param color
     */
    public void setJaBgColorDefault(int color)
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
    public void setJaBackground(int Color)
    {
        this.bgColor = Color;
        invalidate();
    }


    /**
     * 设置
     *
     * @param textColorDefault
     */
    public void setJaTextColorDefault(int textColorDefault)
    {
        this.textColorDefault = textColorDefault;
    }

    public void setJaTextColorPress(int color)
    {
        this.textColorPress = color;
    }

    /**
     * 设置文字大小
     *
     * @param size
     */
    public void setJaTextSize(float size)
    {
        size = DensityUtil.sp2px(size);
        this.textSize = size;
        invalidate();
    }

    /**
     * @param textValue
     */
    public void setJaText(String textValue)
    {
        this.text = textValue;
        invalidate();
    }

    /**
     * 设置描边宽度
     *
     * @param strokeWidth
     */
    public void setJaStrokeWidth(float strokeWidth)
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
    public void setJaStrokeColorPress(int strokeColor)
    {
        this.strokeColorPress = strokeColor;
        invalidate();
    }

    /**
     * 设置边框默认效果
     *
     * @param strokeColor
     */
    public void setJaStrokeColorDefault(int strokeColor)
    {
        this.strokeColorDefault = strokeColor;
        this.strokeColor = strokeColorDefault;
        invalidate();
    }


    /**
     * 设置gravity
     *
     * @param gravity
     */
    public void setJaGravity(int gravity)
    {
        this.gravity = gravity;
        invalidate();
    }

    /**
     * 获取文字画笔
     *
     * @return
     */
    public Paint getTextPaint()
    {
        return textPaint;
    }

    /**
     * 获取背景画笔
     *
     * @return
     */
    public Paint getBgPaint()
    {
        return bgPaint;
    }

    /**
     * 获取边框画笔
     *
     * @return
     */
    public Paint getStrokePaint()
    {
        return strokePaint;
    }

    /**
     * 是否可用
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        super.setEnabled(enabled);
        invalidate();
    }

    /**
     * 是否可点击
     *
     * @param clickable
     */
    public void setClickable(boolean clickable)
    {
        this.clickable = clickable;
        super.setClickable(clickable);
        invalidate();
    }


}
