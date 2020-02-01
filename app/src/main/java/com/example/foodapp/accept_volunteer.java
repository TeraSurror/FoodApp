package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Models.donations;
import com.google.firebase.database.DatabaseReference;

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

        /*volunteer.setOnClickListener(new View.OnClickListener() {
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


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        public static Double calcDistance(Double lat, Double lon) {
            float[] result = new float[1];
            Location.distanceBetween(lat,lon, statics.currLat,statics.currLong,result);
            Log.d("Distance",Float.toString(result[0]));
            return (double)Math.round(result[0]*100.0)/100.0;
        }*/

    }
}
