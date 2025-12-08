package com.servlet.secondhand.entity;

public class Item {
    private int id;
    private String title;
    private String description;
    private double price;
    private int userId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}