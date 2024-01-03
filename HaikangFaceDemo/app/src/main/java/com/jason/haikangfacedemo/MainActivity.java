package com.jason.haikangfacedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.io.SocketConfig;

import java.util.concurrent.TimeUnit;

import om.ok2c.hc.android.HttpClientActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("admin", new char[]{'u', 'n', 'i', 't', 'e', '1', '2', '3', '4', '5', '6'});

        PoolingHttpClientConnectionManagerBuilder poolingHttpClientConnectionManagerBuilder = PoolingHttpClientConnectionManagerBuilder
                .create()
                .setConnectionTimeToLive(1, TimeUnit.MINUTES)
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoTimeout(5000)
                        .build())
                .build();

        HttpClientActivity httpClientActivity = new HttpClientActivity() {
            @Override
            protected void onDestroy() {
                super.onDestroy();
            }

            @Override
            protected void onPause() {
                super.onPause();
            }

            @Override
            protected void onResume() {
                super.onResume();
            }

            @Override
            protected void onStop() {
                super.onStop();
            }
        };

    }
}