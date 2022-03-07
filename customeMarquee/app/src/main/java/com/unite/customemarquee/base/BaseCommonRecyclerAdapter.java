package com.unite.customemarquee.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseCommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>
{

    protected ArrayList<T> item = new ArrayList<>();
    protected Activity activity;
    private  int layoutId;

    public BaseCommonRecyclerAdapter(Activity activity,int layoutId)
    {
        this.activity = activity;
        this.layoutId=layoutId;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(activity).inflate(layoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }


    @Override
    public int getItemCount()
    {
        return item.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        setView(holder, position);
        Event(holder, position);
    }

    protected abstract void setView(@NonNull RecyclerViewHolder holder, int position);

    protected abstract void Event(@NonNull RecyclerViewHolder holder, int position);

    //设置数据
    public void setItem(ArrayList<T> i)
    {
        if (i != null && i.size() > 0)
        {
            item.clear();
            item.addAll(i);
        }
    }

    //添加数据
    public void addItem(ArrayList<T> i)
    {
        item.add((T) i);
    }

}


