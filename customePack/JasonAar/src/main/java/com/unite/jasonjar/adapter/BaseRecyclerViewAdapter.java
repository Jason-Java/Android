package com.unite.jasonjar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected List<T> item ;
    protected Activity activity;
    private int layoutId;

    public BaseRecyclerViewAdapter(Activity activity, int layoutId) {
        this.activity = activity;
        this.layoutId = layoutId;
        item = new ArrayList<>();
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
    public void addItem(List<T> i) {
        int star = item.size();
        item.addAll(i);
        notifyItemRangeChanged(star , i.size());
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
     * 移除指定位置的元素
     *
     * @param index
     */
    public boolean removeItem(int index) {
        if (index > getItemCount()-1 || getItemCount() == 0 || index < 0) {
            return false;
        }
        item.remove(index);
        notifyItemMoved(index,index);


        //notifyDataSetChanged();
        return true;
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
    public List<T> getAllItem() {
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

    public void replaceItem(int index, T element) {
        item.set(index, element);
        notifyItemChanged(index);
    }
}


