package com.example.indianshop.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indianshop.Modals.CategoryModal;
import com.example.indianshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    ArrayList<CategoryModal> clist;
    Context context;
    public CategoryAdapter(ArrayList<CategoryModal> clist, Context context) {
        this.clist = clist;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoryModal cModal = clist.get(position);
        if (cModal.getCategoryImage().isEmpty()){
            cModal.setCategoryImage("https://firebasestorage.googleapis.com/v0/b/indianshop-a5520.appspot.com/o/categoryImages%2FcategoryImages1672141889268?alt=media&token=c893daa9-4547-4378-86ff-b8b701f5ae26");
        }
        Picasso.get().load(cModal.getCategoryImage()).placeholder(R.drawable.placeholder).into(holder.categoryImage);
        holder.categoryName.setText(cModal.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return clist.size();
    }
    class CategoryHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView  categoryName;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.imageView1);
            categoryName = itemView.findViewById(R.id.ctRowTv);
        }
    }
}
