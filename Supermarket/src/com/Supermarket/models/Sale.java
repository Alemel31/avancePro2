package com.Supermarket.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sale {
    String id;
    String date;
    String details;
    double total;

    public Sale(String id, String details, double total) {
        this.id = id;
        this.details = details;
        this.total = total;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = LocalDateTime.now().format(formatter);
    }

    public Sale(String id, String date, String details, double total) {
        this.id = id;
        this.date = date;
        this.details = details;
        this.total = total;
    }

    public String getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getDetails() {
        return details;
    }
    public double getTotal() {
        return total;
    }
    public String toTxtFormat() {
        return id + "," + date + "," + details.replace(",", "|") + "," + total;
    }


}
