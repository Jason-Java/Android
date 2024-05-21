package com.jason.hisensedemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.openLock).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LockController.init();
                }
            }).start();
        });
        findViewById(R.id.getTemp).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TempController.init();
                }
            }).start();
        });
    }

    private void sendUnLock() {


    }
}