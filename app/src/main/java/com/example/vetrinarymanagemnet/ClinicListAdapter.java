package com.example.vetrinarymanagemnet;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClinicListAdapter extends ArrayAdapter<Clinic> {

    private Location userLocation;

    public ClinicListAdapter(@NonNull Context context, @NonNull List<Clinic> clinics, Location userLocation) {
        super(context, 0, clinics);
        this.userLocation = userLocation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Clinic clinic = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.clinic_list_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.clinic_name);
        TextView addressTextView = convertView.findViewById(R.id.clinic_address);
        TextView distanceTextView = convertView.findViewById(R.id.clinic_distance);

        nameTextView.setText(clinic.getName());
        addressTextView.setText("Address: " + clinic.getAddress());

        float[] results = new float[1];
        Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                clinic.getLatitude(), clinic.getLongitude(), results);
        float distance = results[0];
        distanceTextView.setText("Distance: " + String.format("%.2f", distance / 1000) + " km");

        return convertView;
    }
}
