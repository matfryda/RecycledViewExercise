package com.example.recycledviewexercise;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.recycledviewexercise.databinding.ProfileLinearLayoutBinding;

public class ProfileActivity extends AppCompatActivity {

    ProfileLinearLayoutBinding profileBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.profile_linear_layout);

        Toast.makeText(this, "Clicked on me!", Toast.LENGTH_SHORT).show();
        profileBinding.buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            v.getContext().startActivity(intent);
        });

    }
}
