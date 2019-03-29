package com.example.recycledviewexercise;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recycledviewexercise.databinding.ActivityMainBinding;
import com.example.recycledviewexercise.databinding.ActivityProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding profileBinding;
    ActivityMainBinding mainBinding;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        mProgressBar = findViewById(R.id.progressbar);

        profileBinding.buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            v.getContext().startActivity(intent);
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<Employee> call;
        call = api.getEmployee(getIntent().getIntExtra("id", 666));
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
