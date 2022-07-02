package com.example.noudhar;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Creategroup extends AppCompatActivity  {
    EditText createagroup;
    Button buttongroup;
    DatabaseReference Rootref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategroup);
        createagroup = findViewById(R.id.createagroup);
        buttongroup = findViewById(R.id.buttongroup);
        Rootref=FirebaseDatabase.getInstance().getReference();

        getActionBar().setDisplayHomeAsUpEnabled(true);



        buttongroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = createagroup.getText().toString().trim();
                createnewgroup(name);
            }
        });
    }



    private void createnewgroup(String name) {
        if (name.trim().isEmpty()) {
            createagroup.setError("Please Enter a Group Name");
            createagroup.requestFocus();
        }
        else{
            Rootref.child("Groups").child(name).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Creategroup.this, "Group created successfully", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Creategroup.this, "Unable to create group Error code 1", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }






    }
}

