package com.jason.jnitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.jason.jnitest.databinding.ActivityMainBinding;

import java.io.File;

import android_serialport_api.SerialPort;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {
            DeriveKeyBoxSp.getInstance().open("/dev/ttyS1", 9600, 0);
        } catch (Exception e) {

        }

    }

}