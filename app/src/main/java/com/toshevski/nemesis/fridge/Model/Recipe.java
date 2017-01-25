package com.toshevski.nemesis.fridge.Model;

import java.util.ArrayList;

public class Recipe {
    public String Name;
    public ArrayList<Product> Products;
    public String Description;

    public Recipe(String name,ArrayList<Product> products,String description)
    {
        Name = name;
        Products = new ArrayList<Product>();
        Products = products;
        Description = description;

    }

}
