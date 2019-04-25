package com.example.recycledviewexercise.views.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recycledviewexercise.ApiInterface;
import com.example.recycledviewexercise.Employee;
import com.example.recycledviewexercise.R;
import com.example.recycledviewexercise.UpdateActivity;
import com.example.recycledviewexercise.databinding.ActivityProfileBinding;
import com.example.recycledviewexercise.views.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {
    SharedPreferences utils;
    ApiInterface api;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ProfileContract.Presenter presenter;

    ActivityProfileBinding profileBinding;


    //---------------------------------TU JEST METODA ONCREATE() ---------------------------------//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = retrofit.create(ApiInterface.class);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        utils = getSharedPreferences("favoriteemployee", ProfileActivity.MODE_PRIVATE);
        presenter.create();
    }

    @Override
    public void goToEditEmployee() {
        profileBinding.buttonUpdate.setOnClickListener(v -> {
            int id = getIntent().getIntExtra("id", 1);
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("id", id);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public void backToTheListOfEmployees() {
        profileBinding.buttonBack.setOnClickListener(v -> {
            onBackPressed();
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public void getEmployee() {
        Call<Employee> call;
        call = api.getEmployee(getIntent().getIntExtra("id", 666)); //tu jakie ma aktualne id
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    profileBinding.setEmployee(response.body());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private boolean isFavorite = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_favorite: {
                if (isFavorite) {
                    Toast.makeText(ProfileActivity.this, "Favourite action unclicked", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("favoriteEmployee", MODE_PRIVATE).edit();
                    editor.putInt("employeeId", getIntent().getIntExtra("id", 1));
                    editor.apply();
                    profileBinding.anonymousPic.setImageResource(R.drawable.bean);
                    item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                    isFavorite = false;
                } else {
                    Toast.makeText(ProfileActivity.this, "Favourite action clicked", Toast.LENGTH_LONG).show();
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                    profileBinding.anonymousPic.setImageResource(R.drawable.happy_bean);
                    addToFavorite(getIntent().getIntExtra(" id", 0));
                    isFavorite = true;
                }
                return true;
            }
            default:
                item.setIcon(R.drawable.ic_favorite_red_24dp);

        }
        return super.onOptionsItemSelected(item);
    }

    private void addToFavorite(int id) {
//        SharedPreferences saveId = getSharedPreferences("id", getIntent().getIntExtra("id", 1));
//        SharedPreferences saveId = getSharedPreferences("id", id);
        SharedPreferences saveId = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveId.edit();
        editor.putInt("favorite", id);
        editor.commit();
    }
}
