package com.example.indianshop.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.indianshop.Adapters.CategoryAdapter;
import com.example.indianshop.Adapters.CategoryAdapter2;
import com.example.indianshop.Adapters.ProductAdapter;
import com.example.indianshop.Modals.CategoryModal;
import com.example.indianshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Category extends Fragment {
    public Category() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseDatabase myDatabase;
        final String categories = "categories";
        ArrayList<CategoryModal> clist;
        RecyclerView catFraRec;
        CategoryAdapter2 categoryAdapter;
        GridLayoutManager layoutManager;
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        myDatabase = FirebaseDatabase.getInstance();
        catFraRec = view.findViewById(R.id.catFraRec);
        clist= new ArrayList<>();
        categoryAdapter = new CategoryAdapter2(clist,getContext());
        catFraRec.setAdapter(categoryAdapter);
        layoutManager = new GridLayoutManager(getContext(),2);
        catFraRec.setLayoutManager(layoutManager);

        myDatabase.getReference().child(categories)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            clist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                CategoryModal cmodal = dataSnapshot.getValue(CategoryModal.class);
                                clist.add(cmodal);
                            }
                            categoryAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(getContext(), "Not a single category  is there", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        return  view;
    }
}