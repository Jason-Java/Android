package com.unite.jasonjar.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected ArrayList<T> item = new ArrayList<>();
    protected Activity activity;
    protected int layoutId;

    public BaseListViewAdapter(Activity activity, int layoutId) {
        this.activity = activity;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public T getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(activity, position, convertView, parent, layoutId);


        convert(viewHolder, getItem(position), position);

        return viewHolder.getConvertView();
    }

    protected abstract void convert(ViewHolder holder, T item, int position);

    public void setItem(ArrayList<T> item) {
        if (item != null && item.size() > 0) {
            this.item.clear();
            this.item.addAll(item);
            notifyDataSetChanged();
        }
    }

    public void addItem(@NonNull T data) {
        item.add(0, data);
        notifyDataSetChanged();
    }

    public void addItem(@NonNull ArrayList<T> i) {
        item.addAll(0, i);
        notifyDataSetChanged();
    }

    //删除所有的数据列表
    public void clearItem() {
        item.clear();
        notifyDataSetChanged();
    }

    //获取所有的数据列表
    public ArrayList<T> getAllItem() {
        return item;
    }

}
