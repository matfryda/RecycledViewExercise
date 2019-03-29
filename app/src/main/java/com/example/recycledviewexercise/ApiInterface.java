package com.example.recycledviewexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    String URL = "http://dummy.restapiexample.com";

    @GET("api/v1/employees")
    Call<List<Employee>> getEmployees();

    @GET("api/v1/employee/{id}")
    Call<Employee> getEmployee (@Path("id") int id); //okreslam typ  w <>
}
