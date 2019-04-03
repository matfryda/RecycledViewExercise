package com.example.recycledviewexercise;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.recycledviewexercise.databinding.ActivityUpdateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    EditText editName;
    EditText editSalary;
    EditText editAge;
    Button buttonConfirm;
    Button buttonBack;
    ActivityUpdateBinding activityUpdateBinding;

    ApiInterface api;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateBinding = DataBindingUtil.setContentView(this, R.layout.activity_update);
        api = retrofit.create(ApiInterface.class);
        setContentView(R.layout.activity_update);

        editName = findViewById(R.id.editName);
        editSalary = findViewById(R.id.editSalary);
        editAge = findViewById(R.id.editAge);

        buttonBack = findViewById(R.id.buttonBackToProfile);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
            startActivity(intent);
        });

        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(v -> {
            int id = getIntent().getIntExtra("id", 1);
            updateAPIEmployee(id);
            Intent intent = new Intent(v.getContext(), UpdateActivity.class);
            startActivity(intent);
        });

    }

    void updateAPIEmployee(int id) {

        UpdateEmployee updateEmployee = new UpdateEmployee(id, editName.toString(), 132, 32);
        Call<Employee> call = api.putEmployee(id, updateEmployee);

        call.enqueue(new Callback<Employee>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                onResponseEmployeeOK(call, response);
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(@SuppressWarnings("NullableProblems") Call<Employee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

    public void onResponseEmployeeOK(Call<Employee> call, Response<Employee> response) {
        if (response.isSuccessful()) {
            assert response.body() != null;
            Log.d("response", response.body().toString());
        }
    }
}
