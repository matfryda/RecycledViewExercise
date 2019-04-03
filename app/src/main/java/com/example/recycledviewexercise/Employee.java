package com.example.recycledviewexercise;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public
class Employee {

    @SerializedName("id")
    public int id;

    @SerializedName("employee_name")
    public String name;

    @SerializedName("employee_salary")
    public double salary;

    @SerializedName("employee_age")
    public int age;

    @SerializedName("profile_image")
    public String image;

}
