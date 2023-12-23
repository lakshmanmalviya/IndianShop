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

public class InsertCategory extends AppCompatActivity {
    final String FcategoryId = "categoryId";
    final String FcategoryImage = "categoryImage";
    final String FcategoryName = "categoryName";
      ActivityResultLauncher<String> launcher;
      final String categories="categories";
     final String categoryImages="categoryImages";
     FirebaseDatabase database;
     EditText categoryName;
     Button ctBrowseImage,insertCt;
     ImageView categoryImage;
     FirebaseStorage storage;
     DatabaseReference myref;
     String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_category);
        getSupportActionBar().hide();
        categoryImage = findViewById(R.id.categoryImage);
        ctBrowseImage = findViewById(R.id.ctBrowseImage);
        insertCt = findViewById(R.id.insertCt);
        categoryName = findViewById(R.id.categoryName);
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                categoryImage.setImageURI(uri);
                final StorageReference reference = storage.getReference().child(categoryImages).child(categoryImages+new Date().getTime());
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
        ctBrowseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });

        insertCt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = database.getReference().child(categories).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(FcategoryName,categoryName.getText().toString());
                    map.put(FcategoryImage,imageUrl);
                    map.put(FcategoryId,myref.getKey());
                   myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           Toast.makeText(getApplicationContext(), "The category is inserted", Toast.LENGTH_SHORT).show();
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