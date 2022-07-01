package com.example.noudhar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class Signup extends AppCompatActivity implements View.OnClickListener {
    EditText editTextname, editTextemail,editTextpassword,editTextnumber;
    TextView textView3;
    Button button3;
    FirebaseAuth fAuth;
     FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextemail=findViewById(R.id.email);
        editTextname=findViewById(R.id.name);
        editTextnumber=findViewById(R.id.number);
        editTextpassword=findViewById(R.id.password);
        textView3=findViewById(R.id.textview3);
        button3=findViewById(R.id.button3);
        mAuth=FirebaseAuth.getInstance();
        textView3.setOnClickListener(this);
        button3.setOnClickListener(this);
        fAuth=FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() !=null){
            Intent intent = new Intent(Signup.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textview3:
                startActivity(new Intent(this,Login.class));
                finish();
                break;
            case R.id.button3:
                button3();
        }
    }

    private void button3() {
        String email = editTextemail.getText().toString().trim();
        String number = editTextnumber.getText().toString().trim();
        String name = editTextname.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        if (number.isEmpty()) {
            editTextnumber.setError("Please Enter Phone Number");
            editTextnumber.requestFocus();
            return;
        }
        if (number.length() < 10) {
            editTextnumber.setError("Please enter a valid Phone number");
            editTextnumber.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextpassword.setError("Please Enter Password");
            editTextpassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextpassword.setError("Password Should be more than 6 characters");
            editTextpassword.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextemail.setError("Please Enter Email");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please Enter a valid Email Address");
            editTextemail.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            editTextname.setError("Please Enter Your Name");
            editTextname.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    User user = new User(name, number, email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Signup.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                        Intent intent1=new Intent(Signup.this, dashboard.class);
                                        startActivity(intent1);

                                    } else {
                                        Toast.makeText(Signup.this, "Unable to Register User Please try again later", Toast.LENGTH_LONG).show();
                                    }

                                }


                });
            }
        }
    });
}
}