package com.example.vetrinarymanagemnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class menuclick extends AppCompatActivity {

    Button logout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menuclick);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }

        TextView viewProfile = findViewById(R.id.view);
        TextView addProfile = findViewById(R.id.Add);
        TextView editProfile = findViewById(R.id.Edit);
        logout = findViewById(R.id.Logout);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuclick.this, readpet.class);
                startActivity(intent);
            }
        });

        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuclick.this, Addpet.class);
                startActivity(intent);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuclick.this, updatepet.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out the user from Firebase
                mAuth.signOut();

                // Create an intent to start the login activity
                Intent intent = new Intent(menuclick.this, login.class);

                // Clear the activity stack and start the login activity
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // Finish the current activity
                finish();
            }
        });
    }
}
