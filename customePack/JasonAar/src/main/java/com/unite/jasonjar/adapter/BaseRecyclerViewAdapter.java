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
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int index) {
        setView(holder, item.get(index), index);
        Event(holder, item.get(index), index);
    }

    protected abstract void setView(@NonNull RecyclerViewHolder holder, T item, int index);

    protected abstract void Event(@NonNull RecyclerViewHolder holder, T item, int index);

    //设置数据
    public void setItem(ArrayList<T> i) {
        if (i != null && i.size() > 0) {
            item.clear();
            item.addAll(i);
            notifyDataSetChanged();
        }
    }


    /**
     * 添加数据集合
     * @param i
     */
    public void addItem(ArrayList<T> i) {
        int star = item.size();
        item.addAll(i);
        int count = item.size() - star;
        notifyItemRangeChanged(star - 1, count);
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 添加一条数据
     * @param i
     */
    public void addItem(T i) {
        item.add(i);
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 移除项元素
     *
     * @param index
     */
    public void removeItem(int index) {
        item.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 清空所有的元素
     */
    public void clearItem() {
        item.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取所有的元素
     *
     * @return
     */
    public ArrayList<T> getAllItem() {
        return item;
    }

    /**
     * 获取指定位置的item
     *
     * @param index
     * @return
     */
    public T getItem(int index) {
        return item.get(index);
    }
}


