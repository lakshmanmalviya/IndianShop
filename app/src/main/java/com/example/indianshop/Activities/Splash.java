package com.example.indianshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.indianshop.R;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);

                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(Splash.this,ValidUser.class));
                    finish();
                }
            }
        };thread.start();
    }
}