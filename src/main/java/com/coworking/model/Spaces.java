package com.coworking.model;

import jakarta.persistence.*;

@Entity
public class Spaces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private double price;
    private boolean available = true;

    public Spaces() {}

    public Spaces(String type, double price) {
        this.type = type;
        this.price = price;
        this.available = true;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
