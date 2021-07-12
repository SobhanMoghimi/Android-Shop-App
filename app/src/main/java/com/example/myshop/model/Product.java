package com.example.myshop.model;

import java.util.Date;

public class Product {

    private String name;
    //private static int id=1;
    private int price;
    private String imagePath;
    private Seller seller;
    private String description;
    private boolean isPin=false;
    private Date releaseDate;
    private Category category;

    public Product(String name, int price, String imagePath, Seller seller, String description, Date releaseDate, Category category) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.seller = seller;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
    }
}
