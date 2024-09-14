package com.example.vetrinarymanagemnet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText User_Login, Login_Password;
    private Button Login_Button;
    private TextView Signup_Redirect;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }

        // Initialize UI components
        User_Login = findViewById(R.id.User_Login);

        //Set the title for the action bar, if available
        Login_Password = findViewById(R.id.Login_Password);
        Login_Button = findViewById(R.id.Login_Button);
        Signup_Redirect = findViewById(R.id.Signup_Redirect);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Set up button click listener
        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Redirect to signup screen
        Signup_Redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, signup.class));
            }
        });
    }

    private void loginUser() {
        String email = User_Login.getText().toString().trim();
        String password = Login_Password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            User_Login.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Login_Password.setError("Password is required");
            return;
        }

        // Perform Firebase authentication
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, MainActivity.class)); // Adjust to your next activity
                finish(); // Close the login activity
            } else {
                Toast.makeText(login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginError", task.getException().getMessage());
            }
        });
    }
}
