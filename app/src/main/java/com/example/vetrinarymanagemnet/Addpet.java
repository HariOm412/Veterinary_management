package com.example.vetrinarymanagemnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vetrinarymanagemnet.databinding.ActivityAddpetBinding;
import com.example.vetrinarymanagemnet.ui.notifications.NotificationsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addpet extends AppCompatActivity {

    ActivityAddpetBinding binding;
    String petname, breed, gender;
    int age;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use binding correctly
        binding = ActivityAddpetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }


// Check if views are being accessed via binding
        binding.register.setOnClickListener(view -> {
            petname = binding.petname.getText().toString();
            breed = binding.Breed.getText().toString();
            gender = binding.Gender.getText().toString();

            String ageString = binding.Age.getText().toString();
            if (!petname.isEmpty() && !breed.isEmpty() && !gender.isEmpty() && !ageString.isEmpty()) {
                try {
                    age = Integer.parseInt(ageString);
                    Users users = new Users(petname, breed, gender, age);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(petname).setValue(users).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d("AddpetActivity", "Data successfully written.");
                            binding.petname.setText("");
                            binding.Breed.setText("");
                            binding.Gender.setText("");
                            binding.Age.setText("");
                            Toast.makeText(Addpet.this, "Successfully updated", Toast.LENGTH_SHORT).show();


                            // Navigate to NotificationFragment
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container, new NotificationsFragment());
                            fragmentTransaction.addToBackStack(null); // Add this transaction to the back stack
                            fragmentTransaction.commit();


                        } else {
                            Log.e("AddpetActivity", "Data write failed: " + task.getException().getMessage());
                            Toast.makeText(Addpet.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (NumberFormatException e) {
                    Toast.makeText(Addpet.this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                    Log.e("AddpetActivity", "Invalid age format: " + e.getMessage());
                }
            } else {
                Toast.makeText(Addpet.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}