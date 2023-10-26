package com.example.kilimo;

public class User {
    private String first_name;
    private String last_name;
    private String ID_Number;
    private String farm_number;
    private String center;
    private int phone_number;
    private String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String first_name, String last_name, String ID_Number, String farm_number, String center, int phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.ID_Number = ID_Number;
        this.farm_number = farm_number;
        this.center = center;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getID_Number() {
        return ID_Number;
    }

    public String getFarm_number() {
        return farm_number;
    }

    public String getCenter() {
        return center;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone_Number() {
        return phone_number;
    }
}
