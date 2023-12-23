package com.example.indianshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indianshop.Activities.ProductDetail;
import com.example.indianshop.Modals.ProductModal;
import com.example.indianshop.Modals.Users;
import com.example.indianshop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
     ArrayList<ProductModal> plist;
     Context context;
     int cart = 0;
   final String allAddToCart = "allAddToCart";
   FirebaseDatabase database;
   FirebaseAuth mauth;
//    final String wish = "wish";
    final String productPrice = "productPrice";
    final String productName = "productName";
    final String productColor = "productColor";
    final String productCategory = "productCategory";
    final String productId = "productId";
    final String productBrand = "productBrand";
    final String productQnt = "productQnt";
    final String productDescription = "productDescription";
    final String longDescription = "longDescription";
    final String productDiscount = "productDiscount";
    final String productImage = "productImage";
    public ProductAdapter(ArrayList<ProductModal> plist, Context context) {
        this.plist = plist;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_row,parent,false);
        database = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        return new ProductHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        ProductModal pModal = plist.get(position);

        if (pModal.getProductImage().isEmpty()){
            pModal.setProductImage("https://firebasestorage.googleapis.com/v0/b/indianshop-a5520.appspot.com/o/categoryImages%2FcategoryImages1672141889268?alt=media&token=c893daa9-4547-4378-86ff-b8b701f5ae26");
        }
        Picasso.get().load(pModal.getProductImage()).placeholder(R.drawable.placeholder).into(holder.productImage);
        holder.productName.setText(pModal.getProductName());
        holder.productBrand.setText(pModal.getProductBrand());
        holder.productPrice.setText(pModal.getProductPrice());
        holder.productDescription.setText(pModal.getProductDescription());
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ProductModal productModal = plist.get(position);
                    Map<String, Object> map = new HashMap<>();
                    map.put(productName, productModal.getProductName());
                    map.put(productPrice, productModal.getProductPrice());
                    map.put(productId, productModal.getProductId());
                    map.put(productQnt, productModal.getProductQnt());
                    map.put(productColor, productModal.getProductColor());
                    map.put(productDiscount, productModal.getProductDiscount());
                    map.put(productDescription, productModal.getProductDescription());
                    map.put(productCategory, productModal.getProductCategory());
                    map.put(productBrand, productModal.getProductBrand());
                    map.put(productImage, productModal.getProductImage());
                    database.getReference().child(allAddToCart).child(mauth.getCurrentUser().getUid()).push().setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(v.getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
//                Toast.makeText(v.getContext(), "Already exits", Toast.LENGTH_SHORT).show();
        });
        holder.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = plist.indexOf(pModal);
//                Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), ProductDetail.class);
                intent.putExtra(productName,pModal.getProductName());
                intent.putExtra(productPrice,pModal.getProductPrice());
                intent.putExtra(productBrand,pModal.getProductBrand());
                intent.putExtra(productColor,pModal.getProductColor());
                intent.putExtra(productDescription,pModal.getProductDescription());
                intent.putExtra(longDescription,pModal.getLongDescription());
                intent.putExtra(productDiscount,pModal.getProductDiscount());
                intent.putExtra(productId,pModal.getProductId());
                intent.putExtra(productQnt,pModal.getProductQnt());
                intent.putExtra(productImage,pModal.getProductImage());
                intent.putExtra("index",i+"");
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return plist.size();
    }
    class ProductHolder extends RecyclerView.ViewHolder{
          ImageView productImage;
          TextView productPrice,productBrand,productName,productDescription,addToCart,buyNow;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImageTv);
            productPrice = itemView.findViewById(R.id.productPriceTv);
            productBrand = itemView.findViewById(R.id.productBrNameTv);
            productName = itemView.findViewById(R.id.productNameTv);
            productDescription = itemView.findViewById(R.id.productDesTv);
            addToCart = itemView.findViewById(R.id.productCartTv);
            buyNow = itemView.findViewById(R.id.productBuyTv);
        }

    }

}
