package com.example.foodapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.donations;
import com.example.foodapp.R;
import com.example.foodapp.accept_volunteer;
import com.example.foodapp.helpingClasses.statics;

import java.util.ArrayList;

public class requestListAdapter extends RecyclerView.Adapter<requestListAdapter.MyViewHolder>{
    public ArrayList<donations> donationsArrayList;
    public Context mContext;


    public requestListAdapter(Context context) {
        this.mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,distance,dateTime;
        public RelativeLayout relativeLayout;
        public Button button;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            distance = (TextView)view.findViewById(R.id.distance);
            dateTime = (TextView)view.findViewById(R.id.datetime);
            relativeLayout = (RelativeLayout)view.findViewById(R.id.RelativeL);
            button = view.findViewById(R.id.volunteer);

        }

    }

    public requestListAdapter(Context mContext, ArrayList<donations> donationsArrayList) {
        this.mContext = mContext;
        this.donationsArrayList = donationsArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.donation_card, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final donations dont = donationsArrayList.get(position);
        holder.name.setText(dont.name);
        holder.dateTime.setText(dont.currentTime.toUpperCase());
        holder.distance.setText(String.valueOf(calcDistance(dont.lat,dont.lon)));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volunteerCard = new Intent(mContext, accept_volunteer.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("donation",dont);
                volunteerCard.putExtras(bundle);
                mContext.startActivity(volunteerCard);
            }
        });



    }

    public static Double calcDistance(Double lat, Double lon) {
        float[] result = new float[1];
        Location.distanceBetween(lat,lon, statics.currLat,statics.currLong,result);
        Log.d("Distance",Float.toString(result[0]));
        return (double)Math.round(result[0]*100.0)/100.0;
    }


    @Override
    public int getItemCount() {
        return donationsArrayList.size();
    }








}






