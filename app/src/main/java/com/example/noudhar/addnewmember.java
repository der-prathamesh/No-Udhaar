package com.example.noudhar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class addnewmember extends AppCompatActivity {
    Button buttonnewmember;
    EditText addmember;
    private String groupname;
    private int requestcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewmember);
        buttonnewmember=findViewById(R.id.buttonnewmember);
        addmember=findViewById(R.id.addmember);
        Intent intent=getIntent();
        groupname=intent.getStringExtra("Group Name");
        requestcode=intent.getIntExtra("Request Code",0);
    }
}