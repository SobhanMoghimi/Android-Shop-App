package com.example.myshop.model;

public class Categories
{
    private static boolean isAscending,isDescending;
    private static String category;

    public static boolean isIsAscending() {
        return isAscending;
    }

    public static void setIsAscending(boolean isAscending) {
        Categories.isAscending = isAscending;
    }

    public static boolean isIsDescending() {
        return isDescending;
    }

    public static void setIsDescending(boolean isDescending) {
        Categories.isDescending = isDescending;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Categories.category = category;
    }
}
