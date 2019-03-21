package com.example.recycledviewexercise;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ModelClass {
    public String imageResource;
    public String title;
    public String body;

    ModelClass(String imageResource, String title, String body) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }


}
