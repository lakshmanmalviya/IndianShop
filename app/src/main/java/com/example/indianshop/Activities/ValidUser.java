package com.example.indianshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.indianshop.Fragments.Login;
import com.example.indianshop.Fragments.SignUp;
import com.example.indianshop.R;

public class ValidUser extends AppCompatActivity {
   LinearLayout SISignUp,SISignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_user);
        getSupportActionBar().hide();
         replaceFragment(new Login());
        SISignUp = findViewById(R.id.SISignUp);
        SISignIn = findViewById(R.id.SISignIn);
        SISignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           replaceFragment(new SignUp());
            }
        });
        SISignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Login());

            }
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.repFragIU,fragment);
        transaction.commit();
    }
    public void onBackPressed() {
        System.exit(0);
    }
}