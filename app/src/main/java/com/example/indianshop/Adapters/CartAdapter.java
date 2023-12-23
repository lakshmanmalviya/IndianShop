package com.example.indianshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indianshop.Modals.ProductModal;
import com.example.indianshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    ArrayList<ProductModal> plist;
    Context context;

    public CartAdapter(ArrayList<ProductModal> plist, Context context) {
        this.plist = plist;
        this.context = context;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_cart_row,parent,false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        ProductModal pModal = plist.get(position);

        if (pModal.getProductImage().isEmpty()){
            pModal.setProductImage("https://firebasestorage.googleapis.com/v0/b/indianshop-a5520.appspot.com/o/categoryImages%2FcategoryImages1672141889268?alt=media&token=c893daa9-4547-4378-86ff-b8b701f5ae26");
        }
        Picasso.get().load(pModal.getProductImage()).placeholder(R.drawable.placeholder).into(holder.productImage);
        holder.productName.setText(pModal.getProductName());
        holder.productBrand.setText(pModal.getProductBrand());
        holder.productPrice.setText(pModal.getProductPrice());
        holder.productDescription.setText(pModal.getProductDescription());
        holder.productDiscount.setText(pModal.getProductDiscount()+"%");
    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    class CartHolder extends RecyclerView.ViewHolder{
        ImageView productImage,share,minus,plus;
        TextView productPrice,productBrand,productName,productDescription,buyNow,productDiscount,qnt;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.UCproductImage);
            productPrice = itemView.findViewById(R.id.UCprice);
            productBrand = itemView.findViewById(R.id.UCproductBrName);
            productName = itemView.findViewById(R.id.UCproductName);
            productDescription = itemView.findViewById(R.id.UCproductDes);
            share = itemView.findViewById(R.id.UCshare);
            buyNow = itemView.findViewById(R.id.UCbuy);
            productDiscount = itemView.findViewById(R.id.UCdiscount);
            minus = itemView.findViewById(R.id.UCminus);
            plus = itemView.findViewById(R.id.UCplus);
            qnt= itemView.findViewById(R.id.UCqnt);
            buyNow= itemView.findViewById(R.id.UCbuy);

        }
    }
}
