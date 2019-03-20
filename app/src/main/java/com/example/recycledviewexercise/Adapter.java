package com.example.recycledviewexercise;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> modelClassList;


     Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //tworzymy szablon dla wyswietlanych widoków
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) { //powiązujemy View Holder
        final int resource = modelClassList.get(position).getImageResource();
        final String title = modelClassList.get(position).getTitle();
        final String body = modelClassList.get(position).getBody();
        Log.d(TAG, "onBindViewHolder: called.");


        viewHolder.setData(resource, title, body);


    }

    @Override
    public int getItemCount() {
        return modelClassList.size(); //zwracamy wielkość listy
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView body;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }

        private void setData(int resource, String titleText, String bodyText) {
            imageView.setImageResource(resource);
            title.setText(titleText);
            body.setText(bodyText);
        }
    }
}
