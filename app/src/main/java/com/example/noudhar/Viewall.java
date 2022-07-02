package com.example.noudhar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Viewall extends AppCompatActivity  {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.homemenu);
        firebaseAuth=FirebaseAuth.getInstance();

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch(id){
                    case R.id.createanewgroup:
                        Intent intent=new Intent(Viewall.this,Creategroup.class);
                        startActivity(intent);
                    case R.id.balances:
                        //Shows all balances
                    case R.id.About:
                        //About us page
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent3=new Intent(Viewall.this,Login.class);
                        Toast.makeText(Viewall.this, "You are logged out", Toast.LENGTH_LONG).show();
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                }


                return true;
            }
        });
    }



}