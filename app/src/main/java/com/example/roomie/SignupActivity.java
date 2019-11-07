package com.example.roomie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Signup activity
 * Leads to the home page if new user is successfully created
 */

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFireBaseUser;
    EditText firstName, lastName, email, password;
    Button signupButton;
    TextView signinTextView;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signinTextView = findViewById(R.id.signinTextView);
        signupButton = findViewById(R.id.signupButton);
        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);

        mFirebaseAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstString = firstName.getText().toString().trim();
                final String lastString = lastName.getText().toString().trim();
                final String emailString = email.getText().toString().trim();
                String passwordString = password.getText().toString().trim();

                mFirebaseDatabase = FirebaseDatabase.getInstance();
                mDatabaseReference = mFirebaseDatabase.getReference().child("users");

                // First we check that all fields have been completed
                if (firstString.isEmpty()) {
                    firstName.setError("Required");
                    firstName.requestFocus();
                }
                else if (lastString.isEmpty()) {
                    lastName.setError("Required");
                    lastName.requestFocus();
                }
                else if (emailString.isEmpty()) {
                    email.setError("Required");
                    email.requestFocus();
                }
                else if (passwordString.isEmpty()) {
                    password.setError("Required");
                    password.requestFocus();
                }
                else if (!(firstString.isEmpty() && lastString.isEmpty() && emailString.isEmpty() && passwordString.isEmpty())) {
                    // no field was left empty
                    // Creates the new user account
                    mFirebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Sign up was successful!", Toast.LENGTH_SHORT).show();

                                // Get newly created user
                                mFireBaseUser = mFirebaseAuth.getCurrentUser();

                                // Add user to users
                                User newUser = new User(firstString, lastString, emailString, "No chore", false, mFireBaseUser.getUid(), "");
                                mDatabaseReference.child(mFireBaseUser.getUid()).setValue(newUser);

                                // Add user's email to emails
                                //mDatabaseReference = mFirebaseDatabase.getReference().child("emails");
                                //mDatabaseReference.child("emails").child(emailString).setValue(mFireBaseUser.getUid());

                                // Go to HomeActivity
                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Sign up was unsuccessful, please try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    // to catch any errors
                    Toast.makeText(SignupActivity.this, "Error occurred, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signinTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
        });
    }
}

/**
 // Set the User's name
 // get the instance from Firebase
 mFirebaseAuth = FirebaseAuth.getInstance();
 mFireBaseUser = mFirebaseAuth.getCurrentUser();

 if (mFireBaseUser != null) {
 UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
 .setDisplayName(firstString + " " + lastString).build();

 mFireBaseUser.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
@Override
public void onComplete(@NonNull Task<Void> task) {
if (task.isSuccessful()) {
Log.d("SignupActivity", "User profile updated.");
}
}
});
 }**/