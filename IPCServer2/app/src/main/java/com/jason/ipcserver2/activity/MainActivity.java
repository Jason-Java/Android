package com.jason.ipcserver2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jason.ipcserver2.R;
import com.jason.ipcserver2.server.MyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("================"+ this.getPackageName());
        findViewById(R.id.start).setOnClickListener(v -> {
            ;
            Intent intent = new Intent(MainActivity.this, MyService.class);
            startService(intent);

        });
        findViewById(R.id.stop).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyService.class);
            stopService(intent);
        });
    }
}