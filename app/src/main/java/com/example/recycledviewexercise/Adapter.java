package com.example.recycledviewexercise;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class Adapter extends RecyclerView.Adapter<Adapter.BaseViewHolder> {


    private List<Employee> employeeList;
//    private Employee employee;

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
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) { //tworzymy szablon dla wyswietlanych widoków
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return new BaseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, final int position) { //powiązujemy View Holder

        Employee apiList = employeeList.get(position);
        viewHolder.binding.setVariable(BR.employee, apiList);

        Log.d(TAG, "onBindViewHolder: called.");
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }


    static class BaseViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }

}
