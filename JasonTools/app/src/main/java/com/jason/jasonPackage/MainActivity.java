package com.jason.jasonPackage;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.jason.jasontools.util.LogUtil;
import com.jason.jasontools.util.StrUtil;
import com.jason.jasonuitools.view.JasonEditText;
import com.jason.jasonuitools.view.JasonLearLayout;
import com.jason.jasonuitools.view.JasonSpinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> {
            JasonEditText editText = findViewById(R.id.editText);
            editText.setJaBgColor(0XFFdddddd);
            editText.setEnabled(false);
            editText.setJaStrokeWidth(0);
        });

        JasonSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{"1", "2", "3"});
        spinner.setAdapter(adapter);
    }
}