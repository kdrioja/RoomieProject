package com.example.roomie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    TextView householdTextView;
    Button logoutButton;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        householdTextView = findViewById(R.id.householdNameTextLabel);
        logoutButton = findViewById(R.id.logoutButton);

        // Logging out function
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(null);
            }
        });

        // Instantiate variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();


    }

    //private void writeNewUser()

    // Logging out of current account
    public void logout(View view) {
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

 ---------------------------------------------------------------------------------------------------

 // Testing that the display name update was successful in SignupActivity
 //Toast.makeText(HomeActivity.this, mFirebaseUser.getDisplayName(), Toast.LENGTH_LONG).show();
 // ^^ It did  not do it


 // Testing out adding data to the database

User testUser = new User("Test", "User", "tu@gmai.com");
//mDatabase.child("users").child("1").setValue(testUser);
        mDatabaseReference.setValue(testUser);
 **/