package com.example.recycledviewexercise;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public
class Employee {

    @SerializedName("id")
    public int id;

    @SerializedName("employee_name")
    public String name;

    @SerializedName("salary")
    public double salary;

    @SerializedName("employee_age")
    public int age;

    @SerializedName("profile_image")
    public String image;


}
