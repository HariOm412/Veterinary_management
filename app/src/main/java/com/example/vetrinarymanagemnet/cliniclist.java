package com.example.vetrinarymanagemnet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class cliniclist extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private ListView listView;
    private FirebaseFirestore db;
    private List<Clinic> clinicList = new ArrayList<>();
    private static final String TAG = "ClinicList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliniclist);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Paws and Claws Care");
        }

        listView = findViewById(R.id.clinic_list_view);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        db = FirebaseFirestore.getInstance();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getCurrentLocationAndFetchClinic_List();
        }
    }

    private void getCurrentLocationAndFetchClinic_List() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Log.d(TAG, "Location obtained: " + location.toString());
                                fetchClinic_List(location);
                            } else {
                                Log.e(TAG, "Failed to obtain location");
                            }
                        }
                    });
        } else {
            Log.d(TAG, "Location permission not granted");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void fetchClinic_List(Location userLocation) {
        db.collection("Clinic_List")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Clinic clinic = document.toObject(Clinic.class);
                            clinicList.add(clinic);
                            Log.d(TAG, "Clinic added: " + clinic.getName());
                        }
                        Log.d(TAG, "Total clinics fetched: " + clinicList.size());
                        sortClinic_ListByProximity(userLocation);
                    } else {
                        Log.e(TAG, "Failed to fetch clinics from Firestore");
                    }
                });
    }

    private void sortClinic_ListByProximity(Location userLocation) {
        Collections.sort(clinicList, new Comparator<Clinic>() {
            @Override
            public int compare(Clinic c1, Clinic c2) {
                float[] results1 = new float[1];
                Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                        c1.getLatitude(), c1.getLongitude(), results1);
                float distanceToC1 = results1[0];

                float[] results2 = new float[1];
                Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                        c2.getLatitude(), c2.getLongitude(), results2);
                float distanceToC2 = results2[0];

                return Float.compare(distanceToC1, distanceToC2);
            }
        });

        ClinicListAdapter adapter = new ClinicListAdapter(this, clinicList, userLocation);
        listView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Location permission granted");
                getCurrentLocationAndFetchClinic_List();
            } else {
                Log.e(TAG, "Location permission denied");
            }
        }
    }
}