package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Upload_data extends AppCompatActivity {
   EditText et1;
   EditText et2;
   TextView tv;
   Button but;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        et1 = findViewById(R.id.editText3);
        et2 = findViewById(R.id.editText4);
        tv = findViewById(R.id.tview);
        but = findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hm = new HashMap<>();
                hm.put("Name: ", et1.getText().toString());
                hm.put("Marks: ", et2.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Student").push().setValue(hm);
            }
        });
    }
}