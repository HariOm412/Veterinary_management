package com.example.vetrinarymanagemnet.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vetrinarymanagemnet.cliniclist;
import com.example.vetrinarymanagemnet.databinding.FragmentDashboardBinding;
import com.example.vetrinarymanagemnet.databinding.FragmentHomeBinding;
import com.example.vetrinarymanagemnet.menuclick;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
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

        if (leftIcon == null || rightIcon == null || title == null) {
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

        //from gpt to navigate to consultation
        // Trigger the consult button action
        triggerConsultButtonClick();
    }
    //from gpt to navigate to consultation
    private void triggerConsultButtonClick() {
        Intent intent = new Intent(requireContext(), cliniclist.class);
        startActivity(intent);
    }
    //already present
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
