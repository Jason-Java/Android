package com.unite.jasonjar.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StrokeDrawable extends Drawable {
    private Paint paint;
    private int height;
    private int width;
    Bitmap bitmap;


    public StrokeDrawable() {
        paint = new Paint();
        paint.setColor(0XFF000000);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect rect = getBounds();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);

        canvas.drawBitmap(bitmap, rect.right - 20 - bitmap.getWidth(), rect.height() / 2 + bitmap.getHeight() / 2, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


}
