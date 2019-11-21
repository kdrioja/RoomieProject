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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    TextView errorMessage, signupTextView;
    Button loginButton;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFireBaseUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize member variables
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        errorMessage = findViewById(R.id.errorMessageTextView);
        loginButton = findViewById(R.id.logonButton);
        signupTextView = findViewById(R.id.signupTextView);

        // Hide error label
        errorMessage.setVisibility(View.INVISIBLE);

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                // Check that all fields have been completed
                if (emailString.isEmpty()) {
                    email.setError("Required");
                    email.requestFocus();
                }
                else if (passwordString.isEmpty()) {
                    password.setError("Required");
                    password.requestFocus();
                }
                else if (!(emailString.isEmpty() && passwordString.isEmpty())) {

                    // Creates the new user account
                    mFirebaseAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Couldn't log in, please try again.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Sign in is successful, check if the user has a household-id value
                            mFireBaseUser = mFirebaseAuth.getCurrentUser();

                            DatabaseReference usersTableReference = mDatabaseReference.child("users").child(mFireBaseUser.getUid());

                            usersTableReference.child("household").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null && dataSnapshot.getValue().toString().length() != 0) {
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    }
                                    else {
                                        startActivity(new Intent(LoginActivity.this, NoHouseholdActivity.class));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    //ERROR
                                }
                            });
                        }
                        }
                    });
                }
                else {
                    // to catch any errors
                    Toast.makeText(LoginActivity.this, "Error occurred, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }*/
}