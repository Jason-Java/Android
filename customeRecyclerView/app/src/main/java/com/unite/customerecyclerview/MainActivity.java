package com.unite.customerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRec = findViewById(R.id.myRecycler);
        init();
    }

    private void init() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRec.addItemDecoration(new ItemDecorate());
        mRec.setLayoutManager(linearLayoutManager);

        ArrayList<HashMap<String, String>> item = new ArrayList<>();
        HashMap<String, String> map;
        for (int i = 0; i < 30; i++) {
            map = new HashMap<>();
            map.put("name", "1235" + i);
            map.put("age", i+"");
            item.add(map);
        }
        Adapter adapter = new Adapter(this);
        adapter.addItem(item);
        mRec.setAdapter(adapter);
    }
}