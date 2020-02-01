package com.example.foodapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Models.HungerSpots;
import com.example.foodapp.helpingClasses.statics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Slum_Input extends AppCompatActivity {

    HungerSpots hungerSpots;
    EditText name,radius,contact;
    Button button;
    Double radiusD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slum__input);
        name = findViewById(R.id.nameET);
        radius = findViewById(R.id.radiusET);
        contact = findViewById(R.id.contactET);
        button = findViewById(R.id.submit_area);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radiusD = Double.parseDouble(radius.getText().toString());
                hungerSpots = new HungerSpots(name.getText().toString(),contact.getText().toString(), statics.currLat,statics.currLong,radiusD);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("hunger_spots");
                databaseReference.push().setValue(hungerSpots);
            }
        });
    }
}
