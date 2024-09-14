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

import com.example.vetrinarymanagemnet.databinding.ActivityDeletepetBinding;
import com.example.vetrinarymanagemnet.databinding.ActivityUpdatepetBinding;
import com.example.vetrinarymanagemnet.ui.notifications.NotificationsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deletepet extends AppCompatActivity {

    ActivityDeletepetBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityDeletepetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String petname=binding.petname.getText().toString();
                if (!petname.isEmpty())
                {
                    deletepet(petname);

                }
                else
                {
                    Toast.makeText(deletepet.this, "Enter the username", Toast.LENGTH_SHORT).show();
                }
            }
            private void deletepet(String petname){
                reference= FirebaseDatabase.getInstance().getReference("Users");
                reference.child(petname).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(deletepet.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                            binding.delete.setText("");

                            // Navigate to NotificationFragment
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container, new NotificationsFragment());
                            fragmentTransaction.addToBackStack(null); // Add this transaction to the back stack
                            fragmentTransaction.commit();
                        }

                        else {
                            Toast.makeText(deletepet.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}