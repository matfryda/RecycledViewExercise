package com.example.recycledviewexercise.views.main;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recycledviewexercise.Adapter;
import com.example.recycledviewexercise.Employee;
import com.example.recycledviewexercise.R;
import com.example.recycledviewexercise.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private Toolbar mTopToolbar;
    ActivityMainBinding mBinding;
    MainContract.Presenter presenter = new MainPresenter(this);
    boolean doubleBackToExitPressedOnce = false;
    SharedPreferences utils;

//-----------------------------METODA GLOWNA ONCREATE()---------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this);
        utils = getSharedPreferences("favorite", MainActivity.MODE_PRIVATE);
//        setSupportActionBar(mTopToolbar);
        presenter.onCreate();
    }

//-----------------------------METODA GLOWNA ONCREATE()---------------------------------------------

    @Override
    public void showEmployees(List<Employee> employees) {
        Adapter adapter = new Adapter(employees);
        mBinding.recyclerView.setAdapter(adapter);
        presenter.onEmployeeClick(adapter);
        Log.d("employees", employees.toString());
    }

    @Override
    public void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //liniowy layout manager
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showProgressBar(boolean isVisible) {
        if (isVisible) {
            mBinding.progressbar.setVisibility(View.VISIBLE);
        } else {
            mBinding.progressbar.setVisibility(View.GONE);
        }
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.favourite_button) {
            Toast.makeText(MainActivity.this, "Favourite action clicked", Toast.LENGTH_LONG).show();
            saveData();
            return true;
        }
        loadData();
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        String loadFromPref = utils.getString("favorite", "");
    }

    private void saveData() {
        SharedPreferences.Editor editor = utils.edit();
        editor.commit();
    }
}
