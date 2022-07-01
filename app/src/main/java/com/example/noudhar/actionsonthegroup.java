package com.example.noudhar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actionsonthegroup extends AppCompatActivity implements View.OnClickListener {
    Button addamember,addexpense,balances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionsonthegroup);

        addamember=findViewById(R.id.addamember);
        addexpense=findViewById(R.id.addexpense);
        balances=findViewById(R.id.balances);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addamember:
                Intent intent=new Intent(this,addnewmember.class);
                startActivity(intent);
                break;
            case R.id.addexpense:
                //add expense activity
            case R.id.balances:
                //show balance amounts
        }
    }
}