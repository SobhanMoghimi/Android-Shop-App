package com.example.myshop.model;

import java.util.ArrayList;

public class Seller extends Person {

    private String phoneNumber;
    public Seller(String fullName, String email, String password, String phoneNumber) {
        super(fullName, email, password);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
