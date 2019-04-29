package com.example.recycledviewexercise.addNewEmployee;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.recycledviewexercise.R;
import com.example.recycledviewexercise.databinding.ActivityAddEmployeeBinding;
import com.example.recycledviewexercise.views.main.MainActivity;

public class AddEmployeeActivity extends AppCompatActivity implements AddNewEmployeeContract.View {

    ActivityAddEmployeeBinding activityAddEmployeeBinding;

    AddNewEmployeeContract.Presenter presenter = new AddNewEmployeePresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddEmployeeBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_employee);

        presenter.create();
    }

    @Override
    public void init(){
        activityAddEmployeeBinding.buttonBackToMain.setOnClickListener(v -> backToMainActivity());
        activityAddEmployeeBinding.buttonNewEmployeeConfirm.setOnClickListener(v -> presenter.addNewEmployee());
    }

    @Override
    public void backToMainActivity() {
        Intent intent = new Intent(AddEmployeeActivity.this, MainActivity.class);
        this.startActivity(intent);
    }


    @Override
    public void completeData() {
        String name = activityAddEmployeeBinding.newName.getText().toString();

        String salaryString = activityAddEmployeeBinding.newSalary.getText().toString();
        double salary = Double.valueOf(salaryString);

        String ageString = activityAddEmployeeBinding.newAge.getText().toString();
        int age = Integer.valueOf(ageString);
        presenter.createAPIEmployee(name, salary, age);
        Toast.makeText(this, "New Employee is ready", Toast.LENGTH_SHORT).show();
    }
}
