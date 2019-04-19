package com.example.recycledviewexercise;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recycledviewexercise.databinding.ActivityUpdateBinding;
import com.example.recycledviewexercise.views.profile.ProfileActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding activityUpdateBinding;
    ApiInterface api;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateBinding = DataBindingUtil.setContentView(UpdateActivity.this, R.layout.activity_update);
        api = retrofit.create(ApiInterface.class);
        getEmployee();

        activityUpdateBinding.buttonBackToTheProfile.setOnClickListener(v -> backToProfileActivity());
        updateEmployeeButton();
        deleteEmployeeButton();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateEmployeeButton() {
        activityUpdateBinding.buttonConfirm.setOnClickListener(this::dialogUpdateEmployeeConfirm);
    }

    private void deleteEmployeeButton() {
        activityUpdateBinding.buttonDelete.setOnClickListener(this::dialogDeleteEmployeeConfirm);
    }

    private void dialogDeleteEmployeeConfirm(View v) {
        AlertDialog confirmDelete = new AlertDialog.Builder(this)
                .setTitle("Confirm your decision")
                .setIcon(R.drawable.hitman)
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    int id = getIntent().getIntExtra("id", 1);
                    deleteEmployee(id);
                    Toast.makeText(v.getContext(), "The employee is dead, congratulations", Toast.LENGTH_SHORT).show();
                }).setNegativeButton("No", (dialog, which) -> {
                    dialog.cancel();
                    Toast.makeText(v.getContext(), "Come on, kill him!", Toast.LENGTH_SHORT).show();
                }).create();
        confirmDelete.show();
    }

    private void dialogUpdateEmployeeConfirm(View v) {
        AlertDialog confirmUpdate = new AlertDialog.Builder(this)
                .setTitle("Confirm your decision")
                .setIcon(R.drawable.batman_icon)
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    editEmployee(v);
                })
                .setNegativeButton("Nope", (dialog, which) -> {
                    dialog.cancel();
                    Toast.makeText(v.getContext(), "Come on,  pimp my employee!", Toast.LENGTH_SHORT).show();
                }).create();
        confirmUpdate.show();
    }

    private void showCompleteDataDialog(String fieldName) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Complete all data!")
                .setMessage(String.format("%s field is empty!", fieldName.toUpperCase()))
                .setCancelable(false)
                .setNeutralButton("Ok", null)
                .create();
        dialog.show();
    }

    private void showInvalidDataDialog(String fieldName, String reason) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Invalid data!")
                .setMessage(String.format("%s field has invalid data!\n%s", fieldName.toUpperCase(), reason))
                .setCancelable(false)
                .setNeutralButton("Ok", null)
                .create();
        dialog.show();
    }

    private void editEmployee(View v) {
        int id = getIntent().getIntExtra("id", 1);
        String name = activityUpdateBinding.editName.getText().toString();
        if (TextUtils.isEmpty(name.trim())) {
            showCompleteDataDialog("name");
            return;
        }

        double salary;
        try {
            String salaryString = activityUpdateBinding.editSalary.getText().toString();
            if (TextUtils.isEmpty(salaryString)) {
                showCompleteDataDialog("salary");
                return;
            }
            salary = Double.valueOf(salaryString);
            if (salary <= 0.0) {
                showInvalidDataDialog("salary", "Salary value should be geather than zero.");
                return;
            }
        } catch (NumberFormatException ignored) {
            showInvalidDataDialog("salary", "Salary value is not numeric.");
            return;
        }
        int age = -1;
        try {
            String ageString = activityUpdateBinding.editAge.getText().toString();
            if (TextUtils.isEmpty(ageString)) {
                showCompleteDataDialog("age");
                return;

            }
            if (ageString.equals(0)) {
                showInvalidDataDialog("age", "Age value should be geather than zero.");
                return;
            }
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException ignored) {
            showInvalidDataDialog("age", "Age value is not numeric");
        }
        updateAPIEmployee(id, name, salary, age);
        Toast.makeText(v.getContext(), "Good, now the employee is better!", Toast.LENGTH_SHORT).show();
    }

    void updateAPIEmployee(int id, String name, Double salary, int age) {
        UpdateEmployee updateEmployee =
                new UpdateEmployee(id, name, salary, age);
        Call<UpdateEmployee> call = api.putEmployee(id, updateEmployee);

        call.enqueue(new Callback<UpdateEmployee>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
                onResponseEmployeeOK(call, response);
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(@SuppressWarnings("NullableProblems") Call<UpdateEmployee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

    public void onResponseEmployeeOK(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
        if (response.isSuccessful()) {
            assert response.body() != null;
            Log.d("response", response.body().toString());
        }
    }

    public void onResponseDeleteEmployeeOK(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
        if (response.isSuccessful()) {
            assert response.body() != null;
            Log.d("response", response.body().toString());
        }
    }

    private void getEmployee() {
        Call<Employee> call;
        call = api.getEmployee(getIntent().getIntExtra("id", 666)); //tu jakie ma aktualne id

    }

    public void deleteEmployee(int id) {

        Call<Void> call = api.deleteTheEmployee(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                textViewResult.setText("Code " + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void backToProfileActivity() {
        Intent intent = new Intent(UpdateActivity.this, ProfileActivity.class);
        int id = getIntent().getIntExtra("id", 1);
        intent.putExtra("id", id);
        this.startActivity(intent);
    }
}
