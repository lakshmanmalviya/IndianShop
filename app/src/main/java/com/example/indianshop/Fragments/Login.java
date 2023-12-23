package com.example.indianshop.Fragments;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indianshop.MainActivity;
import com.example.indianshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
public class Login extends Fragment {
  public Login() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String Users = "Users";
        EditText SIemail,SIpossword;
        FirebaseAuth mauth;
        FirebaseDatabase firebaseDatabase;
        ProgressDialog dialog;
        TextView  signIn;
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        signIn = view.findViewById(R.id.signIn);
        SIemail = view.findViewById(R.id.SIemail);
        SIpossword = view.findViewById(R.id.SIpossword);
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Signing you ");
        dialog.setMessage("We are signing you");
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SIemail.getText().toString().isEmpty() ||SIpossword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "The email or possword is not provided", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.show();
                mauth.signInWithEmailAndPassword(SIemail.getText().toString(),SIpossword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                if (task.isSuccessful()){
                                    Intent intent= new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        if(mauth.getCurrentUser()!=null){
            getActivity().finish();
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
        }
        return view;
    }

}