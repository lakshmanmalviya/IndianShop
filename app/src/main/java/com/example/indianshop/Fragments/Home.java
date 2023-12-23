package com.example.indianshop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.indianshop.Adapters.CategoryAdapter;
import com.example.indianshop.Adapters.ProductAdapter;
import com.example.indianshop.Classes.RecyclerItemClickListener;
import com.example.indianshop.Modals.CategoryModal;
import com.example.indianshop.Modals.ProductModal;
import com.example.indianshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends Fragment {
 public Home() {}
 ProgressBar progressBar;
  RecyclerView  categoryRec;
  RecyclerView  productRec;
   CategoryAdapter categoryAdapter;
   ProductAdapter productAdapter;
   LinearLayoutManager layoutManager;
   LinearLayoutManager productLayout;
   ArrayList<CategoryModal> clist;
   ArrayList<ProductModal> plist;
   FirebaseDatabase myDatabase;
   final String categories = "categories";
   final String AllProducts = "AllProducts";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRec = view.findViewById(R.id.categoryRec);
        productRec = view.findViewById(R.id.productRec);
        progressBar = view.findViewById(R.id.progressBar);
        myDatabase = FirebaseDatabase.getInstance();
        clist= new ArrayList<>();

        plist = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(clist,getContext());
        productAdapter = new ProductAdapter(plist,getContext());
        categoryRec.setAdapter(categoryAdapter);
        productRec.setAdapter(productAdapter);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        productLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        categoryRec.setLayoutManager(layoutManager);
        productRec.setLayoutManager(productLayout);
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
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Not a category  is there", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        myDatabase.getReference().child(AllProducts)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                           plist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                ProductModal pmodal = dataSnapshot.getValue(ProductModal.class);
                                plist.add(pmodal);
                            }
                            productAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                        Toast.makeText(getContext(), "Not a category  is there", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

//        productRec.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), productRec, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ProductModal pmodal = plist.get(position);
//                Intent intent = new Intent(getContext(),ReadSentences.class);
//                intent.putExtra(lessonKey,pmodal.getLessonId());
////                Toast.makeText(getApplicationContext(), pmodal.getLessonId()+" clicked "+position, Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//            @Override
//            public void onLongItemClick(View view, int position) {
//            }
//        }));

        return view;
    }
}