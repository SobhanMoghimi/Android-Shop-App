package com.example.myshop.model;

public class Seller extends Person
{
    public static Seller activeSeller;


    private String phoneNumber;
    private int posts;
    public Seller(String fullName, String email, String password, String phoneNumber) {
        super(fullName, email, password);
        this.phoneNumber = phoneNumber;
        posts=0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
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
