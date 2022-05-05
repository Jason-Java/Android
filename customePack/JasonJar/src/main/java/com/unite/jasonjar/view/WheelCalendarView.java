package com.unite.jasonjar.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.unite.jasonjar.R;
import com.unite.jasonjar.popup_window.BasePopupWindow;
import com.unite.jasonjar.wheel.LoopView;
import com.unite.jasonjar.wheel.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

public class WheelCalendarView extends BasePopupWindow {
    private View view;
    private Integer baseYear = 1900;
    private Calendar calendar;
    private ArrayList<String> yearList;
    private ArrayList<String> monthList;
    private ArrayList<String> dayList;
    private ArrayList<String> hoursList;
    private ArrayList<String> minuteList;
    private ArrayList<String> secondList;
    private OnSelectDateListener onSelectDateListener;
    private LoopView yearView;
    private LoopView monthView;
    private LoopView dayView;
    private LoopView hoursView;
    private LoopView minuteView;
    private LoopView secondView;
    private RadioGroup radioGroup;

    public void setOnSelectDateListener(OnSelectDateListener onSelectDateListener) {
        this.onSelectDateListener = onSelectDateListener;
    }

    public WheelCalendarView(Activity activity) {
        super(activity);
        yearView = view.findViewById(R.id.yearView);
        monthView = view.findViewById(R.id.monthView);
        dayView = view.findViewById(R.id.dayView);
        hoursView = view.findViewById(R.id.hoursView);
        minuteView = view.findViewById(R.id.minuteView);
        secondView = view.findViewById(R.id.secondView);
        radioGroup = view.findViewById(R.id.radioGroup);

        iniEvent();
        initData();
    }

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.jason_calendar_view, null, false);
        return view;
    }

    private void initData() {
        calendar = Calendar.getInstance();
        initYear();
        initMonth();
        initHours();
        initMinute();
        initSecond();
    }

    //初始化年份
    private void initYear() {
        yearList = new ArrayList<>();
        for (int i = 1900; i < 1900 + 300; i++) {
            yearList.add(i + "");
        }
        int currentYear = calendar.get(Calendar.YEAR);
        yearView.setItems(yearList);
        yearView.setInitPosition(currentYear - baseYear);
    }

    //初始化月份
    private void initMonth() {
        monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i + "");
        }
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        monthView.setItems(monthList);
        monthView.setInitPosition(currentMonth);
        initDayLoopView(currentDay - 1);
    }

    //初始化日
    private void initDayLoopView(int initPosition) {
        int selectItemYear = yearView.getSelectedItem();
        int selectItemMonth = monthView.getSelectedItem();
        String year = yearList.get(selectItemYear);
        String month = monthList.get(selectItemMonth);
        int dayCount = maxDayOfMonth(year, month);
        dayList = new ArrayList<>();
        for (int i = 1; i <= dayCount; i++) {
            dayList.add(i + "");
        }
        //设置原始数据
        dayView.setItems(dayList);
        //设置初始位置
        //dayView.setInitPosition(initPosition);
        dayView.setCurrentPosition(initPosition);
        //dayView.invalidate();
    }

    //初始化小时
    private void initHours() {
        hoursList = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            hoursList.add(i + "");
        }
        hoursView.setItems(hoursList);
        hoursView.setInitPosition(calendar.get(Calendar.HOUR_OF_DAY) );
    }

    //初始化分钟
    private void initMinute() {
        minuteList = new ArrayList<>();
        for (int i = 0; i <= 60; i++) {
            minuteList.add(i + "");
        }
        minuteView.setItems(minuteList);
        minuteView.setInitPosition(calendar.get(Calendar.MINUTE) );
    }

    //初始化秒
    private void initSecond() {
        secondList = new ArrayList<>();
        for (int i = 0; i <= 60; i++) {
            secondList.add(i + "");
        }
        secondView.setItems(secondList);
        secondView.setInitPosition(calendar.get(Calendar.SECOND) );
    }

    private void iniEvent() {
        view.findViewById(R.id.llParen).setOnClickListener(v -> {
            dismiss();
        });

        yearView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                initDayLoopView(0);
                getSelectDate();
            }
        });

        monthView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                initDayLoopView(0);
                getSelectDate();
            }
        });

        dayView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                getSelectDate();
            }
        });

        hoursView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                getSelectDate();
            }
        });

        minuteView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                getSelectDate();
            }
        });

        secondView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                getSelectDate();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioDate) {
                    yearView.setVisibility(View.VISIBLE);
                    monthView.setVisibility(View.VISIBLE);
                    dayView.setVisibility(View.VISIBLE);
                    hoursView.setVisibility(View.GONE);
                    minuteView.setVisibility(View.GONE);
                    secondView.setVisibility(View.GONE);
                }
                if (checkedId == R.id.radioTime) {
                    yearView.setVisibility(View.GONE);
                    monthView.setVisibility(View.GONE);
                    dayView.setVisibility(View.GONE);
                    hoursView.setVisibility(View.VISIBLE);
                    minuteView.setVisibility(View.VISIBLE);
                    secondView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //获取一个月中有多少天
    private int maxDayOfMonth(String year, String month) {
        calendar.set(Integer.valueOf(year), Integer.valueOf(month) - 1, 01);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void getSelectDate() {
        int selectItemYear = yearView.getSelectedItem();
        int selectItemMonth = monthView.getSelectedItem();
        int selectItemDay = dayView.getSelectedItem();

        int selectItemHours = hoursView.getSelectedItem();
        int selectItemMinute = minuteView.getSelectedItem();
        int selectItemSecond = secondView.getSelectedItem();

        String year = yearList.get(selectItemYear);
        String month = monthList.get(selectItemMonth);
        String day = dayList.get(selectItemDay);

        String hours = hoursList.get(selectItemHours);
        String minute = minuteList.get(selectItemMinute);
        String second = secondList.get(selectItemSecond);

        if (onSelectDateListener != null) {
            onSelectDateListener.onDate(year, month, day, hours, minute, second);
        }
    }

    public interface   OnSelectDateListener {
        void  onDate(String year, String month, String day, String hours, String minute, String second);
    }


}
