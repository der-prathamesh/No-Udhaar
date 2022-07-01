package com.example.noudhar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
EditText editTextusername, editTextpassword;
Button button2;
TextView textView2;
private FirebaseAuth mAuth;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    editTextusername= findViewById(R.id.username);
    editTextpassword= findViewById(R.id.password);
    button2=findViewById(R.id.button2);
    mAuth=FirebaseAuth.getInstance();
    textView2=findViewById(R.id.textView2);
    textView2.setOnClickListener(this);
    button2.setOnClickListener(this);

        if(mAuth.getCurrentUser() !=null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView2:
                startActivity(new Intent(this,Signup.class));
                finish();
                break;
            case R.id.button2:
                button2();
        }

    }

    private void button2() {
        String username= editTextusername.getText().toString().trim();
        String password= editTextpassword.getText().toString().trim();

        if (username.isEmpty()){
            editTextusername.setError("Please Enter Username");
            editTextusername.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextpassword.setError("Please Enter Password");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextpassword.setError("Password Should be more than 6 characters");
            editTextpassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }else {
                    Toast.makeText(Login.this,"Unable to login please check credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
        }
    }