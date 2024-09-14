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

public class SpasListAdapter extends ArrayAdapter<Spa> {

    private Location userLocation;

    public SpasListAdapter(@NonNull Context context, @NonNull List<Spa> spas, Location userLocation) {
        super(context, 0, spas);
        this.userLocation = userLocation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Spa spa = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spa_list_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.spa_name);
        TextView addressTextView = convertView.findViewById(R.id.spa_address);
        TextView distanceTextView = convertView.findViewById(R.id.spa_distance);

        if (spa != null) {
            nameTextView.setText(spa.getName());
            addressTextView.setText("Address: " + spa.getAddress());

            float[] results = new float[1];
            Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                    spa.getLatitude(), spa.getLongitude(), results);
            float distance = results[0];
            distanceTextView.setText("Distance: " + String.format("%.2f", distance / 1000) + " km");
        }

        return convertView;
    }
}
