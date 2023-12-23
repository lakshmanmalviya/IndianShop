package com.example.indianshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.indianshop.Fragments.DeleteOptions;
import com.example.indianshop.Fragments.Home;
import com.example.indianshop.Fragments.InsertOptions;
import com.example.indianshop.Fragments.UpdateOptions;
import com.example.indianshop.R;
import com.example.indianshop.databinding.ActivityAdminOpsBinding;

public class AdminOps extends AppCompatActivity {
    Button insert,delete,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ops);
        getSupportActionBar().hide();
        insert = findViewById(R.id.insert);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        InsertOptions insOps = new InsertOptions();
        replaceFragment(insOps);

         insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(insOps);
            }
        });
         delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new DeleteOptions());
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new UpdateOptions());
            }
        });

    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.repAllAdFrag,fragment);
        transaction.commit();
    }

}