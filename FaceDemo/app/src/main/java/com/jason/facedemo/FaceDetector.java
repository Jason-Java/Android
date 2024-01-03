package com.jason.facedemo;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class FaceDetector {
    private static final String TAG = "YCL";

    /**
     * 检测人脸
     * @param faceBitmap 人脸图片
     * @param maxFaceNum 最大检测人脸数
     * @return
     */

    public static   Rect[] detector(Bitmap faceBitmap, int maxFaceNum){
        if (maxFaceNum < 1) {
            Log.e(TAG, "faceDetector: 识别人脸数必须大于1");
            return null;
        }

        Bitmap.Config config = faceBitmap.getConfig();
        if (!config.equals(Bitmap.Config.RGB_565)) {
            faceBitmap  = faceBitmap.copy(Bitmap.Config.RGB_565, true);
        }
        android.media.FaceDetector faceDetector = new android.media.FaceDetector(faceBitmap.getWidth(), faceBitmap.getHeight(), maxFaceNum);
        android.media.FaceDetector.Face[] faces1 = new android.media.FaceDetector.Face[maxFaceNum];
        int faceNum = faceDetector.findFaces(faceBitmap, faces1);
        if (faceNum == 0 || faces1.length < 1) {
            return null;
        }
        Rect[] rects = new Rect[faceNum];
        float eyesDistance = 0f;
        for (int i = 0; i < faceNum; i++) {
            float confidence = faces1[i].confidence();
            Log.i(TAG, "人脸确定值 " + confidence);
            if (confidence < 0.5) {
                continue;
            }
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
}
