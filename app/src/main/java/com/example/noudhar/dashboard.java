package com.example.noudhar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.net.Inet4Address;

public class dashboard extends AppCompatActivity{
Button createnew;
Button seeall;
Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        createnew = findViewById(R.id.createnew);
        seeall = findViewById(R.id.seeall);
        signout=findViewById(R.id.signout);


        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Creategroup.class);
                startActivity(intent);
            }
        });

        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(dashboard.this,Viewall.class);
                startActivity(intent1);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(dashboard.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
