package com.example.recycledviewexercise.addNewEmployee;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.R;
import com.example.recycledviewexercise.UpdateActivity;
import com.example.recycledviewexercise.UpdateEmployee;
import com.example.recycledviewexercise.databinding.ActivityAddEmployeeBinding;
import com.example.recycledviewexercise.views.main.MainActivity;
import com.example.recycledviewexercise.views.profile.ProfileActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEmployeeActivity extends AppCompatActivity implements AddNewEmployeeContract.View {

    ActivityAddEmployeeBinding activityAddEmployeeBinding;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiInterface api = retrofit.create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddEmployeeBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_employee);


        activityAddEmployeeBinding.buttonBackToMain.setOnClickListener(v -> backToMainActivity());
        activityAddEmployeeBinding.buttonNewEmployeeConfirm.setOnClickListener(v -> addNewEmploye());
    }

    public void addNewEmploye() {
        String name = activityAddEmployeeBinding.newName.getText().toString();

        double salary;
        String salaryString = activityAddEmployeeBinding.newSalary.getText().toString();
        salary = Double.valueOf(salaryString);

        int age = 0;
        String ageString = activityAddEmployeeBinding.newAge.getText().toString();
        age = Integer.valueOf(ageString);
        createAPIEmployee(name, salary, age);
    }

    private void createAPIEmployee(String name, double salary, int age) {
        UpdateEmployee employee = new UpdateEmployee(name, salary, age);
        Call<UpdateEmployee> employeesAdded = api.addEmployee(employee);
        employeesAdded.enqueue(new Callback<UpdateEmployee>() {
            @Override
            public void onResponse(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("response", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<UpdateEmployee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });

    }

    public void backToMainActivity() {
        Intent intent = new Intent(AddEmployeeActivity.this, MainActivity.class);
        this.startActivity(intent);
    }
}
