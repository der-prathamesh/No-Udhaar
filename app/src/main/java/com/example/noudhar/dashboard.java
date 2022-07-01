package com.example.noudhar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dashboard extends AppCompatActivity implements View.OnClickListener{
Button createnew;
Button seeall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        createnew = findViewById(R.id.createnew);
        seeall = findViewById(R.id.seeall);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createnew:
                Intent intent = new Intent(this, Creategroup.class);
                startActivity(intent);
                break;

            case  R.id.seeall:
                Intent intent1=new Intent(this,Signup.class);
                startActivity(intent1);
                break;
        }
    }
}
