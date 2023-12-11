package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
     Button b1;
     Button b2;

     EditText e1;
     EditText e2;
     FirebaseDatabase mDatabase;
     DatabaseReference mData;

     //firebase authentication
   private  FirebaseAuth fireBaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.login);
        b2 = findViewById(R.id.signup);
        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
        fireBaseAuth = FirebaseAuth.getInstance();
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, Create_account.class);
               startActivity(i);
           }
       });

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = e1.getText().toString();
               String password = e2.getText().toString();
               if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                   Toast.makeText(MainActivity.this,
                           "Please enter email and password both",
                           Toast.LENGTH_LONG).show();
               }else{
                   login(email, password);
               }
           }
       });


    }
    private void login(String email, String password){
      fireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  Intent i = new Intent(MainActivity.this, Upload_data.class);
                  startActivity(i);

              }else{
                  Toast.makeText(MainActivity.this,
                          "Not a valid user",
                          Toast.LENGTH_LONG).show();
              }
          }
      });
    }
}