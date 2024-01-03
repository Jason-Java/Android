package com.jason.jasonuitools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.jason.jasonuitools.util.DensityUtil;
import com.jason.jasonuitools.R;

import java.lang.reflect.Field;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年12月04日
 */
public class JasonLearLayout extends LinearLayout {
    private Paint paint;
    private Path path;

    //背景相关
    protected int bgColorPress;
    protected int bgColor;
    private int bgDefaultColor = 0xFFFFFFFF;

    //圆角相关
    protected float leftTopRadius = 0;
    protected float leftBottomRadius = 0;
    protected float rightTopRadius = 0;
    protected float rightBottomRadius = 0;
    protected float radius = 0;

    //边框
    protected float strokeWidth = 0;
    protected float strokeLeftWidth = 0;
    protected float strokeRightWidth = 0;
    protected float strokeTopWidth = 0;
    protected float strokeBottomWidth = 0;
    protected int strokeColor;
    protected int strokeColorPress;
    protected int strokeDefaultColor = 0xFFFFFFFF;

    public JasonLearLayout(Context context) {
        super(context);
    }

    public JasonLearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public JasonLearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public JasonLearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    protected void init(AttributeSet attributeSet) {
        TypedArray attr = getContext().obtainStyledAttributes(attributeSet, R.styleable.JasonLearLayout);
        try {
            //圆角相关
            leftTopRadius = attr.getDimension(R.styleable.JasonLearLayout_ja_leftBottomRadius, 0);
            leftBottomRadius = attr.getDimension(R.styleable.JasonLearLayout_ja_leftBottomRadius, 0);
            rightTopRadius = attr.getDimension(R.styleable.JasonLearLayout_ja_RightTopRadius, 0);
            rightBottomRadius = attr.getDimension(R.styleable.JasonLearLayout_ja_RightBottomRadius, 0);
            radius = attr.getDimension(R.styleable.JasonLearLayout_ja_radius, 0);
            if (radius != 0) {
                leftTopRadius = radius;
                leftBottomRadius = radius;
                rightTopRadius = radius;
                rightBottomRadius = radius;
            }
            //边框
            strokeWidth = attr.getDimension(R.styleable.JasonLearLayout_ja_strokeWidth, DensityUtil.px2dp(0));
            if (strokeWidth > DensityUtil.dp2px(7)) {
                strokeWidth = DensityUtil.dp2px(7);
            }
            strokeLeftWidth = attr.getDimension(R.styleable.JasonLearLayout_ja_strokeLeftWidth, strokeWidth);
            strokeRightWidth = attr.getDimension(R.styleable.JasonLearLayout_ja_strokeRightWidth, strokeWidth);
            strokeTopWidth = attr.getDimension(R.styleable.JasonLearLayout_ja_strokeTopWidth, strokeWidth);
            strokeBottomWidth = attr.getDimension(R.styleable.JasonLearLayout_ja_strokeBottomWidth, strokeWidth);

            strokeColor = attr.getColor(R.styleable.JasonLearLayout_ja_strokeColor, strokeDefaultColor);
            strokeColorPress = attr.getColor(R.styleable.JasonLearLayout_ja_strokeColorPress, strokeColor);
            strokeDefaultColor = strokeColor;

            // 背景颜色相关
            bgColor = attr.getInt(R.styleable.JasonLearLayout_ja_bgColor, 0xFFFFFFFF);
            bgColorPress = attr.getInt(R.styleable.JasonLearLayout_ja_bgColorPress, bgColor);
            bgDefaultColor = bgColor;

        } finally {
            attr.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled())
            return !isEnabled();
        if (!isClickable()) {
            return !isClickable();
        }
        float x = event.getX();
        float y = event.getY();
        int childCount = this.getChildCount();

        boolean flag = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bgDefaultColor = bgColorPress;
            strokeDefaultColor = strokeColorPress;
            for (int i = 0; i < childCount; i++) {
                View childView = this.getChildAt(i);
                int left = childView.getLeft();
                int right = childView.getRight();
                int top = childView.getTop();
                int bottom = childView.getBottom();
                if (x >= left && x <= right && y >= top && y <= bottom) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {
            bgDefaultColor = bgColor;
            strokeDefaultColor = strokeColor;
        }
        this.invalidate();
        return flag;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (paint == null) {
            paint = new Paint();
        }
        if (path == null) {
            path = new Path();
        }
        if (strokeLeftWidth > 0 || strokeRightWidth > 0 || strokeTopWidth > 0 || strokeBottomWidth > 0) {
            drawStroke(canvas);
        }
        drawBackGround(canvas);

        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Field mBackground = getSpecialField(this.getClass(), "mBackground");
        mBackground.setAccessible(true);
        try {
            mBackground.set(this, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        super.onDraw(canvas);
    }

    /**
     * 绘制边框
     *
     * @param canvas
     */
    private void drawStroke(Canvas canvas) {
        path.reset();
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        RectF rectF = new RectF(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        // 绘制边框
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(strokeDefaultColor);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制背景色
     *
     * @param canvas
     */
    private void drawBackGround(Canvas canvas) {
        path.reset();
        // 绘制背景色
        RectF rectF = new RectF(strokeLeftWidth, strokeTopWidth, this.getMeasuredWidth() - strokeRightWidth, this.getMeasuredHeight() - strokeBottomWidth);
        float[] outRadio = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
        path.addRoundRect(rectF, outRadio, Path.Direction.CW);
        //绘制背景
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0);
        paint.setColor(bgDefaultColor);
        canvas.drawPath(path, paint);
    }

    /**
     * 设置默认背景色和按压背景色
     *
     * @param color
     */
    public void setJaBgColor(@ColorInt int color) {
        this.bgColor = color;
        this.bgColorPress = color;
        this.bgDefaultColor = color;
        this.invalidate();
    }

    /**
     * 设置按压效果背景色
     *
     * @param color
     */
    public void setJaBgColorPress(int color) {
        this.bgColorPress = color;
        this.bgDefaultColor = color;
        this.invalidate();
    }

    /**
     * 设置默认背景色
     *
     * @param color
     */
    public void setJaBgDefaultColor(int color) {
        this.bgColor = color;
        this.bgDefaultColor = color;
        this.invalidate();
    }

    /**
     * 设置边框默认颜色和按压颜色
     *
     * @param color
     */
    public void setStrokeColor(int color) {
        this.strokeColor = color;
        this.strokeColorPress = color;
        this.strokeDefaultColor = color;
        this.invalidate();
    }

    /**
     * 设置边框按压颜色
     *
     * @param color
     */
    public void setStrokeColorPress(int color) {
        this.strokeColorPress = color;
        this.strokeDefaultColor = color;
        this.invalidate();
    }

    /**
     * 设置边框默认颜色
     *
     * @param color
     */
    public void setStrokeDefaultColor(int color) {
        this.strokeColor = color;
        this.strokeDefaultColor = color;
        this.invalidate();
    }

    /**
     * 同时设置左、右、上、下边框
     *
     * @param width
     */
    public void setJaStrokeWidth(float width) {
        this.strokeWidth = width;
        this.strokeLeftWidth = width;
        this.strokeRightWidth = width;
        this.strokeTopWidth = width;
        this.strokeBottomWidth = width;
        this.invalidate();
    }

    /**
     * 设置左边边框宽度
     *
     * @param width
     */
    public void setJaStrokeLeftWidth(float width) {
        this.strokeLeftWidth = width;
        this.invalidate();
    }

    /**
     * 设置右边边框宽度
     *
     * @param width
     */
    public void setJaStrokeRightWidth(float width) {
        this.strokeRightWidth = width;
        this.invalidate();
    }

    /**
     * 设置上边边框宽度
     *
     * @param width
     */
    public void setJaStrokeTopWidth(float width) {
        this.strokeTopWidth = width;
        this.invalidate();
    }

    /**
     * 设置底边边框宽度
     *
     * @param width
     */
    public void setJaStrokeBottomWidth(float width) {
        this.strokeBottomWidth = width;
        this.invalidate();
    }

    /**
     * 利用反射功能，获取指定的字段，如果此类中没有找到则从父类中进行获取
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public Field getSpecialField(Class clazz, String fieldName) {

        while (clazz != null && clazz != Object.class) {
            Field declaredField = null;
            try {
                declaredField = clazz.getDeclaredField(fieldName);
            } catch (Exception exception) {
            }
            if (declaredField == null) {
                clazz = clazz.getSuperclass();
            } else {
                return declaredField;
            }
        }
        return null;
    }
}
