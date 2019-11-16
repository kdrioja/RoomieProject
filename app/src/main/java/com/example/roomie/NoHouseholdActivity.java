package com.example.roomie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NoHouseholdActivity extends AppCompatActivity {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_household);

        //Instantiate Variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    public void settingsClicked(View view) {
        startActivity(new Intent(NoHouseholdActivity.this, SettingsActivity.class));
    }

    public void createHousehold(View view) {
        startActivity(new Intent(NoHouseholdActivity.this, CreateHouseholdActivity.class));
    }

    public void logoutClicked(View view) {
        mFirebaseAuth.signOut();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            Toast.makeText(NoHouseholdActivity.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NoHouseholdActivity.this, LoginActivity.class));
        }
        else {
            Toast.makeText(NoHouseholdActivity.this, "Logout unsuccessful, please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}