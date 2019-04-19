package com.example.recycledviewexercise.views.profile;

import android.util.Log;

import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilePresenter implements ProfileContract.Presenter {

    ProfileActivity view;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    public void getEmployee() {
    }
//        ApiInterface api = api = retrofit.create(ApiInterface.class);
//        ;
//        Call<Employee> call;
//        call = api.getEmployee(getIntent().getIntExtra("id", 666)); //tu jakie ma aktualne id
//        call.enqueue(new Callback<Employee>() {
//            @Override
//            public void onResponse(Call<Employee> call, Response<Employee> response) {
//                if (response.isSuccessful()) {
//                    view.getEmployee();
//                    profileBinding.setEmployee(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Employee> call, Throwable t) {
//                Log.d("response", t.getMessage());
//            }
//        });
}
