package com.example.myshop.model;

import java.util.ArrayList;

public class Admin {

    public static Admin activeAdmin;
    public boolean isAdmin;
    private ArrayList<Product> promotedProducts = new ArrayList<>();

    public static Admin getActiveAdmin() {
        return activeAdmin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public static void setActiveAdmin(Admin activeAdmin) {
        Admin.activeAdmin = activeAdmin;
    }

    public ArrayList<Product> getPromotedProducts() {
        return promotedProducts;
    }

    public void setPromotedProducts(ArrayList<Product> promotedProducts) {
        this.promotedProducts = promotedProducts;
    }

    public void addToPromotedProducts(Product product){
        promotedProducts.add(product);
    }

    public void removeFromPromotedProducts(Product product){
        promotedProducts.remove(product);
    }
}
