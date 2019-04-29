package com.example.recycledviewexercise.views.main;

import com.example.recycledviewexercise.Adapter;
import com.example.recycledviewexercise.Employee;

import java.util.List;

public interface MainContract {

    interface View {
        void setRecyclerView();

        void showProgressBar(boolean isVisible);

        void showEmployees(List<Employee> employees);

        void addEmployee();

    }
    interface Presenter {


        void onEmployeeClick(Adapter adapter);

        void onCreate();

    }
}
