package com.jason.usb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月15日
 */
public class CreateBitmap {
    public static Bitmap createBitmap(String table) {

        int width = table.length() * 35;
        int height = 45;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.WHITE);
        Paint mPaint = new Paint();
        mPaint.setTextSize(26);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0.1f);

        // Always Black

        float xPoint = 15, yPoint = 0;
        yPoint += 32;
        canvas.drawText(table, xPoint, yPoint, mPaint);

        //定义矩形框的位置
        RectF rectF = new RectF(0, 0, width, height);

        //设置画笔模式为空心
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制矩形框
        //canvas.drawRect(rectF, mPaint);
        //绘制圆角矩形
        canvas.drawRoundRect(rectF, height/2, height/2, mPaint);
        canvas.save();
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        bitmap= Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }
}
