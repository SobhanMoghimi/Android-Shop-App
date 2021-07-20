package com.example.myshop.model;

import android.graphics.Bitmap;

import java.util.Date;

public class Product
{


    private String name;
    private int id;
    private int price;
    private Bitmap image;
    private Seller seller;
    private String description;
    private boolean isPin=false;
    private Date releaseDate;
    private Category category;
    private String sellerPhoneNumber;
    public static Product workingProduct;
    public Product(String name, int price, Bitmap image, Seller seller, String description, Date releaseDate, String category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.seller = seller;
        this.description = description;
        this.releaseDate = releaseDate;
        setCategory(category);
    }

    public Product(String name, int price, Bitmap image, String description, boolean isPin, String sellerPhoneNumber, String category)
    {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.isPin = isPin;
        this.sellerPhoneNumber = sellerPhoneNumber;
        setCategory(category);
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", seller=" + seller +
                ", description='" + description + '\'' +
                ", isPin=" + isPin +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", sellerPhoneNumber='" + sellerPhoneNumber + '\'' +
                '}';
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

    public String isPin() {
        if (isPin) {
            return "true";
        }
        return "false";
    }

    public void setPin(boolean pin) {
        isPin = pin;
    }

    public Category getCategory() {
        return category;
    }


    public void setCategory(String category) {
        if (category.equals("فشن"))
            this.category = Category.FASHION;
        else if (category.equals("لوازم بهداشتی"))
            this.category = Category.BEAUTY;
        else if (category.equals("کتاب"))
            this.category = Category.BOOK;
        else if (category.equals("ماشین"))
            this.category = Category.CAR;
        else if (category.equals("لوازم الکترونیکی"))
            this.category = Category.ELECTRONICS;
        else if (category.equals("لوازم خانه"))
            this.category = Category.HOME;
    }
    public String getCategoryString()
    {
        if(category==Category.BEAUTY)
            return "لوازم بهداشتی";
        if(category==Category.BOOK)
            return "کتاب";
        if(category==Category.FASHION)
            return "فشن";
        if(category==Category.ELECTRONICS)
            return "لوازم الکترونیکی";
        if(category==Category.HOME)
            return "لوازم خانه";
        if(category==Category.CAR)
            return "خودرو";
        return "بدون دسته بندی";
    }


    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSellerPhoneNumber() {
        return sellerPhoneNumber;
    }

    public void setSellerPhoneNumber(String sellerPhoneNumber) {
        this.sellerPhoneNumber = sellerPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
