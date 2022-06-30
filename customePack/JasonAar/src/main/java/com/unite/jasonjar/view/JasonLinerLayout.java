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
public class JasonLinerLayout extends LinearLayout {

    private Path path;
    private RectF rectF;

    private float strokeWidth = 4;
    private int strokeColor = 0XFF000000;
    protected int strokeColorPress = 0XFF000000;
    protected int strokeColorDefault = 0XFF000000;
    private Paint strokePaint;

    private float leftTopRadius = 0;
    private float leftBottomRadius = 0;
    private float rightTopRadius = 0;
    private float rightBottomRadius = 0;
    private float radius = 0;

    private Paint bgPaint;
    protected int bgColorDefault = -1;
    protected int bgColorPress = -1;
    private int bgColor = 0XFFFFFFFF;
    protected Drawable bgDrawableDefault;
    protected Drawable bgDrawablePress;
    private Drawable bgDrawable;
    private boolean clickable;


    public JasonLinerLayout(Context context) {
        super(context);
        init();

    }

    public JasonLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttributes(attrs);
    }

    public JasonLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public JasonLinerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        path = new Path();
        rectF = new RectF();
        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setAntiAlias(true);
        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);

    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray attr = getContext().obtainStyledAttributes(attrs, R.styleable.JasonLinerLayout);
        if (attr == null) {
            return;
        }
        try {
            //默认背景
            Drawable drawable = attr.getDrawable(R.styleable.JasonLinerLayout_ja_bgColorDefault);
            if (drawable instanceof ColorDrawable) {
                bgColorDefault = ((ColorDrawable) drawable).getColor();
            } else {
                bgDrawableDefault = drawable;
            }
            //按压背景
            drawable = attr.getDrawable(R.styleable.JasonLinerLayout_ja_bgColorPress);
            if (drawable instanceof ColorDrawable) {
                bgColorPress = ((ColorDrawable) drawable).getColor();
            } else {
                bgDrawablePress = drawable;
            }

            drawable = attr.getDrawable(R.styleable.JasonLinerLayout_ja_bgColor);
            if (drawable instanceof ColorDrawable) {
                bgColor = ((ColorDrawable) drawable).getColor();
            } else {
                bgDrawable = drawable;
            }

            clickable = attr.getBoolean(R.styleable.JasonLinerLayout_ja_clickable, false);
            if (!clickable) {
                super.setClickable(false);
            } else {
                super.setClickable(true);
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
            leftTopRadius = attr.getDimension(R.styleable.JasonLinerLayout_ja_leftTopRadius, 0);
            leftBottomRadius = attr.getDimension(R.styleable.JasonLinerLayout_ja_leftBottomRadius, 0);
            rightTopRadius = attr.getDimension(R.styleable.JasonLinerLayout_ja_RightTopRadius, 0);
            rightBottomRadius = attr.getDimension(R.styleable.JasonLinerLayout_ja_RightBottomRadius, 0);
            radius = attr.getDimension(R.styleable.JasonLinerLayout_ja_radius, 0);
            if (radius != 0) {
                leftTopRadius = radius;
                leftBottomRadius = radius;
                rightTopRadius = radius;
                rightBottomRadius = radius;
            }
            //边框
            //边框
            strokeWidth = attr.getDimension(R.styleable.JasonLinerLayout_ja_strokeWidth, DensityUtil.px2dp(0));
            if (strokeWidth > DensityUtil.dp2px(7)) {
                strokeWidth = DensityUtil.dp2px(7);
            }
            strokeColorPress = attr.getColor(R.styleable.JasonLinerLayout_ja_strokeColorPress, 0);
            strokeColorDefault = attr.getColor(R.styleable.JasonLinerLayout_ja_strokeColorDefault, 0);
            strokeColor = attr.getColor(R.styleable.JasonLinerLayout_ja_strokeColor, 0);
            if (strokeColorPress == 0) {
                strokeColorPress = strokeColor;
            }
            if (strokeColorDefault == 0) {
                strokeColorDefault = strokeColor;
            }
        } finally {
            attr.recycle();
            invalidate();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);

        switch (event.getAction()) {
            //按下效果
            case MotionEvent.ACTION_DOWN:
                if (clickable) {
                    bgColor = bgColorPress;
                    bgDrawable = bgDrawablePress;
                    strokeColor = strokeColorPress;
                    this.invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                bgColor = bgColorDefault;
                bgDrawable = bgDrawableDefault;
                strokeColor = strokeColorDefault;
                invalidate();
                break;
        }
        return flag;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawBackground(canvas);
        drawStroke(canvas);
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        setBackground(null);
        super.onDraw(canvas);
    }

    private void drawBackground(Canvas canvas) {
        rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CCW);
        canvas.clipPath(path);

        canvas.save();
        bgPaint.setColor(bgColor);
        canvas.drawPath(path, bgPaint);
        canvas.restore();
    }

    private void drawStroke(Canvas canvas) {
        rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CCW);

        if (strokeWidth <= 0) return;
        canvas.save();
        canvas.clipPath(path);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);
        canvas.drawPath(path, strokePaint);
        canvas.restore();
    }


}
