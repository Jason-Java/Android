package com.unite.customerecyclerview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class Adapter extends RecyclerView.Adapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> item = null;

    public Adapter(Activity activity) {
        this.activity = activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(activity, R.layout.layout_adapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holder holder1 = (Holder) holder;
        ((Holder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public void addItem(ArrayList<HashMap<String, String>> valueArray) {
        if (item == null) item = new ArrayList<>();
        item.addAll(valueArray);
    }

     class Holder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView age;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }

        public void setData(int position) {
            name.setText(item.get(position).get("name"));
            age.setText(item.get(position).get("age"));
        }

    }
}
