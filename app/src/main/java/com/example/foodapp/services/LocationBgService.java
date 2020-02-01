package com.example.foodapp.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.foodapp.Models.User;
import com.example.foodapp.helpingClasses.statics;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationBgService extends Service {

    int mstartMode;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest = LocationRequest.create();
    private LocationCallback mLocationCallback;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private User currUser;
    private String uid;
    private double latitude = 0.0, longitude = 0.0, altitude = 0.0;
    private static final String TAG = "LocationBgService";
    DatabaseReference dbref,userdbref;


    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate: ");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init(intent);
        return mstartMode;
    }

    private void init(Intent intent) {

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        uid = firebaseUser.getUid();
        //currUser = (User)intent.getSerializableExtra("curruser");
//        Log.d("USER IN LBG SERVICE",currUser.name);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null)
                    return;
                for (Location location : locationResult.getLocations()) {

                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.d("LOCATION SERVICE",Double.toString(latitude));
                        if (firebaseUser != null && databaseReference != null) {

                            Log.d(TAG,"Entered Database Entry Phase");

                            databaseReference.child(uid).child("latitude").setValue(latitude);
                            databaseReference.child(uid).child("longitude").setValue(longitude);
                            statics.currLat = latitude;
                            statics.currLong = longitude;
                            Log.d(TAG, "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAltitude: " + altitude);

                        }
                        //Log.d(TAG, "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAltitude: " + altitude);
                    }
                }
            }
        };
        initiateLocListener();
    }


    private void initiateLocListener() {
        Log.d(TAG,"Entered initiateLocListener");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback,
                    null);
        }
    }

    @Override
    public void onDestroy() {
        //remove listener
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }












}
