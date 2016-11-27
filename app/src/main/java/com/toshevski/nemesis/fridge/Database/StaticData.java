package com.toshevski.nemesis.fridge.Database;

import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;

import java.util.ArrayList;


public class StaticData {

    public static ArrayList<Market> getMarkets() {
        ArrayList<Market> markets = new ArrayList<Market>();
        // markets.add(new Market("SP",);

        return markets;
    }

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Кашкавал", 1.0, true));
        products.add(new Product("Салама", 2.0, true));
        products.add(new Product("Феферони", 1.0, false));
        products.add(new Product("Павлака", 3.1, true));
        products.add(new Product("Сирење", 1.0, true));
        products.add(new Product("Кечап", 2.0, true));
        products.add(new Product("Мајонез", 1.0, false));
        products.add(new Product("Сланина", 3.1, true));
        products.add(new Product("Свински врат", 1.0, true));
        products.add(new Product("Печурки", 2.0, true));

        return products;
    }
}
