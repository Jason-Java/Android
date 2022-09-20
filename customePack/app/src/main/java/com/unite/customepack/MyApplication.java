package com.unite.customepack;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unite.jasonjar.util.LogUtil;

import java.util.logging.Logger;

/**
 * @author Jason
 * @version 1.0.0
 * date 2022/8/8
 * E-Mail:fjz19971129@163.com
 */
public class MyApplication extends Application {

    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = getContext();
        registerActivity();
    }


    public static Application getContext() {
        return application;
    }

    private void registerActivity() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogUtil.i("创建activity ");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LogUtil.i("结束activity ");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

}
