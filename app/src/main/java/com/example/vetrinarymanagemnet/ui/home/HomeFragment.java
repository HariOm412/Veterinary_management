package com.example.vetrinarymanagemnet.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.vetrinarymanagemnet.R;
import com.example.vetrinarymanagemnet.Vaccilist;
import com.example.vetrinarymanagemnet.cliniclist;
import com.example.vetrinarymanagemnet.databinding.FragmentHomeBinding;
import com.example.vetrinarymanagemnet.Spalist;
import com.example.vetrinarymanagemnet.menuclick;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
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

        // Initialize views
        ImageView leftIcon = binding.include.leftIcon;
        ImageView rightIcon = binding.include.rightIcon;
        TextView title = binding.include.toolbarTitle;
        ImageButton consult = binding.consult; // Initialize the "Consult" ImageButton
        ImageButton groom = binding.groom; // Initialize the "Groom" ImageButton
        ImageButton vacci = binding.vacci; // Initialize the "Vacci" ImageButton

        if (leftIcon == null || rightIcon == null || title == null || consult == null || groom == null) {
            Log.e("HomeFragment", "One or more views are not found. Check your layout IDs.");
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

        // Click listener for the consult button to navigate to ClinicList activity
        consult.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), cliniclist.class);
            startActivity(intent);
        });

        // Click listener for the groom button to navigate to SpaList activity
        groom.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Spalist.class);
            startActivity(intent);
        });

        // Click listener for the vacci button to navigate to SpaList activity
        vacci.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Vaccilist.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
