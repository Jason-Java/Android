package com.unite.jasonjar.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.unite.jasonjar.R;
import com.unite.jasonjar.domain.KeyValue;

public class AdapterKeyValue extends BaseRecyclerViewAdapter<KeyValue> {

    public AdapterKeyValue(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, KeyValue item, int index) {

       /* holder.setText(R.id.tvKey, item.getKey());
        holder.setText(R.id.tvValue, item.getValue());*/
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, KeyValue item, int index) {

    }


}
