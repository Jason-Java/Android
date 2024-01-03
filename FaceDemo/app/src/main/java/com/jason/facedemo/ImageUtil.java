package com.jason.facedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ImageUtil {

    /**
     * YUV格式图像转换成JEPG格式
     *
     * @param image
     * @return
     */
    public static Bitmap YUVtoJepg(Image image) {
        ByteBuffer[] buffers = new ByteBuffer[3];
        int[] number_buffers = new int[3];
        byte[][] bytes_plans = new byte[3][];
        int planes = image.getPlanes().length;
        for (int i = 0; i < planes; i++) {
            buffers[i] = image.getPlanes()[i].getBuffer();
            number_buffers[i] = buffers[i].remaining();
            bytes_plans[i] = new byte[number_buffers[i]];
            buffers[i].get(bytes_plans[i]);
        }

        Bitmap bitmap = null;
        if (planes > 1) {
            byte[] yuv_buffer = byteMergerAll(bytes_plans[0], bytes_plans[2]);
            //yuv420转换为jpeg格式
            if (yuv_buffer != null) {
                bitmap = YUVtoJepg(yuv_buffer, image.getWidth(), image.getHeight(), 100);
            }
        } else {
            bitmap = BitmapFactory.decodeByteArray(bytes_plans[0], 0, number_buffers[0]);
        }
        return bitmap;
    }

    /**
     * 合并图像通道
     *
     * @param values
     * @return
     */
    private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

    /**
     * YUV格式图像转换成JEPG格式
     *
     * @param data
     * @param width   图片的宽
     * @param hight   图片的高
     * @param quality 转换质量
     * @return
     */
    public static Bitmap YUVtoJepg(byte[] data, int width, int hight, int quality) {
        try {
            YuvImage image_jpeg =
                    new YuvImage(data, ImageFormat.NV21, width, hight, null);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image_jpeg.compressToJpeg(new Rect(0, 0, width, hight), quality, stream);
            Bitmap jpeg_bitmap =
                    BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.toByteArray().length);
            return jpeg_bitmap;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /**
     * 裁剪Bitmap
     *
     * @param bitmap 原图像
     * @param rect   裁剪框
     * @return 裁剪过后的bitmap
     */
    public static Bitmap croppingBitmap(Bitmap bitmap, Rect rect) {
        int x = rect.left;
        int y = rect.top;
        int weight = rect.right - x;
        int height = rect.bottom - y;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x + weight > bitmap.getWidth()) {
            x = 0;
            weight = bitmap.getWidth();
        }
        if (y + height > bitmap.getHeight()) {
            y = 0;
            height = bitmap.getHeight();
        }
        return Bitmap.createBitmap(bitmap, x, y, weight, height, null, false);
    }

    /**
     * 在bitmap上面绘制矩形显示出来
     *
     * @param bitmap
     * @param rect
     */
    public static Bitmap bitmapDrawRect(Bitmap bitmap, Rect rect) {
        if (rect == null) {
            return bitmap;
        }
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//不填充
        paint.setStrokeWidth(2); //线的宽度
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(rect, paint);
        return bitmap;
    }

    /**
     * 图片旋转
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        //图片旋转90度
        Matrix m = new Matrix();
        m.setRotate(-90, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    }

    public static Bitmap RGB565toBitmap(Image image)
    {
        ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
       /* int length = byteBuffer.remaining();
        byte[] bytes = new byte[length];
        byteBuffer.get(bytes);*/

        Bitmap bitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.RGB_565);
        bitmap.copyPixelsFromBuffer(byteBuffer);
        return bitmap;
    }

    /**
     * bitmap转Base64
     * @param bitmap
     * @return
     */
    public String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
