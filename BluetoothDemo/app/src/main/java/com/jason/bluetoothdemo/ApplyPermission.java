package com.jason.bluetoothdemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.util.ArrayList;

/**
 * <p>描述:权限申请
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/12/16 10:20
 */
public class ApplyPermission {

    public static String[] permission = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static boolean apply(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        try {
            //如果操作系统SDK级别在23之上（android6.0），就进行动态权限申请
            if (Build.VERSION.SDK_INT >= 23) {
                ArrayList<String> pmList = new ArrayList<>();
                //获取当前未授权的权限列表
                for (String permi : permission) {
                    int nRet = ContextCompat.checkSelfPermission(activity, permi);
                    if (nRet != PackageManager.PERMISSION_GRANTED) {
                        pmList.add(permi);
                    }
                }
                if (pmList.size() > 0) {
                    String[] sList = pmList.toArray(new String[0]);
                    ActivityCompat.requestPermissions(activity, sList, 1000);
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


}
