package com.example.roomie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    TextView householdTextView;
    Button logoutButton;

    String usersHouseholdID;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        householdTextView = findViewById(R.id.householdNameTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Instantiate variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();


        /*
        // Get the user's household ID from their user node

        DatabaseReference usersNodeReference = mDatabaseReference.child("users").child(mFirebaseUser.getUid()).child("household");

        usersNodeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    //Toast.makeText(HomeActivity.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                    usersHouseholdID = dataSnapshot.getValue().toString();
                }
                else {
                    usersHouseholdID = "";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //ERROR
            }
        });

        // Change householdNameTextView to the household's name
        DatabaseReference householdNodeReference = mDatabaseReference.child("households").child(usersHouseholdID).child("name");

        householdNodeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    householdTextView.setText(dataSnapshot.getValue().toString());
                }
                else {
                    householdTextView.setText("COULDN'T GET IT");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        */

        // Logging out function
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutClicked(null);
            }
        });
    }

    // Logging out of current account
    public void onLogoutClicked(View view) {
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

    public void onSettingsClicked(View view) {
        startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
    }
}