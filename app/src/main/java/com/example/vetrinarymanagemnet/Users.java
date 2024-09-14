package com.example.vetrinarymanagemnet;

public class Users {
    private String petname;
    private String breed;
    private String gender;
    private int age;

    // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    public Users() {
    }

    public Users(String petname, String breed, String gender, int age) {
        this.petname = petname;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }

    // Getters and setters
    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
