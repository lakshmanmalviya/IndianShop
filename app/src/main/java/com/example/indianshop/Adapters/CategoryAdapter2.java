package com.example.indianshop.Adapters;

import android.content.Context;
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

public class CategoryAdapter2 extends RecyclerView.Adapter<CategoryAdapter2.catHolder>{
    ArrayList<CategoryModal> clist;
    Context context;

    public CategoryAdapter2(ArrayList<CategoryModal> clist, Context context) {
        this.clist = clist;
        this.context = context;
    }

    @NonNull
    @Override
    public catHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_row_two,parent,false);
        return new  catHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull catHolder holder, int position) {
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

    class catHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView categoryName;
        public catHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.catRowImg2);
            categoryName = itemView.findViewById(R.id.catRowTv2);
        }
    }
}
