package com.example.indianshop.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indianshop.MainActivity;
import com.example.indianshop.Modals.Users;
import com.example.indianshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends Fragment {
//    String userName, email,possword,userId;
    final String userName = "userName";
    final String email = "email";
    final String possword = "possword";
    final String userId = "userId";
    final String Users = "Users";
    FirebaseAuth mauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    ProgressDialog dialog;

        public SignUp() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EditText  inuserName,SUemail,SUpossword;
        TextView signUp;
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        inuserName = view.findViewById(R.id.inuserName);
        SUemail = view.findViewById(R.id.SUemail);
        SUpossword = view.findViewById(R.id.SUpossword);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        signUp = view.findViewById(R.id.signUp);
        dialog = new ProgressDialog(getContext());
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SUemail.getText().toString().isEmpty() ||SUpossword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "The email or possword is not provided", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.show();
                mauth.createUserWithEmailAndPassword(SUemail.getText().toString(),SUpossword.getText().toString())
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                if (task.isSuccessful()){
                                    myref = firebaseDatabase.getReference().child(Users).push();
                                    Map<String,Object> map = new HashMap<>();
                                    map.put(userName,inuserName.getText().toString());
                                    map.put(email,SUemail.getText().toString());
                                    map.put(possword,SUpossword.getText().toString());
                                    map.put(userId,myref.getKey());
                                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                         @Override
                                         public void onSuccess(Void unused) {
                                        Toast.makeText(getContext(), "Account is created", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getContext(), MainActivity.class));
                                             getActivity().finish();
                                         }
                                     });
                                }

                                else
                                {
                                     Toast.makeText(getContext(), "Creation is failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        return  view;
    }

}