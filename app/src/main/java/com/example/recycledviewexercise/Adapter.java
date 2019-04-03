package com.example.recycledviewexercise;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class Adapter extends RecyclerView.Adapter<Adapter.BaseViewHolder> {


    private List<Employee> employeeList;
    public Employee employee;

    Adapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 16) {
            return R.layout.relative_under_16;
        } else if (position < 20) {
            return R.layout.relative_linear_employees;
        } else return R.layout.relative_employees_layout;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {  //tworzymy szablon dla wyswietlanych widoków
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return new BaseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, final int position) { //powiązujemy View Holder

        Employee employee = employeeList.get(position);                                     //tworze liste, bedzie na nia wpisywalo z api

        viewHolder.binding.setVariable(BR.employee, employee);                              //binduje trzymacz widoku z lista, BR - binding resources. Pozwala odniesc sie do employee
        viewHolder.itemView.setOnClickListener(v -> {                                       //na klikniecie na element ma sie zadziac ten clickOnListener
            Log.d(TAG, "onClick: ");
            Toast.makeText(v.getContext(), "Actual salary " + String.valueOf(employee.salary), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
            intent.putExtra("id", employee.id);
            v.getContext().startActivity(intent);

        });


        Log.d(TAG, "onBindViewHolder: called.");
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }      //zwraca wielkość listy


    static class BaseViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;                                    //bindujemy

        BaseViewHolder(@NonNull View itemView) {                    //Nasz bazowy trzymacz widoku, bindujemy go.
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
