package com.example.recycledviewexercise.views.profile;

public interface ProfileContract {
    interface View {
        public void getEmployee();
        public void backToTheListOfEmployees();
        public void goToEditEmployee();

    }


    interface Presenter {
        void getEmployee();

        public void create();
    }
}
