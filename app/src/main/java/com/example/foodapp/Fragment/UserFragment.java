package com.example.foodapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodapp.Models.User;
import com.example.foodapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserFragment extends Fragment {
    User user;

    ProgressBar progressBar;
    Button btnTextView;
    Button btn2;
    int progress = 0;
    private OnFragmentInteractionListener mListener;
    String uid;
    User currUser;
    TextView name,contact,email;

    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

    public void getUser(){
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("users");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currUser = dataSnapshot.child(uid).getValue(User.class);
                name.setText(currUser.name);
                contact.setText(currUser.number);
                email.setText(currUser.email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user,container,false);
        name = view.findViewById(R.id.name);
        contact = view.findViewById(R.id.contact);
        email = view.findViewById(R.id.email);
        progressBar = view.findViewById(R.id.determinateBar);
        btnTextView = view.findViewById(R.id.btn);
        btn2 = view.findViewById(R.id.btn2);
        btn2.setVisibility(View.GONE);

        btnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress+=20;
                progressBar.setProgress(progress);

                if(progress == 100){
                    btnTextView.setText("Collect Certificate");
                    progress = 0;
                    btnTextView.setVisibility(View.GONE);
                    btn2.setVisibility(View.VISIBLE);
                }
            }
        });
        getUser();

    return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
