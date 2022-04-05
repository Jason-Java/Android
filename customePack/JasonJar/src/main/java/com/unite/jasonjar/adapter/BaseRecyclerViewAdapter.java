package com.unite.jasonjar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected ArrayList<T> item = new ArrayList<>();
    protected Activity activity;
    private int layoutId;

    public BaseRecyclerViewAdapter(Activity activity, int layoutId) {
        this.activity = activity;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        setView(holder, item.get(position), position);
        Event(holder, item.get(position), position);
    }

    protected abstract void setView(@NonNull RecyclerViewHolder holder, T item, int position);

    protected abstract void Event(@NonNull RecyclerViewHolder holder, T item, int position);

    //设置数据
    public void setItem(ArrayList<T> i) {
        if (i != null && i.size() > 0) {
            item.clear();
            item.addAll(i);
            notifyItemChanged(0,getItemCount()-1);
        }
    }


    //添加数据
    public void addItem(ArrayList<T> i) {
        item.add((T) i);
        notifyItemChanged(getItemCount() - 1);
    }

    //清空所有的元素
    public void clearItem() {
        int count = getItemCount();
        item.clear();
        notifyItemRangeRemoved(0, count - 1);
    }

    /**
     * 删除末项元素
     *
     * @param position
     */
    public void removeItem(int position) {
        item.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 获取所有的元素
     *
     * @return
     */
    public ArrayList<T> getAllItem() {
        return item;
    }


}


