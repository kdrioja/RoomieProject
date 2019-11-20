package com.example.roomie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateHouseholdActivity extends AppCompatActivity {

    Button addButton, cancelButton, createButton;
    EditText nameEditText, emailEditText;
    TextView emailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_household);

        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        createButton = findViewById(R.id.createButton);
        nameEditText = findViewById(R.id.householdNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        emailsTextView = findViewById(R.id.emailsTextView);

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

    public void onAddClicked(View view) {

    }
}
