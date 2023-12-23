package com.example.indianshop.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indianshop.Adapters.CartAdapter;
import com.example.indianshop.Adapters.CategoryAdapter;
import com.example.indianshop.Adapters.ProductAdapter;
import com.example.indianshop.Modals.CategoryModal;
import com.example.indianshop.Modals.ProductModal;
import com.example.indianshop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserCart extends Fragment {
    public UserCart() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView cancel ,okay;
        ProgressBar progressBar;
        RecyclerView  userCartRec;
        CartAdapter cartAdapter;
        LinearLayoutManager productLayout;
        ArrayList<ProductModal> plist;
        FirebaseDatabase myDatabase;
        LinearLayout clearCart;
        Dialog dialog;
        final String allAddToCart = "allAddToCart";
        View view =  inflater.inflate(R.layout.fragment_user_cart, container, false);
        userCartRec = view.findViewById(R.id.userCartRec);
        progressBar = view.findViewById(R.id.progressBar2);
        clearCart = view.findViewById(R.id.clearCart);
        myDatabase = FirebaseDatabase.getInstance();
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);
        cancel =  dialog.findViewById(R.id.cancel);
        okay = dialog.findViewById(R.id.okay);
                plist = new ArrayList<>();
        cartAdapter = new CartAdapter(plist,getContext());
        userCartRec.setAdapter(cartAdapter);
        productLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        userCartRec.setLayoutManager(productLayout);
        myDatabase.getReference().child(allAddToCart).child(FirebaseAuth.getInstance().getUid())
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            plist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                ProductModal pmodal = dataSnapshot.getValue(ProductModal.class);
                                plist.add(pmodal);
                            }
                            cartAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Not a single item   is there", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Cancelled ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child(allAddToCart).child(FirebaseAuth.getInstance().getUid()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                Toast.makeText(getContext(), "Cart is Cleared ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
       return  view;
    }
}