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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateHouseholdActivity extends AppCompatActivity {

    Button addButton, cancelButton, createButton;
    EditText nameEditText, emailEditText;
    TextView emailsTextView;

    List<String> roommateIDs;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFireBaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_household);

        //Initialize component member variables
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        createButton = findViewById(R.id.createButton);
        nameEditText = findViewById(R.id.householdNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        emailsTextView = findViewById(R.id.emailsTextView);

        roommateIDs = new ArrayList<>();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFireBaseUser = mFirebaseAuth.getCurrentUser();

        //Clear ipsum text from TextView
        emailsTextView.setText("");

        //On Click Listeners
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClicked(null);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked(null);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateClicked(null);
            }
        });

        //On creation add the current user's uid since they are a roommate
        roommateIDs.add(mFireBaseUser.getUid());
        emailsTextView.append(mFireBaseUser.getEmail());
    }

    /**
     * Cancels the creation of a new household object
     * @param view
     */
    public void onCancelClicked(View view) {
        startActivity(new Intent(CreateHouseholdActivity.this, NoHouseholdActivity.class));
    }

    /**
     * Saves list of roommates user ids
     * Updates TextView to display added emails
     * @param view
     */
    public void onAddClicked(View view) {
        final DatabaseReference emailsTableReference = mDatabaseReference.child("emails");
        final String inputEmail = emailEditText.getText().toString();
        //final String commaEmail = emailEditText.getText().toString().replace('.', ',');

        if (inputEmail.isEmpty()) {
            emailEditText.setError("Required");
            emailEditText.requestFocus();
        }
        else {
            //Check if email is in database
            //Replace all '.' with ','
            final String commaEmail = emailEditText.getText().toString().replace('.', ',');

            //emailsTableReference.child(commaEmail);


            emailsTableReference.child(commaEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    /*
                    Returns the value of the child if exists, otherwise -> null

                    dataSnapshot.toSting()
                    DataSnapshot {key = pni@gmail,com, value = u2WTwPgSZzZB0mAcniA9xLmLBnx2}
                     */

                    //emailsTextView.append(dataSnapshot.toString() + " /// ");


                    if (dataSnapshot.getValue() == null) {
                        //Child doesn't exist
                        emailEditText.getText().clear();
                        Toast.makeText(CreateHouseholdActivity.this,
                                "Email not registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Check if user ID is already in the rommateIDs list
                        if (roommateIDs.contains(dataSnapshot.getValue().toString())) {
                            emailEditText.getText().clear();
                            Toast.makeText(CreateHouseholdActivity.this,
                                    "Email already added", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //add user ID to list of user IDs in this household
                            roommateIDs.add(dataSnapshot.getValue().toString());

                            //emailsTextView.append(dataSnapshot.getValue().toString() + " ::: ");
                            //emailsTextView.append(dataSnapshot.toString() + " // ");

                            //Display the email in the text view
                            emailsTextView.append(", " + inputEmail);

                            //Clear the email input field
                            emailEditText.getText().clear();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //ERROR
                }
            });
        }
    }

    /**
     * Creates a household object
     * Adds the household object to the households table
     * Adds the household-id to each of the user's tables
     * @param view
     */
    public void onCreateClicked(View view) {

    }
}