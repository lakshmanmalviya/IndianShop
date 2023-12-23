package com.example.indianshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.indianshop.Activities.AdminOps;
import com.example.indianshop.Fragments.Category;
import com.example.indianshop.Fragments.Home;
import com.example.indianshop.Fragments.UserCart;
import com.example.indianshop.Fragments.User;
import com.example.indianshop.databinding.ActivityMainBinding;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    private long pressedTime;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        Home frone = new Home();
        replaceFragment(frone);
        binding.voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminOps.class));
            }
        });

        binding.bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int tabIndex, @NonNull AnimatedBottomBar.Tab tab1)
            {
                switch(tabIndex){
                    case 0:
                        replaceFragment(frone);
                        break;
                    case 1:
                        replaceFragment( new User());
                        break;
                    case 2:
                        replaceFragment(frone);
                        break;
                    case 3:
                        replaceFragment( new UserCart());
                        break;
                    case 4:
                        replaceFragment(new Category());
                        break;
                    default:
                        replaceFragment(new Home());
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.repAllLayout,fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
         if (pressedTime+2000>System.currentTimeMillis()){
             super.onBackPressed();
             finish();
         }
         else{
             Toast.makeText(getApplicationContext(), "Press Again to Exit", Toast.LENGTH_SHORT).show();
         }
         pressedTime = System.currentTimeMillis();

    }
}