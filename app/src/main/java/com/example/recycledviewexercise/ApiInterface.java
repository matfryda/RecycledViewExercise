package com.example.recycledviewexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    String URL = "http://dummy.restapiexample.com";

    @GET("api/v1/employees")
    Call<List<Employee>> getEmployees();

    @GET("api/v1/employee/{id}")
    Call<Employee> getEmployee(@Path("id") int id); //okreslam typ  w <>

    @Headers("Content-Type: application/json")
    @PUT("api/v1/update/{id}")
    Call<UpdateEmployee> putEmployee(@Path("id") int id, @Body UpdateEmployee updateEmployee);

    @DELETE("api/v1/delete/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);

    @POST("api/v1/create")
    Call<UpdateEmployee> addEmployee(@Body UpdateEmployee updateEmployee);
}
