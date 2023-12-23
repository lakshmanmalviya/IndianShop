package com.example.indianshop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.indianshop.Activities.ValidUser;
import com.example.indianshop.R;
import com.google.firebase.auth.FirebaseAuth;

public class User extends Fragment {
    FirebaseAuth mauth;
    public User() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button logout;
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        mauth = FirebaseAuth.getInstance();
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signOut();
                startActivity(new Intent(getContext(),ValidUser.class));
            }
        });
        return  view;
    }
}