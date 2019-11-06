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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Login activity
 * Can either lead to home page if logged in successfully or
 * Signup activity if it is a new user
 */

/**
 * TO DO: Need to implement continuous login
 * Note that onCreate() is called when the activity is first created
 * and onStart() is called after onCreate()
 *
 * also onRestart() exists
 */

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFireBaseUser;
    EditText email, password;
    TextView errorMessage, signupTextView;
    Button loginButton;

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
        //signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.logonButton);
        signupTextView = findViewById(R.id.signupTextView);

        // Hide error label
        errorMessage.setVisibility(View.INVISIBLE);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFireBaseUser = mFirebaseAuth.getCurrentUser();

                if (mFireBaseUser != null) {
                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
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
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                // First we check that all fields have been completed
                if (emailString.isEmpty()) {
                    /**
                     * TO DO: check that it is a valid email
                     */
                    email.setError("Required");
                    email.requestFocus();
                }
                else if (passwordString.isEmpty()) {
                    password.setError("Required");
                    password.requestFocus();
                }
                else if (!(emailString.isEmpty() && passwordString.isEmpty())) {
                    // no field was left empty
                    // Creates the new user account
                    mFirebaseAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Couldn't log in, please try again.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else {
                    // to catch any errors
                    Toast.makeText(MainActivity.this, "Error occurred, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        // if signup button is clicked then we need to transition to the sign up page
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignup = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intentSignup);
            }
        });
        */

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignup = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intentSignup);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
