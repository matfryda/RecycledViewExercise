package com.example.recycledviewexercise;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view); //powiązujemy z id danym w pliku xml
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //liniowy layout manager

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);


        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture one", "this is picture one"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture two", "this is picture two"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture three", "this is picture three"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture four", "this is picture four"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture five", "this is picture five"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture six", "this is picture six"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture seven", "this is picture seven"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture eight", "this is picture eight"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, "picture nine", "this is picture nine"));

        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
