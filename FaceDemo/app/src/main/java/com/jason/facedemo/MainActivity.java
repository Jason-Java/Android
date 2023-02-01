package com.jason.facedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.FaceDetector;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String TAG = "YCL";


    private CameraHelp cameraHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraHelp = new CameraHelp(this);
        cameraHelp.openCamera(1, new Size(480, 960));
        cameraHelp.setImageListener(new CameraHelp.ImageAvailableListener() {
            @Override
            public void onImageAvailable(Bitmap bitmap) {
                bitmapDrawRect(bitmap, null);
            }
        });
    }



    /**
     * 在bitmap上面绘制矩形显示出来
     *
     * @param faceBitmap
     * @param rect
     */
    public void bitmapDrawRect(Bitmap faceBitmap, Rect rect) {
        ImageView imageView = findViewById(R.id.image);
        if (rect == null) {
            imageView.setImageBitmap(faceBitmap);
            imageView.invalidate();
            return;
        }
        //faceBitmap=croppingBitmap(faceBitmap,rect);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//不填充
        paint.setStrokeWidth(2); //线的宽度
        Canvas canvas = new Canvas(faceBitmap);
        canvas.drawRect(rect, paint);
        imageView.setImageBitmap(faceBitmap);
        imageView.invalidate();
    }


    /**
     * 检测人脸
     *
     * @param faceBitmap 人脸图像
     */
    public Rect[] faceDetector(Bitmap faceBitmap) {
        Bitmap bitmap = faceBitmap.copy(Bitmap.Config.RGB_565, true);
        int maxFaceNume = 3;
        FaceDetector faceDetector = new FaceDetector(bitmap.getWidth(), bitmap.getHeight(), maxFaceNume);
        FaceDetector.Face[] faces1 = new FaceDetector.Face[maxFaceNume];
        int faceNum = faceDetector.findFaces(bitmap, faces1);
        if (faceNum == 0) {
            return null;
        }
        if (faces1 == null || faces1.length < 1) {
            return null;
        }
        Rect[] rects = new Rect[faceNum];
        float eyesDistance = 0f;
        for (int i = 0; i < faceNum; i++) {
            PointF eyeMidPoint = new PointF();//两眼的中点距离
            faces1[i].getMidPoint(eyeMidPoint);//两眼之间的距离
            eyesDistance = faces1[i].eyesDistance();
            float distanceX = 1.5f * eyesDistance;
            float distanceTop = 1.6f * eyesDistance;
            float distanceBottom = 2.2f * eyesDistance;
            float left = eyeMidPoint.x - distanceX;
            float right = eyeMidPoint.x + distanceX;
            float top = eyeMidPoint.y - distanceTop;
            float bottom = eyeMidPoint.y + distanceBottom;
            Rect rect = new Rect();
            rect.set((int) left, (int) top, (int) right, (int) bottom);
            rects[i] = rect;
        }
        return rects;
    }


    @Override
    protected void onDestroy () {
        cameraHelp.destroy();
        super.onDestroy();
    }
}