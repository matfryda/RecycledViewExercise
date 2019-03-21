package com.example.recycledviewexercise;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.recycledviewexercise.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ActivityMainBinding mBinding;


    public void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view); //powiązujemy z id danym w pliku xml
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //liniowy layout manager

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);
        setRecyclerView();

        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Model---Sun---Gasometer---Oberhausen---%28Gentry%29.jpg/1200px-Model---Sun---Gasometer---Oberhausen---%28Gentry%29.jpg", "Sun", "Our star"));
        modelClassList.add(new ModelClass("https://media.istockphoto.com/photos/solar-sysytem-planet-mercury-picture-id691444480?k=6&m=691444480&s=612x612&w=0&h=Tpqze-_FN7VIhbnSuik3UUPqYGa8z0ZQWgwkt0CVRuk=", "Marcury", "First planet from the Sun"));


        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
