package com.example.vetrinarymanagemnet;

public class Vacci {
    private String Name;
    private String Address;
    private double latitude;
    private double longitude;

    // Default constructor required for calls to DataSnapshot.getValue(Vacci.class)
    public Vacci() {}

    public Vacci(String name, String address, double latitude, double longitude) {
        this.Name = name;
        this.Address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}