package com.jason.facedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "YCL";
    private CameraHelp cameraHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraHelp = new CameraHelp(this);
        cameraHelp.openCamera(0, new Size(1024, 1024));
        cameraHelp.setImageListener(new CameraHelp.ImageAvailableListener() {
            @Override
            public void onImageAvailable(final Bitmap bitmap) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap1 = bitmap;
                        Rect[] rects = FaceDetector.detector(bitmap1,1);
                        if (rects != null && rects.length > 0) {
                            bitmap1 = bitmap1.copy(Bitmap.Config.RGB_565, true);
                            bitmap1 = ImageUtil.bitmapDrawRect(bitmap1, rects[0]);
                        }
                        showBitmap(bitmap1);
                        bitmap1.recycle();
                    }
                }).start();
            }
        });
    }

    /**
     * 在bitmap上面绘制矩形显示出来
     *
     * @param faceBitmap
     */
    public void showBitmap(Bitmap faceBitmap) {
        SurfaceView surfaceView = findViewById(R.id.image);
        SurfaceHolder holder = surfaceView.getHolder();
        Canvas canvas1 = holder.lockCanvas();
        Rect rect1 = new Rect();
        this.getWindowManager().getDefaultDisplay().getRectSize(rect1);
        canvas1.drawBitmap(faceBitmap, null, rect1, new Paint());
        holder.unlockCanvasAndPost(canvas1);
    }

    @Override
    protected void onDestroy() {
        cameraHelp.destroy();
        super.onDestroy();
    }
}