package com.example.myshop.model;

public class Seller extends Person
{
    public static Seller activeSeller;


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


    @Override
    public String toString()
    {
        return "Seller{" +
                "logCount=" + getLoginCount() +
                ", id=" + getId() +
                ", name= "+ getFullName() +
                ", email= "+ getEmail()+
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
