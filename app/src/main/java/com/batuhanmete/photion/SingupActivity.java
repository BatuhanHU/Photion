package com.batuhanmete.photion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SingupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button buttonRegister;
    private EditText emailText, passwordText, passwordText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        // [START initialize auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize auth]



        //connecting frontend-backend

        setContentView(R.layout.activity_singup);

        //initializing button
        buttonRegister = findViewById(R.id.buttonRegister);

        //initializing editText
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        passwordText2 = findViewById(R.id.passwordText2);


        //initializing button click view
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }

    // [START on_start_check_user]
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and open main page if signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(SingupActivity.this, MainActivity.class));
            finish();
        }
    }
    // [END on_start_check_user]



    private void register(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String password2 = passwordText2.getText().toString();


        //check password match
        if(! password.equals(password2)){
            Toast.makeText(SingupActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            passwordText.setText("");
            passwordText2.setText("");
        }
        else if(email.equals("") || password.equals("")){
            Toast.makeText(SingupActivity.this, "Please fill all  boxes", Toast.LENGTH_SHORT).show();

        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign up success, open the login page
                                startActivity(new Intent(SingupActivity.this, MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(SingupActivity.this, "An error occurred. Please try again", Toast.LENGTH_SHORT).show();
                                emailText.setText("");
                                passwordText.setText("");
                                passwordText2.setText("");

                            }

                        }
                    });
        }
    }






}
