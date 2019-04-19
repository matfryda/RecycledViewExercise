package com.example.recycledviewexercise.views.profile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.Employee;
import com.example.recycledviewexercise.R;
import com.example.recycledviewexercise.UpdateActivity;
import com.example.recycledviewexercise.databinding.ActivityProfileBinding;
import com.example.recycledviewexercise.views.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {
    ApiInterface api;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ProfileContract.Presenter presenter;

    ActivityProfileBinding profileBinding;
    ActionBar actionBar = getSupportActionBar();

    //---------------------------------TU JEST METODA ONCREATE() ---------------------------------//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = retrofit.create(ApiInterface.class);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        backToTheListOfEmployees();

        goToEditEmployee();

        getEmployee();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void goToEditEmployee() {
        profileBinding.buttonUpdate.setOnClickListener(v -> {
            int id = getIntent().getIntExtra("id", 1);
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("id", id);
            v.getContext().startActivity(intent);
        });
    }

    private void backToTheListOfEmployees() {
        profileBinding.buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    void getEmployee() {
        Call<Employee> call;
        call = api.getEmployee(getIntent().getIntExtra("id", 666)); //tu jakie ma aktualne id
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    profileBinding.setEmployee(response.body());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

}

