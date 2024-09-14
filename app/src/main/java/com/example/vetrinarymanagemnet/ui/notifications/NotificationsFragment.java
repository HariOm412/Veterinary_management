package com.example.vetrinarymanagemnet.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.vetrinarymanagemnet.Addpet;
import com.example.vetrinarymanagemnet.databinding.FragmentNotificationsBinding;
import com.example.vetrinarymanagemnet.deletepet;
import com.example.vetrinarymanagemnet.login;
import com.example.vetrinarymanagemnet.menuclick;
import com.example.vetrinarymanagemnet.readpet;
import com.example.vetrinarymanagemnet.updatepet;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Hide the ActionBar
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
            }
        }

        // Initialize views from binding
        ImageView leftIcon = binding.include.leftIcon;
        ImageView rightIcon = binding.include.rightIcon;
        TextView title = binding.include.toolbarTitle;
        Button buttonAddPet = binding.buttonAddPet;
        Button buttonUpdatePet = binding.buttonUpdatePet;
        Button button_delete_pet = binding.buttonDeletePet;
        Button button_read_pet = binding.buttonReadPet;

        if (leftIcon == null || rightIcon == null || title == null || buttonAddPet == null || buttonUpdatePet == null || button_delete_pet == null) {
            Log.e("NotificationsFragment", "One or more views are not found. Check your layout IDs.");
            return;
        }

        // Click listener for the left icon to exit the app
        leftIcon.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        // Click listener for the right icon to navigate to MenuClick activity
        rightIcon.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), menuclick.class);
            startActivity(intent);
        });

        // Click listeners for buttons
        buttonAddPet.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Addpet.class);
            startActivity(intent);
        });

        buttonUpdatePet.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), updatepet.class);
            startActivity(intent);
        });

        button_read_pet.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), readpet.class);
            startActivity(intent);
        });


        button_delete_pet.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), deletepet.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
