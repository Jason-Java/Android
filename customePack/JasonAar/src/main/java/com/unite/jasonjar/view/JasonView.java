package com.unite.jasonjar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
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
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.R;
import com.unite.jasonjar.util.DensityUtil;
import com.unite.jasonjar.util.LogUtil;

public class JasonView {

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


    private View view;

    public JasonView(View view) {
        this.view = view;
        init();
    }

    public JasonView(View view, AttributeSet attrs) {
        this.view = view;
        init();
        initAttributes(attrs);
    }


    protected void init() {
        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);
        bgPaint.setDither(true);

        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setColor(texColor);
        textPaint.setDither(true);


        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setAntiAlias(true);
        strokePaint.setDither(true);
    }

    protected void initAttributes(AttributeSet attrs) {

        TypedArray attr = view.getContext().obtainStyledAttributes(attrs, R.styleable.JasonBaseView);
        if (attr == null) {
            return;
        }
        //背景相关
        try {
            //默认背景
            Drawable drawable = attr.getDrawable(R.styleable.JasonBaseView_ja_bgColorDefault);
            if (drawable instanceof ColorDrawable) {
                bgColorDefault = ((ColorDrawable) drawable).getColor();
            } else {
                bgDrawableDefault = drawable;
            }
            //按压背景
            drawable = attr.getDrawable(R.styleable.JasonBaseView_ja_bgColorPress);
            if (drawable instanceof ColorDrawable) {
                bgColorPress = ((ColorDrawable) drawable).getColor();
            } else {
                bgDrawablePress = drawable;
            }

            drawable = attr.getDrawable(R.styleable.JasonBaseView_ja_bgColor);
            if (drawable instanceof ColorDrawable) {
                bgColor = ((ColorDrawable) drawable).getColor();
            } else {
                bgDrawable = drawable;
            }


            if (bgColorDefault != -1) {
                bgColor = bgColorDefault;
            }

            if (bgColorPress == -1) {
                bgColorPress = bgColor;
            }
            if (bgColorDefault == -1) {
                bgColorDefault = bgColor;
            }
            if (bgDrawableDefault != null) {
                bgDrawable = bgDrawableDefault;
            }

            if (bgDrawablePress == null) {
                bgDrawablePress = bgDrawable;
            }

            if (bgDrawableDefault == null) {
                bgDrawableDefault = bgDrawable;
            }

            //圆角相关
            leftTopRadius = attr.getDimension(R.styleable.JasonBaseView_ja_leftTopRadius, 0);
            leftBottomRadius = attr.getDimension(R.styleable.JasonBaseView_ja_leftBottomRadius, 0);
            rightTopRadius = attr.getDimension(R.styleable.JasonBaseView_ja_RightTopRadius, 0);
            rightBottomRadius = attr.getDimension(R.styleable.JasonBaseView_ja_RightBottomRadius, 0);
            radius = attr.getDimension(R.styleable.JasonBaseView_ja_radius, 0);
            if (radius != 0) {
                leftTopRadius = radius;
                leftBottomRadius = radius;
                rightTopRadius = radius;
                rightBottomRadius = radius;
            }
            //边框
            strokeWidth = attr.getDimension(R.styleable.JasonBaseView_ja_strokeWidth, DensityUtil.px2dp(0));
            if (strokeWidth > DensityUtil.dp2px(7)) {
                strokeWidth = DensityUtil.dp2px(7);
            }
            strokeColorPress = attr.getColor(R.styleable.JasonBaseView_ja_strokeColorPress, 0);
            strokeColorDefault = attr.getColor(R.styleable.JasonBaseView_ja_strokeColorDefault, 0);
            strokeColor = attr.getColor(R.styleable.JasonBaseView_ja_strokeColor, 0);
            if (strokeColorPress == 0) {
                strokeColorPress = strokeColor;
            }
            if (strokeColorDefault == 0) {
                strokeColorDefault = strokeColor;
            }
            //文字相关
            textSize = attr.getDimension(R.styleable.JasonBaseView_ja_textSize, DensityUtil.sp2px(15));

            text = attr.getString(R.styleable.JasonBaseView_ja_text);
            textColorPress = attr.getColor(R.styleable.JasonBaseView_ja_textColorPress, -103);
            textColorDefault = attr.getColor(R.styleable.JasonBaseView_ja_textColorDefault, -103);
            texColor = attr.getColor(R.styleable.JasonBaseView_ja_textColor, 0XFF000000);

            if (textColorDefault != -103) {
                texColor = textColorDefault;
            }

            if (textColorDefault == -103) {
                textColorDefault = texColor;
            }
            if (textColorPress == -103) {
                textColorPress = texColor;
            }

            //默认不可用状态
            enabled = attr.getBoolean(R.styleable.JasonBaseView_ja_enable, true);
            if (!enabled) {
                view.setEnabled(false);
            } else {
                view.setEnabled(true);
            }
            clickable = attr.getBoolean(R.styleable.JasonBaseView_ja_clickable, true);
            if (!clickable) {
                view.setClickable(false);
            } else {
                view.setClickable(true);
            }


            //位置状态  默认居中
            gravity = attr.getInt(R.styleable.JasonBaseView_ja_gravity, 01);

            //内边距相关
            padding = attr.getDimension(R.styleable.JasonBaseView_ja_padding, 0);
            paddingLeft = attr.getDimension(R.styleable.JasonBaseView_ja_paddingLeft, 0);
            paddingRight = attr.getDimension(R.styleable.JasonBaseView_ja_paddingRight, 0);
            paddingTop = attr.getDimension(R.styleable.JasonBaseView_ja_paddingTop, 0);
            paddingBottom = attr.getDimension(R.styleable.JasonBaseView_ja_paddingBottom, 0);
            if (paddingLeft == 0) {
                paddingLeft = padding;
            }
            if (paddingRight == 0) {
                paddingRight = padding;
            }
            if (paddingTop == 0) {
                paddingTop = padding;
            }
            if (paddingBottom == 0) {
                paddingBottom = padding;
            }


        } finally {
            attr.recycle();
        }
    }


    public void onTouchEvent(MotionEvent event) {
        int viewHeight = view.getMeasuredHeight();
        int viewWidth = view.getMeasuredWidth();
        int locations[] = new int[2];
        view.getLocationInWindow(locations);
        switch (event.getAction()) {
            //按下效果
            case MotionEvent.ACTION_DOWN:
                if (enabled && clickable) {
                    bgColor = bgColorPress;
                    bgDrawable = bgDrawablePress;
                    texColor = textColorPress;
                    strokeColor = strokeColorPress;
                    view.invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                bgColor = bgColorDefault;
                bgDrawable = bgDrawableDefault;
                texColor = textColorDefault;
                strokeColor = strokeColorDefault;
                view.invalidate();
                break;
        }
    }


    public int getMeasureWidth(int widthMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        float widthSize = View.MeasureSpec.getSize(widthMeasureSpec);

        textPaint.setTextSize(textSize);
        float textWidth = textPaint.measureText(text == null ? "" : text);
        if (widthMode != View.MeasureSpec.EXACTLY) {
            widthSize = paddingLeft + paddingRight + textWidth;
        }
        return (int) widthSize;
    }

    public int getMeasureHeight(int heightMeasureSpec) {
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        float heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        textPaint.setTextSize(textSize);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        if (heightMode != View.MeasureSpec.EXACTLY) {
            heightSize = paddingTop + paddingBottom + (fontMetrics.bottom - fontMetrics.top);
        }
        return (int) heightSize;
    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    protected void drawBackground(Canvas canvas) {
        //自定义路径
        Path path = new Path();
        RectF rectF = new RectF(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
        canvas.clipPath(path);
        if (bgDrawable != null) {
            Bitmap bitmap = ((BitmapDrawable) bgDrawable).getBitmap();
            bgPaint.setAlpha(255);
            canvas.drawBitmap(bitmap, null, rectF, bgPaint);
        } else {
            //设置画笔颜色
            bgPaint.setColor(bgColor);
            bgPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, bgPaint);
        }

        //绘制边框
        if (strokeWidth > 0) {
            strokePaint.setColor(strokeColor);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, strokePaint);
        }

    }


    /**
     * 绘制文字
     *
     * @param canvas
     */
    protected void drawText(Canvas canvas) {

        float width = view.getMeasuredWidth();
        float height = view.getMeasuredHeight();
        textPaint.setTextSize(textSize);
        textPaint.setColor(texColor);
        text = text == null ? "" : text;
        float textWidth = textPaint.measureText(text);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        
        switch (gravity) {
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
    public void setJaRadius(float radius) {
        radius = DensityUtil.dp2px(radius);
        this.leftTopRadius = radius;
        this.leftBottomRadius = radius;
        this.rightTopRadius = radius;
        this.rightBottomRadius = radius;
        view.invalidate();
    }

    //设置左上角圆角
    public void setJaLeftTopRadius(float radius) {
        radius = DensityUtil.dp2px(radius);
        this.leftTopRadius = radius;
        view.invalidate();
    }

    /**
     * 设置左下角圆角
     *
     * @param radius
     */
    public void setJaLeftBottomRadius(float radius) {
        radius = DensityUtil.dp2px(radius);
        this.leftBottomRadius = radius;
        view.invalidate();
    }

    /**
     * 设置右上角圆角
     *
     * @param radius
     */
    public void setJaRightTopRadius(float radius) {
        radius = DensityUtil.dp2px(radius);
        this.rightTopRadius = radius;
        view.invalidate();
    }

    /**
     * 设置右下角圆角
     *
     * @param radius
     */
    public void setJaRightBottomRadius(float radius) {
        radius = DensityUtil.dp2px(radius);
        this.rightBottomRadius = radius;
        view.invalidate();
    }


    /**
     * 设置按下背景颜色
     *
     * @param color
     */
    public void setJaBgColorPress(int color) {
        this.bgColorPress = color;
        view.invalidate();
    }

    /**
     * 设置当前背景颜色
     *
     * @param color
     */
    public void setJaBgColorDefault(int color) {
        this.bgColorDefault = color;
        this.bgColor = color;
        view.invalidate();
    }

    /**
     * 设置背景颜色
     *
     * @param Color
     */
    public void setJaBackground(int Color) {
        this.bgColor = Color;
        view.invalidate();
    }


    /**
     * 设置
     *
     * @param textColorDefault
     */
    public void setJaTextColorDefault(int textColorDefault) {
        this.textColorDefault = textColorDefault;
    }

    public void setJaTextColorPress(int color) {
        this.textColorPress = color;
    }

    /**
     * 设置文字大小
     *
     * @param size
     */
    public void setJaTextSize(float size) {
        size = DensityUtil.sp2px(size);
        this.textSize = size;
        view.invalidate();
    }

    /**
     * @param textValue
     */
    public void setJaText(String textValue) {
        this.text = textValue;
        view.invalidate();
    }

    /**
     * 设置描边宽度
     *
     * @param strokeWidth
     */
    public void setJaStrokeWidth(float strokeWidth) {
        strokeWidth = DensityUtil.dp2px(strokeWidth);
        this.strokeWidth = strokeWidth;
        view.invalidate();
    }

    /**
     * 设置边框按压效果
     *
     * @param strokeColor
     */
    public void setJaStrokeColorPress(int strokeColor) {
        this.strokeColorPress = strokeColor;
        view.invalidate();
    }

    /**
     * 设置边框默认效果
     *
     * @param strokeColor
     */
    public void setJaStrokeColorDefault(int strokeColor) {
        this.strokeColorDefault = strokeColor;
        this.strokeColor = strokeColorDefault;
        view.invalidate();
    }


    /**
     * 设置gravity
     *
     * @param gravity
     */
    public void setJaGravity(int gravity) {
        this.gravity = gravity;
        view.invalidate();
    }

    /**
     * 获取文字画笔
     *
     * @return
     */
    public Paint getTextPaint() {
        return textPaint;
    }

    /**
     * 获取背景画笔
     *
     * @return
     */
    public Paint getBgPaint() {
        return bgPaint;
    }

    /**
     * 获取边框画笔
     *
     * @return
     */
    public Paint getStrokePaint() {
        return strokePaint;
    }

    /**
     * 是否可用
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        view.setEnabled(enabled);
        view.invalidate();
    }

    /**
     * 是否可点击
     *
     * @param clickable
     */
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
        view.setClickable(clickable);
        view.invalidate();
    }

}
