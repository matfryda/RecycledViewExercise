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
    private OnClickItemListener onClickItemListener;


    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public Adapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.relative_under_16;
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
            onClickItemListener.onClick(v, employee);
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
