package com.example.noudhar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

public class Creategroup extends AppCompatActivity implements View.OnClickListener {
    EditText createagroup;
    Button buttongroup;
    DatabaseReference mdatabase;
    List<String>Groups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategroup);
        createagroup = findViewById(R.id.createagroup);
        buttongroup = findViewById(R.id.buttongroup);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Groups");
        Groups=new ArrayList<>();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttongroup:
                createnewgroup();
        }
    }

    private void createnewgroup() {
        String name = createagroup.getText().toString().trim();
        if (name.trim().isEmpty()) {
            createagroup.setError("Please Enter a Group Name");
            createagroup.requestFocus();
            return;
        }
        else{
            Groups.add(name);
        }
        mdatabase.setValue(Groups).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Creategroup.this,"Group Created Successfully",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(Creategroup.this, "Unable to create group please try later", Toast.LENGTH_SHORT).show();
                }
        }


        });





    }
}

