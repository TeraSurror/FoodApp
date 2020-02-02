package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Models.donations;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.foodapp.Fragment.requestListAdapter.calcDistance;

public class accept_volunteer extends AppCompatActivity {

    TextView a,b,c,d,e;
    Button volunteer;
    DatabaseReference reef;
    donations currDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_volunteer);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null)
            currDon = (donations)bundle.getSerializable("donation");

        a = (TextView)findViewById(R.id.donor_name1);
        b = (TextView)findViewById(R.id.donor_number1);
        c = (TextView)findViewById(R.id.food_description1);
        d = (TextView)findViewById(R.id.time_stamp1);
        e = (TextView)findViewById(R.id.donor_location1);

        a.setText(currDon.name);
        b.setText(currDon.contact);
        c.setText(currDon.desc);
        d.setText(currDon.currentTime);
        e.setText(String.valueOf(calcDistance(currDon.lat,currDon.lon)));

        volunteer = (Button)findViewById(R.id.volunteer);

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference()
                        .child("active_donations").child(currDon.userid).child("volunteers");
                String key = dbref.push().getKey();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                dbref.push().setValue(uid);
                finish();
            }
        });

        }

    }

