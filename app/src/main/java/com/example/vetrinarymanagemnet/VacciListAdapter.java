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

public class VacciListAdapter extends ArrayAdapter<Vacci> {

    private Location userLocation;

    public VacciListAdapter(@NonNull Context context, @NonNull List<Vacci> vaccis, Location userLocation) {
        super(context, 0, vaccis);
        this.userLocation = userLocation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Vacci vacci = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vacci_list_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.vacci_name);
        TextView addressTextView = convertView.findViewById(R.id.vacci_address);
        TextView distanceTextView = convertView.findViewById(R.id.vacci_distance);

        if (vacci!= null) {
            nameTextView.setText(vacci.getName());
            addressTextView.setText("Address: " + vacci.getAddress());

            float[] results = new float[1];
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                    vacci.getLatitude(), vacci.getLongitude(), results);
            float distance = results[0];
            distanceTextView.setText("Distance: " + String.format("%.2f", distance / 1000) + " km");
        }

        return convertView;
    }
}
