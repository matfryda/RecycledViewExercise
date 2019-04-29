package com.example.recycledviewexercise.addNewEmployee;

import android.util.Log;
import android.widget.Toast;

import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.UpdateEmployee;
import com.example.recycledviewexercise.databinding.ActivityAddEmployeeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewEmployeePresenter implements AddNewEmployeeContract.Presenter {

    private AddNewEmployeeContract.View view;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ApiInterface api = retrofit.create(ApiInterface.class);

    AddNewEmployeePresenter(AddNewEmployeeContract.View view) {
        this.view = view;
    }

    @Override
    public void createAPIEmployee(String name, double salary, int age) {
        UpdateEmployee employee = new UpdateEmployee(name, salary, age);
        Call<UpdateEmployee> employeesAdded = api.addEmployee(employee);
        employeesAdded.enqueue(new Callback<UpdateEmployee>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("response", response.body().toString());

                }
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<UpdateEmployee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

    @Override
    public void create() {
      view.init();
    }

    @Override
    public void addNewEmployee() {
        view.completeData();
    }
}
