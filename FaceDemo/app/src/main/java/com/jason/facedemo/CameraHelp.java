package com.jason.facedemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class CameraHelp {

    SurfaceView surfaceView;
    private ImageReader imageReader = null;
    private Surface imageSurface = null;
    private CameraCaptureSession captureSession = null;
    private CameraDevice cameraDevice = null;
    private CameraManager cameraManager = null;
    private String TAG = "JasonCameraTAG";
    private WeakReference<Activity> activity;
    private ImageAvailableListener imageListener;
    private int imageCount = 0;

    public CameraHelp(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    public void setImageListener(ImageAvailableListener imageListener) {
        this.imageListener = imageListener;
    }

    public void openCamera(int cameraId, Size imageSize) {
        imageReader = ImageReader.newInstance(imageSize.getWidth(), imageSize.getHeight(), ImageFormat.YUV_420_888, 3);
        imageSurface = imageReader.getSurface();
        imageReader.setOnImageAvailableListener(availableListener, null);
        cameraManager = (CameraManager) activity.get().getSystemService(Context.CAMERA_SERVICE);
        try {
            if (ActivityCompat.checkSelfPermission(activity.get(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList.length == 0) {
                Log.e(TAG, "openCamera: 无相机");
                return;
            }
            if (cameraIdList.length < cameraId) {
                Log.e(TAG, "openCamera: 超出相机索引");
                return;
            }
            cameraManager.openCamera(cameraId + "", deviceStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片回调监听器
     */
    private ImageReader.OnImageAvailableListener availableListener = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
            try {
                Image image = reader.acquireLatestImage();
                imageCount++;
                Log.d(TAG, "onImageAvailable: " + imageCount);
                if (imageCount % 3 != 0) {
                    image.close();
                    return;
                }
                imageCount = 0;
                if (image == null) {
                    return;
                }
                Bitmap bitmap = ImageUtil.YUVtoJepg(image);
                image.close();
                if (imageListener != null) {
                    imageListener.onImageAvailable(bitmap);
                }
            } catch (Exception e) {

            }

        }
    };
    /**
     * 设备回调监听器
     */
    private CameraDevice.StateCallback deviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
            try {
                cameraDevice.createCaptureSession(Arrays.asList(imageSurface), sessionStateCallback, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            destroy();
            Log.e(TAG, "onDisconnected: 相机连接失败");
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            destroy();
            Log.e(TAG, "onDisconnected: 相机打开失败");
        }
    };

    private CameraCaptureSession.StateCallback sessionStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
            captureSession = session;
            try {
                CaptureRequest.Builder build = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                build.addTarget(imageSurface);
                build.set(CaptureRequest.JPEG_ORIENTATION, 0);
                CaptureRequest request = build.build();
                captureSession.setRepeatingRequest(request, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            Log.e(TAG, "onConfigureFailed: 配置失败");
        }
    };


    /**
     * 销毁资源
     */
    public void destroy() {
        if (imageReader != null) {
            imageReader.close();

        }
        imageReader = null;
        if (imageSurface != null) {
            imageSurface.release();
        }
        imageSurface = null;
        if (captureSession != null) {
            captureSession.close();
        }
        if (cameraDevice != null) {
            cameraDevice.close();
        }

        cameraManager = null;
        activity = null;
        imageListener = null;
    }


    public interface ImageAvailableListener {
        void onImageAvailable(Bitmap bitmap);
    }
}
