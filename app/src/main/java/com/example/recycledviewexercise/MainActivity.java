package com.example.recycledviewexercise;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.recycledviewexercise.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    public void onResponseOK(Call<List<Employee>> call, Response<List<Employee>> response) {
        if (response.isSuccessful()) {
            Adapter adapter = new Adapter(response.body());
            mBinding.recyclerView.setAdapter(adapter);
            assert response.body() != null;
            Log.d("response", response.body().toString());
        }
        mBinding.progressbar.setVisibility(View.GONE);
    }


    public void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //liniowy layout manager
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);

        setRecyclerView();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        mBinding.progressbar.setVisibility(View.VISIBLE);

        Call<List<Employee>> call = api.getEmployees(); //za pomocą interface Call tworzymy listę obiektów


        call.enqueue(new Callback<List<Employee>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    Adapter adapter = new Adapter(response.body());
                    mBinding.recyclerView.setAdapter(adapter);
                    assert response.body() != null;
                    Log.d("response", response.body().toString());
                }
                mBinding.progressbar.setVisibility(View.GONE);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                mBinding.progressbar.setVisibility(View.GONE);
                Log.d("response", t.getMessage());
            }
        });
    }

}
