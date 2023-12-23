package com.example.indianshop.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.indianshop.R;
import com.example.indianshop.databinding.ActivityInsertProductBinding;
import com.example.indianshop.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InsertProduct extends AppCompatActivity {
    ActivityInsertProductBinding bnd;
         final String AllProducts = "AllProducts";
         final String productsImages = "productsImages";
         FirebaseStorage storage;
         DatabaseReference myref;
         FirebaseDatabase database;
         String imageUrl ="";
         ActivityResultLauncher<String> launcher;
         final String wish = "wish";
         final String cart = "cart";
         final String productName = "productName";
         final String productPrice = "productPrice";
         final String productColor = "productColor";
         final String productCategory = "productCategory";
         final String productId = "productId";
         final String productBrand = "productBrand";
         final String productQnt = "productQnt";
         final String productDescription = "productDescription";
         final String longDescription = "longDescription";
         final String productDiscount = "productDiscount";
         final String productImage = "productImage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityInsertProductBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                bnd.insPruductImage.setImageURI(uri);
                final StorageReference reference = storage.getReference().child(productsImages).child(productsImages+new Date().getTime());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
//                             Toast.makeText(getApplicationContext(), "ImageURL Assigned", Toast.LENGTH_SHORT).show();
                                imageUrl = uri.toString();
                            }
                        });
                    }
                });
            }
        });
        bnd.prBrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });

        bnd.instPrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnd.insProductName.getText().toString().isEmpty()||bnd.insProductPrice.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = database.getReference().child(AllProducts).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(productName,bnd.insProductName.getText().toString());
                    map.put(productDescription,bnd.insProductDescription.getText().toString());
                    map.put(productBrand,bnd.insProductBrand.getText().toString());
                    map.put(productCategory,bnd.insProductCategory.getText().toString());
                    map.put(productColor,bnd.insProductColor.getText().toString());
                    map.put(productDiscount,bnd.insProductDiscount.getText().toString());
                    map.put(productQnt,bnd.insProductQnt.getText().toString());
                    map.put(productPrice,bnd.insProductPrice.getText().toString());
                    map.put(productImage,imageUrl);
                    map.put(longDescription,bnd.insLongDescription.getText().toString());
                    map.put(productId,myref.getKey());
                    map.put(wish,""+0);
                    map.put(cart,""+0);
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "The Product is inserted", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed"+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}