package com.example.vetrinarymanagemnet;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vetrinarymanagemnet.databinding.ActivityReadpetBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class readpet extends AppCompatActivity {
    ActivityReadpetBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityReadpetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }

        // Initialize Firebase reference
        reference = FirebaseDatabase.getInstance().getReference("Users");

        binding.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petname = binding.petname.getText().toString();
                if (!petname.isEmpty()) {
                    readpet(petname);
                } else {
                    Toast.makeText(readpet.this, "Please enter Petname", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void readpet(String petname) {
    reference = FirebaseDatabase.getInstance().getReference("Users");
    reference.child(petname).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {
            if(task.isSuccessful()){
                if(task.getResult().exists())
                {
                    Toast.makeText(readpet.this, "Sucessfully Read", Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot=task.getResult();
                    String Breed =String.valueOf(dataSnapshot.child("breed").getValue());
                    String gender =String.valueOf(dataSnapshot.child("gender").getValue());
                    String age =String.valueOf(dataSnapshot.child("age").getValue());
                    binding.Breed.setText(Breed);
                    binding.Gender.setText(gender);
                    binding.Age.setText(age);

                }
                else
                {
                    Toast.makeText(readpet.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(readpet.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }
}
