package com.example.roomie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Login activity
 * Can either lead to home page if logged in successfully or
 * Signup activity if it is a new user
 */

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFireBaseUser;
    EditText email, password;
    TextView errorMessage;
    Button signupButton, loginButton;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the instance from Firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        errorMessage = findViewById(R.id.errorMessageTextView);
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.logonButton);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFireBaseUser = mFirebaseAuth.getCurrentUser();

                if (mFireBaseUser != null) {
                    Toast.makeText(MainActivity.this, "You are logged in.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Please log in", Toast.LENGTH_SHORT).show();
                }
            }
        };


        // if login button is clicked then check email and password and proceed to home screen
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        // if signup button is clicked then we need to transition to the sign up page
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignup = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intentSignup);
            }
        });

    }
}
