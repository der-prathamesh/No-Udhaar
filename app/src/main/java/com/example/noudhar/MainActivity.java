package com.example.noudhar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfirebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser= mfirebaseAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            //User is already logged in
            startActivity(new Intent(this,dashboard.class));
            finish();
        }

        else{
            //User is not logged in
            final String PREFS_NAME = "MyPrefsFile";

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

            if (settings.getBoolean("my_first_time", true)) {
                //the app is being launched for first time, do something
                Log.d("Comments", "First time");
                startActivity(new Intent(this,onboarding.class));

                // record the fact that the app has been started at least once
                settings.edit().putBoolean("my_first_time", false).apply();
            }
            else {
                startActivity(new Intent(this, Login.class));
                finish();
            }
        }
    }
}
