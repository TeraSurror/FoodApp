package com.example.foodapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonorFoodDetails extends AppCompatActivity {

    LinearLayout inputListWrapper;
    LinearLayout inputItem;
    Button addItemButton;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_food_details);


        addItemButton = findViewById(R.id.addItem);

    }

    public void onAddItemClick (View view) {

        inputListWrapper = findViewById(R.id.inputWrapper);
        layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View foodItem = layoutInflater.inflate(R.layout.fooditem,null);
        inputListWrapper.addView(foodItem);

        Toast.makeText(getApplicationContext(),"Hello Javatpoint", Toast.LENGTH_SHORT).show();

    }
}
