package com.batuhanmete.photion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {

    private FirebaseAuth mAuth;

    private Button buttonRegister, buttonSignIn;
    private EditText emailText, passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //checking if user is already logged in

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        //connecting frontend-backend

        setContentView(R.layout.activity_login);

        //initializing button
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonSignIn = findViewById(R.id.buttonSignIn);

        //initializing editText
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        //initializing button click view
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SingupActivity.class));
            }
        });



    }


}
