package com.example.recycledviewexercise.views.main;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.recycledviewexercise.Adapter;
import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.Employee;
import com.example.recycledviewexercise.views.profile.ProfileActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

import static android.support.constraint.Constraints.TAG;

public class MainPresenter implements MainContract.Presenter {
    //pobieranie danych w prezenterze


    private MainContract.View view;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onEmployeeClick(Adapter adapter) {
        adapter.setOnClickItemListener((v, employee) -> {
            Log.d(TAG, "onClick: ");
            Toast.makeText(v.getContext(), "Actual salary " + String.valueOf(employee.salary), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
            intent.putExtra("id", employee.id);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public void onCreate() {
        view.setRecyclerView();
        getEmployees();
    }

    private void getEmployees() {
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<Employee>> call = api.getEmployees(); //za pomocą interface Call tworzymy listę obiektów
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(response.isSuccessful()){
                    List<Employee> employees = response.body();
                    view.showEmployees(employees);
                }
                view.showProgressBar(false);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                view.showProgressBar(false);
                Log.d("response", t.getMessage());
            }
        });
    }

}
