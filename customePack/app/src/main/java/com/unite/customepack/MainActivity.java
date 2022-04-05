package com.unite.customepack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.unite.jasonjar.view.ErrorView;
import com.unite.jasonjar.view.WaitingView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.LogUtil;


public class MainActivity extends AppCompatActivity {
    SuperLoadingProgress mSuperLoadingProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /**
         * 在进行网络请求的时候不要忘记在AndroidManifest.xml中添加网络权限
         * <uses-permission android:name="android.permission.INTERNET"/>
         * 由于android不允许在UI线程中进行同步网络请求,所以我们用一个线程发起网络请求
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response response = client.newCall(request).execute();
//                    LogUtil.e("我是同步请求的内容 " + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()//.post(RequestBody)post请求
                .build();
        try {
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.e("我是异步请求的错误 " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    LogUtil.e("我是异步请求的内容 " + response.body().string());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        findViewById(R.id.btn2).setOnClickListener(v -> {

            WaitPopup waitPopup = new WaitPopup(this);
            waitPopup.showPopWindow();
        });


    }
}