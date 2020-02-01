package com.example.foodapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.Fragment.HomeFragment;
import com.example.foodapp.Fragment.MapFragment;
import com.example.foodapp.Fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolBar;
    public static final String TAG ="Main Activity";
    public String uid;
    public DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = getSupportActionBar();
        BottomNavigationView nav = findViewById(R.id.navigation);
        nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
