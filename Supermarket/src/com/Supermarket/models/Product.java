package com.Supermarket.models;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String imagePath;

    public Product(String id, String name, double price, int stock, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public String toTxtFormat() {
        return id + "," + name + "," + price + "," + stock+ "," + imagePath;
    }

    @Override
    public String toString() {
        return getName();
    }
}
