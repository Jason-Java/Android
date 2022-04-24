package com.unite.customepack;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.unite.jasonjar.adapter.RecyclerViewHolder;
import com.unite.jasonjar.domain.KeyValue;
import com.unite.jasonjar.view.MultipleAdapter;

public class MyAdapter extends MultipleAdapter {


    public MyAdapter(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, KeyValue item, int position) {
holder.setText(R.id.key,item.getKey());
holder.setText(R.id.value,item.getValue());
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, KeyValue item, int position) {

    }
}
