package com.example.roomie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView householdTextView;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Instantiate variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        householdTextView = findViewById(R.id.householdNameTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Logging out function
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // Testing that the display name update was successful in SignupActivity
        Toast.makeText(HomeActivity.this, mFirebaseUser.getDisplayName(), Toast.LENGTH_LONG);

    }

    // Logging out of current account
    protected void logout() {
        mFirebaseAuth.signOut();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            Toast.makeText(HomeActivity.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
        else {
            Toast.makeText(HomeActivity.this, "Logged out unsuccessful, please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}

/**
 log out button
 //member vars

 FirebaseAuth mFirebaseAuth;
 private FirebaseAuth.AuthStateListener mAuthStateListener;

 create on click listener

 FirebaseAuth.getInstance().signOut();
 startActivity(new Intent(HomeActivity.this, MainActivity.class);
 }
 **/