package com.unite.jasonjar.view;

import android.app.Activity;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unite.jasonjar.R;
import com.unite.jasonjar.popup_window.BasePopupWindow;
import com.unite.jasonjar.wheel.LoopView;
import com.unite.jasonjar.wheel.OnItemSelectedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JasonAddressView extends BasePopupWindow {
    private View view;

    private ArrayList<String> provinceList=new ArrayList<>();
    private ArrayList<String> cityList=new ArrayList<>();
    private ArrayList<String> areaList=new ArrayList<>();
    private OnSelectDateListener onSelectDateListener;
    private LoopView provinceView;
    private LoopView cityView;
    private LoopView areaView;
    private JSONArray provinceJsonArray;
    private JSONArray cityJsonArray;
    private JSONArray areaJsonArray;


    public void setOnSelectDateListener(OnSelectDateListener onSelectDateListener) {
        this.onSelectDateListener = onSelectDateListener;
    }

    public JasonAddressView(Activity activity) {
        super(activity);
        provinceView = view.findViewById(R.id.provinceView);
        cityView = view.findViewById(R.id.cityView);
        areaView = view.findViewById(R.id.areaView);

        iniEvent();
        initData();
    }

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.jason_address_view, null, false);
        return view;
    }

    private void initData() {


        initProvince();
        initCity(0);
        initArea(0);


    }

    private void iniEvent() {
        view.findViewById(R.id.llParen).setOnClickListener(v -> {
            dismiss();
        });

        provinceView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                initCity(index);
                getSelectDate();
            }
        });
        cityView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                initArea(index);
                getSelectDate();
            }
        });
        areaView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                getSelectDate();
            }
        });
    }

    //初始化省份
    private void initProvince() {
        provinceJsonArray = new JSONArray();
        provinceJsonArray = JSON.parseArray(parseJson("jasonaddres.json"));
        provinceList = new ArrayList<>();
        for (int i = 0; i < provinceJsonArray.size(); i++) {
            JSONObject jsonObject = provinceJsonArray.getJSONObject(i);
            provinceList.add(jsonObject.getString("name"));
        }
        provinceView.setItems(provinceList);
        provinceView.setCurrentPosition(0);
    }

    //初始化城市
    private void initCity(int parentIndex) {
        cityJsonArray = provinceJsonArray.getJSONObject(parentIndex).getJSONArray("city");
        if (cityJsonArray.size() <= 1) {
            cityList = new ArrayList<>();
            JSONArray areaJsonArray = cityJsonArray.getJSONObject(0).getJSONArray("area");
            for (int i = 0; i < areaJsonArray.size(); i++) {
                cityList.add(areaJsonArray.getString(i));
            }
            cityView.setItems(cityList);
            cityView.setCurrentPosition(0);
            return;
        }
        cityList = new ArrayList<>();
        for (int i = 0; i < cityJsonArray.size(); i++) {
            cityList.add(cityJsonArray.getJSONObject(i).getString("name"));
        }
        cityView.setItems(cityList);
        cityView.setCurrentPosition(0);
    }

    //初始化地区
    private void initArea(int parentIndex) {
        if(cityJsonArray.size()<=1) return;
        areaJsonArray = cityJsonArray.getJSONObject(parentIndex).getJSONArray("area");
        areaList = new ArrayList<>();
        for (int i = 0; i < areaJsonArray.size(); i++) {
            areaList.add(areaJsonArray.getString(i));
        }
        if(areaList.size()<=1)
        {
            areaView.setItems(new ArrayList<>());
            areaView.invalidate();
            return;
        }
        areaView.setItems(areaList);
        areaView.setCurrentPosition(0);
    }


    private void getSelectDate() {
        int selectItemYear = provinceView.getSelectedItem();
        int selectItemMonth = cityView.getSelectedItem();
        int selectItemDay = areaView.getSelectedItem();
        String province = provinceList.get(selectItemYear);
        String city = cityList.get(selectItemMonth);
        String area = "";
        if(areaList.size()>1)
        {
            area = areaList.get(selectItemDay);
        }
        if (onSelectDateListener != null) {
            onSelectDateListener.onDate(province, city, area);
        }
    }


    public interface OnSelectDateListener {
        void onDate(String province, String city, String area);
    }

    public String parseJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = activity.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();


    }
}
