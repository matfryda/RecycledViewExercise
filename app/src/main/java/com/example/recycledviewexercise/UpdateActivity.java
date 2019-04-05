package com.example.recycledviewexercise;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.recycledviewexercise.databinding.ActivityUpdateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding activityUpdateBinding;
    ApiInterface api;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateBinding = DataBindingUtil.setContentView(UpdateActivity.this, R.layout.activity_update);
        api = retrofit.create(ApiInterface.class);

        getEmployee();


        activityUpdateBinding.buttonBackToTheProfile.setOnClickListener(v -> backToProfileActivity());

        activityUpdateBinding.buttonConfirm.setOnClickListener(v -> {
            int id = getIntent().getIntExtra("id", 1);
            String name = activityUpdateBinding.editName.getText().toString();
            Double salary = Double.valueOf(activityUpdateBinding.editSalary.getText().toString());
            int age = Integer.parseInt(activityUpdateBinding.editAge.getText().toString());
            updateAPIEmployee(id, name, salary, age);
            backToProfileActivity();
        });
        activityUpdateBinding.buttonDelete.setOnClickListener(v -> {
            int id = getIntent().getIntExtra("id", 1);
            deleteEmployee(id);
            backToProfileActivity();
        });
    }

    void updateAPIEmployee(int id, String name, Double salary, int age) {
        UpdateEmployee updateEmployee =
                new UpdateEmployee(id, name, salary, age);
        Call<UpdateEmployee> call = api.putEmployee(id, updateEmployee);

        call.enqueue(new Callback<UpdateEmployee>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
                onResponseEmployeeOK(call, response);
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(@SuppressWarnings("NullableProblems") Call<UpdateEmployee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

    public void onResponseEmployeeOK(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
        if (response.isSuccessful()) {
            assert response.body() != null;
            Log.d("response", response.body().toString());
        }
    }

    public void onResponseDeleteEmployeeOK(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
        if (response.isSuccessful()) {
            assert response.body() != null;
            Log.d("response", response.body().toString());
        }
    }

    private void getEmployee() {
        Call<Employee> call;
        call = api.getEmployee(getIntent().getIntExtra("id", 666)); //tu jakie ma aktualne id

    }

    public void deleteEmployee(int id) {

        Call<Void> call = api.deleteTheEmployee(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                textViewResult.setText("Code " + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void backToProfileActivity() {
        Intent intent = new Intent(UpdateActivity.this, ProfileActivity.class);
        int id = getIntent().getIntExtra("id", 1);
        intent.putExtra("id", id);
//        intent.putExtra("name", activityUpdateBinding.editName.getText().toString());
//        intent.putExtra("salary", Double.valueOf(activityUpdateBinding.editSalary.getText().toString()));
//        intent.putExtra("age", Integer.valueOf(activityUpdateBinding.editAge.getText().toString()));
        this.startActivity(intent);
    }
}
