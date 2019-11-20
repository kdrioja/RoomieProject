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
    //int emailsCount;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

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
        //emailsCount = 0;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

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

    }

    public void onCancelClicked(View view) {
        startActivity(new Intent(CreateHouseholdActivity.this, NoHouseholdActivity.class));
    }

    public void onCreateClicked(View view) {

    }

    /**
     * Handles the adding of
     * @param view
     */
    public void onAddClicked(View view) {
        final DatabaseReference emailsTableReference = mDatabaseReference.child("emails");
        final String newEmail = emailEditText.getText().toString();

        if (newEmail.isEmpty()) {
            emailEditText.setError("Required");
            emailEditText.requestFocus();
        }
        else {
            //Check if email is in database
            //Replace all '.' with ','
            String emailComma = newEmail;
            emailComma.replace('.', ',');

            emailsTableReference.child(emailComma).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //email is in DB, get userID associated with it
                    String userID = dataSnapshot.getValue(String.class);

                    //add user ID to list of user IDs in this household
                    roommateIDs.add(userID);

                    //Display the email in the text view
                    if (roommateIDs.size() == 1) {
                        emailsTextView.append(newEmail);
                    }
                    else {
                        emailsTextView.append(", " + newEmail);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //email is not in DB, inform user through an error
                    emailEditText.getText().clear();
                    Toast.makeText(CreateHouseholdActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}