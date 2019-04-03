package com.example.recycledviewexercise;

import com.google.gson.annotations.SerializedName;

class UpdateEmployee {
    UpdateEmployee(int id, String name, double salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("salary")
    public double salary;

    @SerializedName("age")
    public int age;

}
