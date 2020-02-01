package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.minorproject.android.esrf.Helping_Classes.statics;

public class register extends AppCompatActivity {
    EditText name,email,number;
    //TextView warning;
    Button submit;
    User user;
    DatabaseReference dbref;
    FirebaseUser mAuth;
    String uid;
    ScrollView scrollView;
    public static final String TAG = "register.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataToFb();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //SharedPreferences pref = getApplicationContext().getSharedPreferences("tokens", 0); // 0 - for private mode
        //statics.token = pref.getString("token_value",null);
        //DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
        //dbref.child("users").child(uid).child("token").setValue(statics.token);
    }

    void init(){
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        number = (EditText)findViewById(R.id.number);
        submit = (Button)findViewById(R.id.submit);
        //warning =(TextView)findViewById(R.id.warning);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    User bindUser(){
        String n = name.getText().toString();
        String e = email.getText().toString();
        String num = number.getText().toString();
        User user1 = new User(n,e,0.0,0.0,25.0,num);

        return user1;
    }

    void submitDataToFb(){
        if(isFilled())
        {
            user = bindUser();
            dbref = FirebaseDatabase.getInstance().getReference();
            dbref.child("users").child(uid).setValue(user);
            Log.d(TAG, "Entered submitDataToFb");
            Intent intent = new Intent(register.this, MainActivity.class);
            intent.putExtra("curruser", user);
            startActivity(intent);
        }
        else{

            //warning.setVisibility(View.VISIBLE);
            scrollView.smoothScrollTo(0,0);

        }
    }

    boolean isFilled(){
        if(TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(email.getText()) ||TextUtils.isEmpty(number.getText()))
            return false;
        else
            return true;

    }





}
