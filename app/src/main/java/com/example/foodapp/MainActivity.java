package com.example.foodapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.Fragment.HomeFragment;
import com.example.foodapp.Fragment.MapFragment;
import com.example.foodapp.Fragment.UserFragment;
import com.example.foodapp.services.LocationBgService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolBar;
    public static final String TAG ="Main Activity";
    public Intent serviceIntent;
    public String uid;
    public DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent(MainActivity.this, LocationBgService.class);
        handlePermissions();
        startService(serviceIntent);
        toolBar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.navigation);
        nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void handlePermissions() {
        int locationRequestCode = 1000;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, locationRequestCode);
        else{
            startService(serviceIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startService(serviceIntent);
                }
                else
                    Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_user:
                    toolBar.setTitle("User");
                    fragment = new UserFragment();
                    //fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_home:
                    toolBar.setTitle("Home");
                    fragment = new HomeFragment();
                    //fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_map:
                    toolBar.setTitle("Map");
                    fragment = new MapFragment();
                    //fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
