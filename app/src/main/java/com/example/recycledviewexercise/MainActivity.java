package com.example.recycledviewexercise;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view); //powiązujemy z id danym w pliku xml

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);  //liniowy layout manager
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
//        mAdapter = new RecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        //test
    }
}
