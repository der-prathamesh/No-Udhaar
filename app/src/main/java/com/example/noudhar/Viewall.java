package com.example.noudhar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Database;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Viewall extends AppCompatActivity  {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private FirebaseAuth firebaseAuth;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String>allgroups=new ArrayList<>();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.homemenu);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Groups");
        firebaseAuth=FirebaseAuth.getInstance();
        listView=findViewById(R.id.listview);
        arrayAdapter=new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,allgroups);
        listView.setAdapter(arrayAdapter);


        Showallgroups();


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

    private void Showallgroups() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Set<String>set=new HashSet<>();
                Iterator iterator=snapshot.getChildren().iterator();

                while (iterator.hasNext()){
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                allgroups.clear();
                allgroups.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}