package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class accept_volunteer extends AppCompatActivity {

    TextView a,b,c,d,e;
    Button volunteer;
    DatabaseReference reef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_volunteer);

        a = (TextView)findViewById(R.id.donor_name1);
        b = (TextView)findViewById(R.id.donor_number1);
        c = (TextView)findViewById(R.id.food_description1);
        d = (TextView)findViewById(R.id.time_stamp1);
        e = (TextView)findViewById(R.id.donor_location1);

        volunteer = (Button)findViewById(R.id.volunteer);

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reef = FirebaseDatabase.getInstance().getReference().child("donations").child("121519451");
                reef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String number = dataSnapshot.child("contact").getValue().toString();
                        String details = dataSnapshot.child("desc").getValue().toString();
                        String time = dataSnapshot.child("currentTime").getValue().toString();
                        String location = dataSnapshot.child("lat").getValue().toString();

                        a.setText(name);
                        b.setText(number);
                        c.setText(details);
                        d.setText(time);
                        e.setText(location);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
