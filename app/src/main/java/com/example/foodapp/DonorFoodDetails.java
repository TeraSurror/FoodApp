package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DonorFoodDetails extends AppCompatActivity {

    EditText userName;
    EditText userContact;
    EditText foodDesc;
    EditText userNote;



    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_food_details);
        mDatabase = FirebaseDatabase.getInstance().getReference("donations");

    }

    public void onPostBtnClicked(View view){

        String url = "https://foodapp-fa08e.firebaseio.com/donations.json";

        //Name
        userName = findViewById(R.id.userName);
        final String userNameIn = userName.getText().toString();

        //Contact
        userContact = findViewById(R.id.userContact);
        final String userContactIn = userContact.getText().toString();

        //Description
        foodDesc = findViewById(R.id.foodDesc);
        final String foodDescIn = foodDesc.getText().toString();

        //Note
        userNote = findViewById(R.id.userNote);
        final String userNotIn = userNote.getText().toString();

        String tet = userContactIn+userNameIn+userNotIn;
        Toast.makeText(getApplicationContext(),tet,Toast.LENGTH_SHORT).show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", userNameIn);
                params.put("contact", userContactIn);
                params.put("desc",foodDescIn);
                params.put("note",userNotIn);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this); // this = context
        queue.add(postRequest);
    }

}
