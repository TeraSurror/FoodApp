package com.example.foodapp;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.User;
import com.example.foodapp.helpingClasses.statics;

import java.util.ArrayList;

public class volunteersAdapter extends RecyclerView.Adapter<volunteersAdapter.MyViewHolder>{
    public ArrayList<User> volunteersArrayList;
    public Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,distance,contact,email;
        public RelativeLayout relativeLayout;
        public Button button;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            distance = (TextView)view.findViewById(R.id.distance);
            contact = (TextView)view.findViewById(R.id.contact);
            email = (TextView)view.findViewById(R.id.email);
            button = view.findViewById(R.id.volunteer);

        }

    }

    public volunteersAdapter(Context mContext, ArrayList<User> volunteersArrayList) {
        this.mContext = mContext;
        this.volunteersArrayList = volunteersArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteer_card, parent, false);
        return new volunteersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final User user = volunteersArrayList.get(position);
        holder.name.setText(user.name);
        holder.contact.setText(user.number.toUpperCase());
        holder.email.setText(user.email);
        holder.distance.setText(String.valueOf(calcDistance(user.latitude,user.longitude)));


    }

    public static Double calcDistance(Double lat, Double lon) {
        float[] result = new float[1];
        Location.distanceBetween(lat,lon, statics.currLat,statics.currLong,result);
        Log.d("Distance",Float.toString(result[0]));
        return (double)Math.round(result[0]*100.0)/100.0;
    }

    @Override
    public int getItemCount() {
        return volunteersArrayList.size();
    }



}
