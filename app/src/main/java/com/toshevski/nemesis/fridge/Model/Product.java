package com.toshevski.nemesis.fridge.Model;

public class Product {
    public String Name;
    public double Quantity;
    public boolean IsAvailable;

    public Product(String name, double quantity, boolean isAvailable) {
        Name = name;
        Quantity = quantity;
        IsAvailable = isAvailable;
    }
}
