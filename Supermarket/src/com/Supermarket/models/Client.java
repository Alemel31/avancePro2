package com.Supermarket.models;

public class Client {
    private String id;
    private String name;
    private String phone;

    public Client(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String toTxtFormat() {
        return id + " | "+ name +" | "+ phone ;
    }
}
