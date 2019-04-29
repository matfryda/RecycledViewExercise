package com.example.recycledviewexercise.addNewEmployee;

public interface AddNewEmployeeContract {
    interface View {
        void backToMainActivity();

        void init();

        void completeData();
    }

    interface Presenter {

        void createAPIEmployee(String name, double salary, int age);

        void create();

        void addNewEmployee();
    }


}
