package com.example.myshop.model;

import java.util.Date;

public class Product {

    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPin() {
        return isPin;
    }

    public void setPin(boolean pin) {
        isPin = pin;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
