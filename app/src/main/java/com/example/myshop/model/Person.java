package com.example.myshop.model;

import java.util.ArrayList;

public abstract class Person {

    private String fullName;
    private String email;
    private static int id=1;
    private String password;
    private ArrayList<Product> products;
    private int loginCount;

    public Person(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.id = id;
        id++;
    }

}
