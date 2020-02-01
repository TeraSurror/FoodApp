package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DonorFoodDetails extends AppCompatActivity {

    LinearLayout inputListWrapper;
    LinearLayout inputItem;
    Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_food_details);


        addItemButton = findViewById(R.id.addItem);

    }

    public void onAddItemClick (View view) {
        inputListWrapper = findViewById(R.id.InputWrapper);
        inputItem  = findViewById(R.id.inputItem);

        inputListWrapper.addView(inputItem);

    }
}
