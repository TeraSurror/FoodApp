package com.example.foodapp.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.donations;
import com.example.foodapp.R;

import java.util.ArrayList;

public class requestListAdapter extends RecyclerView.Adapter<requestListAdapter.MyViewHolder>{
    public ArrayList<donations> donationsArrayList;
    public Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,distance,dateTime;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            //distance = (TextView)view.findViewById(R.id.distance);
            dateTime = (TextView)view.findViewById(R.id.datetime);
            relativeLayout = (RelativeLayout)view.findViewById(R.id.RelativeL);
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final donations dont = donationsArrayList.get(position);
        holder.name.setText(dont.name);
        holder.dateTime.setText(dont.currentTime.toUpperCase());
        //holder.distance.setText(Float.toString(floc.distance)+"m");
        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationDailog(holder,floc);
            }
        });*/



    }


    @Override
    public int getItemCount() {
        return donationsArrayList.size();
    }








}






