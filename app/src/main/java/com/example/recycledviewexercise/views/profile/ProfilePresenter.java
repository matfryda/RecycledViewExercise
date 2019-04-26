package com.example.recycledviewexercise.views.profile;

import android.content.Intent;
import android.util.Log;

import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.Employee;
import com.example.recycledviewexercise.views.main.MainActivity;

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

    @Override
    public void create() {
        view.backToTheListOfEmployees();
        view.goToEditEmployee();
        getEmployee();
    }




}
