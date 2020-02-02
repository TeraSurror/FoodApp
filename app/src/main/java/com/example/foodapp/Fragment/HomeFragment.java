package com.example.foodapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.DonorFoodDetails;
import com.example.foodapp.Models.donations;
import com.example.foodapp.PendingDonations;
import com.example.foodapp.R;
import com.example.foodapp.helpingClasses.statics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button donateButton,pendingDonateButton;
    private requestListAdapter adapter;
    private ArrayList<donations> donationsArrayList;
    private DatabaseReference dbref;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {}


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        donateButton = view.findViewById(R.id.donate);
        pendingDonateButton = view.findViewById(R.id.pendinDonate);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DonorFoodDetails.class));
            }
        });
        pendingDonateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PendingDonations.class));
            }
        });

        donationsArrayList = new ArrayList<>();
        setdonationsArrayList();


        return view;
    }

    public void setdonationsArrayList(){
        donationsArrayList.clear();
        dbref = FirebaseDatabase.getInstance().getReference().child("donations");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationsArrayList.clear();
                for(DataSnapshot temp : dataSnapshot.getChildren()){
                    donations don = temp.getValue(donations.class);
                    don.distance = calcDistance(don.lat,don.lon);
                    if(don.distance <= 5000)
                        donationsArrayList.add(don);
                }
                setCustomAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static Double calcDistance(Double lat, Double lon) {
        float[] result = new float[1];
        Location.distanceBetween(lat,lon, statics.currLat,statics.currLong,result);
        Log.d("Distance",Float.toString(result[0]));
        return (double)Math.round(result[0]*100.0)/100.0;
    }


    public void setCustomAdapter(){
        for(int i=0;i<donationsArrayList.size();i++)
            Log.d("Home Fragment",donationsArrayList.get(i).name);
        adapter = new requestListAdapter(getActivity(),donationsArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
