package com.example.foodapp.Fragment;

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

import com.example.foodapp.Models.HungerSpots;
import com.example.foodapp.R;
import com.example.foodapp.helpingClasses.statics;

import java.util.ArrayList;

public class hsListAdapter extends RecyclerView.Adapter<hsListAdapter.MyViewHolder>{
    public ArrayList<HungerSpots> hungerSpotsArrayList;
    public Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,distance,dateTime;
        public RelativeLayout relativeLayout;
        public Button button;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            distance = (TextView)view.findViewById(R.id.distance);
        }

    }


    public hsListAdapter(Context mContext, ArrayList<HungerSpots> hungerSpotsArrayList) {
        this.mContext = mContext;
        this.hungerSpotsArrayList = hungerSpotsArrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slum_area_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final HungerSpots hs = hungerSpotsArrayList.get(position);
        holder.name.setText(hs.name);
        holder.distance.setText(String.valueOf(calcDistance(hs.lat,hs.lon)));
    }

    public static Double calcDistance(Double lat, Double lon) {
        float[] result = new float[1];
        Location.distanceBetween(lat,lon, statics.currLat,statics.currLong,result);
        Log.d("Distance",Float.toString(result[0]));
        return (double)Math.round(result[0]*100.0)/100.0;
    }

    @Override
    public int getItemCount() {
        return hungerSpotsArrayList.size();
    }
}
