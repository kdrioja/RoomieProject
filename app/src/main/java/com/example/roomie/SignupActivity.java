package com.example.roomie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Signup activity
 * Leads to the home page if new user is successfully created
 */

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    EditText firstName, lastName, email, password;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupButton = findViewById(R.id.signupButton);
        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstString = firstName.getText().toString();
                String lastString = lastName.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

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
                else if (!(firstString.isEmpty() && lastString.isEmpty() && emailString.isEmpty() && passwordString.isEmpty())) {
                    // no field was left empty
                    // Creates the new user account
                    mFirebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Sign up was successful!", Toast.LENGTH_SHORT).show();

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
    }
}
