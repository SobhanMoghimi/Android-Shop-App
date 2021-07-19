package com.example.myshop.model;

import java.util.ArrayList;

public class Seller extends Person
{
    public static Seller activeSeller;
    private int logCount;


    int id;
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


    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    @Override
    public String toString()
    {
        return "Seller{" +
                "logCount=" + logCount +
                ", id=" + id +
                ", name= "+ super.getFullName() +
                ", email= "+super.getEmail()+
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
