package com.example.vetrinarymanagemnet;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vetrinarymanagemnet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Variables for exit functionality
    private static final long EXIT_DELAY = 1500; // 1 second
    private boolean backPressedOnce = false; // Flag to track if back button was pressed once
    private Handler handler = new Handler(); // Handler to manage delayed tasks
    private NavController navController; // Moved here for correct usage
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    // Exit Message on Pressing Back Button
    @Override
    public void onBackPressed() {
        if (navController.getCurrentDestination() != null &&
                navController.getCurrentDestination().getId() == R.id.navigation_home) {
            if (backPressedOnce) {
                // Exit the app if back button is pressed twice within the delay
                finishAffinity(); // Close all activities and exit the app
                return;
            }
            // Show Toast message
            Toast.makeText(this, "Press back button again to exit", Toast.LENGTH_SHORT).show();

            // Set flag to true and reset after EXIT_DELAY
            backPressedOnce = true;
            handler.postDelayed(() -> backPressedOnce = false, EXIT_DELAY);
        } else {
            // Navigate back or handle default behavior for non-home pages
            super.onBackPressed();
        }
    }   // Exit message code ends here

}
