package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendingDonations extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.foodapp.volunteersAdapter adapter;
    private ArrayList<User> volunteersArrayList;
    private DatabaseReference dbref;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_donations);
        recyclerView = findViewById(R.id.my_recycler_view);
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        volunteersArrayList = new ArrayList<>();
        setvolunteerArrayList();
    }

    public void setvolunteerArrayList(){
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot temp : dataSnapshot.getChildren()){
                    String tempValue = (String)temp.getValue();
                    Log.d("volunteer Array",tempValue);
                    //volunteersArrayList.add(tempValue);
                }
                setCustomAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public User getUser(final String uid){
        final User[] user = new User[1];
        DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("users");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user[0] = dataSnapshot.child(uid).getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return user[0];
    }

    public void setCustomAdapter(){
        if(volunteersArrayList!=null) {
            for (int i = 0; i < volunteersArrayList.size(); i++)
                Log.d("Home Fragment", volunteersArrayList.get(i).name);
            adapter = new com.example.foodapp.volunteersAdapter(this, volunteersArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

    }
}
