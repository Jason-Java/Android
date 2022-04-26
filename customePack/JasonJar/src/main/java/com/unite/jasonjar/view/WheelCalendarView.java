package com.unite.jasonjar.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

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
    private OnSelectDateListener onSelectDateListener;
    private LoopView yearView;
    private LoopView monthView;
    private LoopView dayView;


    public void setOnSelectDateListener(OnSelectDateListener onSelectDateListener) {
        this.onSelectDateListener = onSelectDateListener;
    }


    public WheelCalendarView(Activity activity) {
        super(activity);
        yearView = view.findViewById(R.id.yearView);
        monthView = view.findViewById(R.id.monthView);
        dayView = view.findViewById(R.id.dayView);

        iniEvent();
        initData();
    }

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.jason_calendar_view, null, false);
        return view;
    }

    private void initData() {
        yearList = new ArrayList<>();
        for (int i = 1900; i < 1900 + 300; i++) {
            yearList.add(i + "");
        }
        monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i + "");
        }

        calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        yearView.setItems(yearList);
        yearView.setInitPosition(currentYear - baseYear);

        monthView.setItems(monthList);
        monthView.setInitPosition(currentMonth);
        initDayLoopView(currentDay - 1);
    }

    private void iniEvent() {
        view.findViewById(R.id.llParen).setOnClickListener(v->{
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
    }

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

    //获取一个月中有多少天
    private int maxDayOfMonth(String year, String month) {
        calendar.set(Integer.valueOf(year), Integer.valueOf(month) - 1, 01);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void getSelectDate() {
        int selectItemYear = yearView.getSelectedItem();
        int selectItemMonth = monthView.getSelectedItem();
        int selectItemDay = dayView.getSelectedItem();
        String year = yearList.get(selectItemYear);
        String month = monthList.get(selectItemMonth);
        String day = dayList.get(selectItemDay);
        if (onSelectDateListener != null) {
            onSelectDateListener.onDate(year + "-" + month + "-" + day);
        }
    }

    public interface OnSelectDateListener {
        void onDate(String value);
    }
}
