package com.example.recycledviewexercise.addNewEmployee;

public interface AddNewEmployeeContract {
    public interface View {

    }

    public interface Presenter {
        void createAPIEmployee(String name, double salary, int age);
    }


}
