package com.example.recycledviewexercise.views.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.recycledviewexercise.Adapter;
import com.example.recycledviewexercise.Employee;
import com.example.recycledviewexercise.R;
import com.example.recycledviewexercise.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    ActivityMainBinding mBinding;
    MainContract.Presenter presenter = new MainPresenter(this);

//-----------------------------METODA GLOWNA ONCREATE()---------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this);
        presenter.onCreate();
    }

//-----------------------------METODA GLOWNA ONCREATE()---------------------------------------------

    @Override
    public void showEmployees(List<Employee> employees) {
        Adapter adapter = new Adapter(employees);
        mBinding.recyclerView.setAdapter(adapter);
        presenter.onEmployeeClick(adapter);
        Log.d("employees", employees.toString());
    }

    @Override
    public void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //liniowy layout manager
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showProgressBar(boolean isVisible) {
        if (isVisible) {
            mBinding.progressbar.setVisibility(View.VISIBLE);
        } else {
            mBinding.progressbar.setVisibility(View.GONE);
        }
    }

}
