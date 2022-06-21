package com.unite.jasonjar.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import util.StringUtil;

/**
 * 日历控件
 */
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
                public void onDate(String year, String month, String day, String hours, String minute, String second) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(year);
                    builder.append("-");
                    builder.append(month);
                    builder.append("-");
                    builder.append(day);
                    if (!StringUtil.equals(hours, "0") || !StringUtil.equals(minute, "0") || !StringUtil.equals(second, "0")) {
                        builder.append(" ");
                        builder.append(hours);
                        builder.append(":");
                        builder.append(minute);
                        builder.append(":");
                        builder.append(second);
                    }
                    JasonCalendarView.this.setText(builder.toString());
                }
            });
            wheelCalendarView.showPopWindow();
        });
    }
}
