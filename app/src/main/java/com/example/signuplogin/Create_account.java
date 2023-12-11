package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Create_account extends AppCompatActivity {
     EditText e1;
     EditText e2;
     Button b1;
    //firebase authentication
    private FirebaseAuth fireBaseAuth;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
        b1 = findViewById(R.id.signup1);
        fireBaseAuth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Create_account.this,
                            "Please enter email and password both",
                            Toast.LENGTH_LONG).show();
                }else{
                    register(email, password);
                }
            }
        });
    }
    private void register(String email1, String password1){
        fireBaseAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(Create_account.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Create_account.this,
                            "Successfully added",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Create_account.this,
                            "Failure",
                            Toast.LENGTH_LONG).show();
                    Log.e("Firebase Error", task.getException().getMessage());
                }
            }
        });
    }
}