package com.example.vetrinarymanagemnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    private EditText userEmail, userPassword, usernameEditText, phoneNumberEditText;
    private Button signupButton;
    private TextView loginRedirect;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Set the title for the action bar, if available
        if (getSupportActionBar() != null) {
         getSupportActionBar().setTitle("Paws and Claws Care");
        }

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Bind UI elements
        userEmail = findViewById(R.id.User_signup);
        userPassword = findViewById(R.id.Singup_Password);
        signupButton = findViewById(R.id.Signup_Button);
        loginRedirect = findViewById(R.id.Login_Redirect);
        usernameEditText = findViewById(R.id.ownerName);
        phoneNumberEditText = findViewById(R.id.phoneNumber);

        // Set click listeners
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this, login.class));
            }
        });
    }

    private void createUser() {
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();

        // Validate username
        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email
        if (email.isEmpty()) {
            userEmail.setError("Email is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Invalid email format");
            return;
        }

        // Validate password
        if (password.isEmpty()) {
            userPassword.setError("Password is required");
            return;
        }
        if (password.length() < 8) {
            userPassword.setError("Password must be >= 8 characters");
            return;
        }

        // Validate phone number
        if (phoneNumber.isEmpty()) {
            phoneNumberEditText.setError("Phone Number cannot be empty");
            return;
        }
        if (phoneNumber.length() != 10) {
            phoneNumberEditText.setError("Phone Number must be 10 digits long");
            return;
        }

        // Create user with Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, login.class));
                        finish();
                    } else {
                        Toast.makeText(signup.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
