package com.unite.customepack;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        WaitDialog waitDialog1 = new WaitDialog();


        findViewById(R.id.start).setOnClickListener(v -> {
            waitDialog1.show(fragmentManager, "1");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    waitDialog1.dismiss();
                }
            }, 3000);
        });

        JasonButton jasonButton = findViewById(R.id.jason);
        jasonButton.setBgColorDefault(Color.RED);
        jasonButton.setBgColorPress(Color.GREEN);
        jasonButton.setStrokeWidth(1);
        jasonButton.setLeftTopRadius(25);
        jasonButton.setTextSize(30);

    }


}