package com.example.myshop.model;

import java.util.ArrayList;

public class Customer extends Person {

    private ArrayList<Product> bookMark;
    public static Customer activeCustomer;
    public Customer(String fullName, String email, String password) {
        super(fullName, email, password);
    }

    public ArrayList<Product> getBookMark() {
        return bookMark;
    }

    public void setBookMark(ArrayList<Product> bookMark) {
        this.bookMark = bookMark;
    }

}
