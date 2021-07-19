package com.example.myshop.model;

import java.util.ArrayList;

public class Customer extends Person {

    private ArrayList<Product> bookMark;

    public Customer(String fullName, String email, String password) {
        super(fullName, email, password);
    }

    public ArrayList<Product> getBookMark() {
        return bookMark;
    }

    public void setBookMark(ArrayList<Product> bookMark) {
        this.bookMark = bookMark;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }


    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }
}
