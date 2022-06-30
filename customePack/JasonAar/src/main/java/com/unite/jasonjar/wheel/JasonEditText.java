package com.unite.jasonjar.wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Jason
 * @version 1.0.0
 * @data 2022/6/30
 */
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
    protected void dispatchDraw(Canvas canvas) {
        setBackground(null);


        super.dispatchDraw(canvas);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        super.onDraw(canvas);
    }

    private void drawBackground(Canvas canvas) {
        canvas.save();
        RectF rectF = new RectF();
        rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        float[] outRadio = {20, 20, 20, 20, 20, 20, 20, 20};
        Path path = new Path();
        path.addRoundRect(rectF, outRadio, Path.Direction.CCW);

        canvas.save();
        Paint bgPaint = new Paint();
        /*bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(0XFF123456);
        canvas.drawPath(path, bgPaint);
        canvas.restore();*/


        canvas.clipPath(path);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setColor(0XFF00FF7F);
        bgPaint.setStrokeWidth(5);
        canvas.drawPath(path, bgPaint);
        canvas.restore();
    }
}
