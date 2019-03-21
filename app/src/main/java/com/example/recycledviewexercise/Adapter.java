package com.example.recycledviewexercise;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.recycledviewexercise.databinding.ItemLayoutBinding;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> modelList;

    Adapter(List<ModelClass> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //tworzymy szablon dla wyswietlanych widoków
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_layout, viewGroup, false);
        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) { //powiązujemy View Holder
        ModelClass listModels = modelList.get(position);
        viewHolder.itemLayoutBinding.setImage(listModels);

        Log.d(TAG, "onBindViewHolder: called.");
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding itemLayoutBinding;

        ViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            itemLayoutBinding = itemView;
        }
    }
}

