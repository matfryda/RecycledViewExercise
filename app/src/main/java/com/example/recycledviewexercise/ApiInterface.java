package com.example.recycledviewexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String URL = "http://dummy.restapiexample.com";

    @GET("api/v1/employees")
    Call<List<Employee>> getEmployees();
}
