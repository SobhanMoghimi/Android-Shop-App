package com.example.myshop.model;

import android.graphics.Bitmap;

public class Product
{


    private String name;
    private int id;
    private int price;
    private Bitmap image;
    private Seller seller;
    private String description;
    private boolean isPin=false;
    private Category category;
    private static Product workingProduct;

    public Product(String name, int price, Bitmap image, Seller seller, String description, String category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.seller = seller;
        this.description = description;
        setCategory(category);
    }

    public Product(String name, int id, int price, Bitmap image, Seller seller, String description, boolean isPin, String category) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.image = image;
        this.seller = seller;
        this.description = description;
        this.isPin = isPin;
        setCategory(category);
    }

    public static Product getWorkingProduct() {
        return workingProduct;
    }

    public static void setWorkingProduct(Product workingProduct) {
        Product.workingProduct = workingProduct;
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
                ", category=" + category +
                ", sellerPhoneNumber='" + seller.getPhoneNumber() + '\'' +
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
        if (category.equals("??????"))
            this.category = Category.FASHION;
        else if (category.equals("?????????? ??????????????"))
            this.category = Category.BEAUTY;
        else if (category.equals("????????"))
            this.category = Category.BOOK;
        else if (category.equals("??????????"))
            this.category = Category.CAR;
        else if (category.equals("?????????? ????????????????????"))
            this.category = Category.ELECTRONICS;
        else if (category.equals("?????????? ????????"))
            this.category = Category.HOME;
    }

    public static Category getCategoryType(String category) {
        if (category.equals("??????"))
            return Category.FASHION;
        else if (category.equals("?????????? ??????????????"))
            return Category.BEAUTY;
        else if (category.equals("????????"))
            return Category.BOOK;
        else if (category.equals("??????????"))
            return Category.CAR;
        else if (category.equals("?????????? ????????????????????"))
            return Category.ELECTRONICS;
        else if (category.equals("?????????? ????????"))
            return  Category.HOME;
        else return null;
    }

    public String getCategoryString()
    {
        if(category==Category.BEAUTY)
            return "?????????? ??????????????";
        if(category==Category.BOOK)
            return "????????";
        if(category==Category.FASHION)
            return "??????";
        if(category==Category.ELECTRONICS)
            return "?????????? ????????????????????";
        if(category==Category.HOME)
            return "?????????? ????????";
        if(category==Category.CAR)
            return "??????????";
        return "???????? ???????? ????????";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
