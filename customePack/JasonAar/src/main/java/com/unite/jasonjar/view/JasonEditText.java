package com.unite.jasonjar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class JasonEditText extends androidx.appcompat.widget.AppCompatEditText {

    public JasonEditText(@NonNull Context context) {
        super(context);
    }

    public JasonEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JasonEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawBackground(canvas);
        setBackground(null);
    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    protected void drawBackground(Canvas canvas) {
        Paint bgPaint = new Paint();

        //自定义路径
        Path path = new Path();
        RectF rectF = new RectF(0,  0, super.getMeasuredWidth(), super.getMeasuredHeight());
        float[] outRadio = {20, 20, 20, 20, 20, 20, 20, 20};
        path.addRoundRect(rectF, outRadio, Path.Direction.CCW);

        //设置画笔颜色
        bgPaint.setColor(0XFF123456);
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,bgPaint);


        bgPaint.setColor(0XFF098765);
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(2);
        canvas.drawPath(path, bgPaint);
    }


}
