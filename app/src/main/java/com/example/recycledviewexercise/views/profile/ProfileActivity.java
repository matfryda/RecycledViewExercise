package com.example.recycledviewexercise.views.profile;

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
    boolean doubleBackToExitPressedOnce = false;
    boolean isFavorite;
    SharedPreferences sharedPreferences;
    ApiInterface api;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ProfileContract.Presenter presenter;

    ActivityProfileBinding profileBinding;

    final static String FAVS_EMPLOYEE = "favoriteEmployee";


    //---------------------------------TU JEST METODA ONCREATE() ---------------------------------//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = retrofit.create(ApiInterface.class);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        sharedPreferences = getSharedPreferences("id", ProfileActivity.MODE_PRIVATE);
        backToTheListOfEmployees();
        goToEditEmployee();
        getEmployee();
        loadFavorite();
    }
    //---------------------------------TU JEST METODA ONCREATE() ---------------------------------//


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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_favorite);
        if (isItFavorite()) {
            item.setIcon(R.drawable.ic_favorite_white_24dp);
        } else item.setIcon(R.drawable.ic_favorite_border_white_24dp);
        return true;
    }


    //------------------------------------------------------------------------------------------------//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean isFavourite = isItFavorite();
        switch (item.getItemId()) {
            case R.id.action_favorite: {
                if (isItFavorite()) {
                    Toast.makeText(ProfileActivity.this, "Removed from favourites", Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                    profileBinding.anonymousPic.setImageResource(R.drawable.bean);
                    removeFromFavorite();
                    isItFavorite();

                } else {
                    Toast.makeText(ProfileActivity.this, "Added to favourites", Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                    profileBinding.anonymousPic.setImageResource(R.drawable.happy_bean);
                    addToFavorite();
                    isItFavorite();

                }
                return true;
            }
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToFavorite() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int employeeId = getIntent().getIntExtra("id", 1);
        editor.putBoolean(String.valueOf(employeeId), true);
        editor.apply();
    }

    private void removeFromFavorite() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int employeeId = getIntent().getIntExtra("id", 1);
        editor.putBoolean(String.valueOf(employeeId), false);
        editor.apply();
    }

    //sprawdza czy pracownik o danym id jest już wpisany do ulubionych
    private boolean isItFavorite() {
        int id = getIntent().getIntExtra("id", 1);
        if (sharedPreferences.getBoolean(String.valueOf(id), false)) {
            return true;
        } else return false;
    }

    //------------metoda sprawdza czy dany pracownik jest juz w ulubionym, jesli jest to zmienia obrazek
//------------jest odpalana gdy ładowany jest widok-------------------------------------------------
    private void loadFavorite() {
        if (isItFavorite()) {
            MenuItem item = findViewById(R.id.action_favorite);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            profileBinding.anonymousPic.setImageResource(R.drawable.happy_bean);
        }
    }
}
