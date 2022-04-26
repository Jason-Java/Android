package com.unite.jasonjar.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class JasonCalendarView extends androidx.appcompat.widget.AppCompatTextView {

    public JasonCalendarView(@NonNull Context context) {
        super(context);
        initData();
        initEvent(context);
    }

    public JasonCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
        initEvent(context);
    }

    public JasonCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initData() {

    }

    private void initEvent(Context context) {
        JasonCalendarView.this.setOnClickListener(v -> {
            WheelCalendarView wheelCalendarView = new WheelCalendarView((Activity) context);
            wheelCalendarView.setOnSelectDateListener(new WheelCalendarView.OnSelectDateListener() {
                @Override
                public void onDate(String value) {
                    JasonCalendarView.this.setText(value);
                }
            });
            wheelCalendarView.showPopWindow();
        });
    }
}
