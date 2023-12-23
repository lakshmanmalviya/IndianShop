package com.example.indianshop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indianshop.Activities.InsertCategory;
import com.example.indianshop.Activities.InsertProduct;
import com.example.indianshop.R;

public class InsertOptions extends Fragment {
    public InsertOptions() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView insertCategories,insertProducts;
        View view =  inflater.inflate(R.layout.fragment_insert_options, container, false);
        insertCategories = view.findViewById(R.id.insertCategories);
        insertProducts = view.findViewById(R.id.insertProducts);
        insertCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InsertCategory.class));
            }
        });
        insertProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InsertProduct.class));
            }
        });
      return view;
    }
}