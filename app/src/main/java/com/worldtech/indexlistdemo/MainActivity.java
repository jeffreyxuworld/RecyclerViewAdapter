package com.worldtech.indexlistdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.worldtech.indexlistdemo.adapter.IndexDataAdapter;
import com.worldtech.indexlistdemo.model.IndexDataModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IndexDataAdapter indexDataAdapter;
    private String jsonData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        indexDataAdapter = new IndexDataAdapter(this);
        recyclerView.setAdapter(indexDataAdapter);

        Gson gson = new Gson();
        IndexDataModel model = gson.fromJson(jsonData, IndexDataModel.class);
        indexDataAdapter.updateShowTypeData(model.data);
        indexDataAdapter.notifyDataSetChanged();

    }




}
