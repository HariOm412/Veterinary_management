package com.example.vetrinarymanagemnet;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vetrinarymanagemnet.databinding.ActivityUpdatepetBinding;
import com.example.vetrinarymanagemnet.ui.notifications.NotificationsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updatepet extends AppCompatActivity
{

    ActivityUpdatepetBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUpdatepetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }
        binding.updatebtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String petname = binding.petname.getText().toString();
                String breed = binding.Breed.getText().toString();
                String gender = binding.Gender.getText().toString();
                String age = binding.Age.getText().toString();

                updatepet(petname, breed, gender, age);

            }



        private void updatepet (String petname, String breed, String gender, String age)
        {

            HashMap User =new HashMap();
            User.put("petname",petname);
            User.put("breed",breed);
            User.put("gender",gender);
            User.put("age",age);

            databaseReference= FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(petname).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task)

                {
                    if(task.isSuccessful())
                    {
                        binding.petname.setText("");
                        binding.Breed.setText("");
                        binding.Gender.setText("");
                        binding.Age.setText("");
                        Toast.makeText(updatepet.this,"successfully updated",Toast.LENGTH_SHORT).show();


                        // Navigate to NotificationFragment
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container, new NotificationsFragment());
                        fragmentTransaction.addToBackStack(null); // Add this transaction to the back stack
                        fragmentTransaction.commit();

                    }
                else {
                    Toast.makeText(updatepet.this,"Failed",Toast.LENGTH_SHORT).show();

                    }
                }

            });


            }
        });
    }}