package com.jason.exampelrxjava;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.unite.jasonjar.adapter.BaseRecyclerViewAdapter;
import com.unite.jasonjar.adapter.RecyclerViewHolder;

public class AdapterStar extends BaseRecyclerViewAdapter<Star> {

    public AdapterStar(Activity activity, int layoutId) {
        super(activity, layoutId);
    }


    //判断是否是头部
    public boolean isHeadItem(int position) {
        if (position == 0) {
            return true;
        } else {
            int currentGroup = item.get(position).getGroupName();
            int preGroup = item.get(position - 1).getGroupName();
            if (currentGroup != preGroup) {
                return true;
            }
            return false;
        }
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, Star item, int position) {
        holder.setText(R.id.tv, item.getName());
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, Star item, int position) {

    }
}
