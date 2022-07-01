package com.example.noudhar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class actionongroup extends AppCompatActivity {
    Button addamember,addexpense,balances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionongroup);
        addamember=findViewById(R.id.addamember);
        addexpense=findViewById(R.id.addexpense);
        balances=findViewById(R.id.balances);

    }
}