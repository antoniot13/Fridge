package com.toshevski.nemesis.fridge.Database;

import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;

import java.util.ArrayList;


public class StaticData {

    public static ArrayList<Market> getMarkets() {

        Location targetLocation = new Location("");//provider name is unecessary
        targetLocation.setLatitude(0.0d);//your coords of course
        targetLocation.setLongitude(0.0d);

        ArrayList<Market> markets = new ArrayList<Market>();
        markets.add(new Market("СП", targetLocation));
        markets.add(new Market("Тинекс", targetLocation));
        markets.add(new Market("Веро", targetLocation));
        markets.add(new Market("Рамстор", targetLocation));
        markets.add(new Market("КАМ", targetLocation));
        markets.add(new Market("Гранап", targetLocation));
        markets.add(new Market("Рептил", targetLocation));
        markets.add(new Market("Макс Лукс", targetLocation));
        markets.add(new Market("Пожаренка", targetLocation));

        return markets;
    }

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Кашкавал", 1.0, true));
        products.add(new Product("Салама", 2.0, true));
        products.add(new Product("Феферони", 0.0, false));
        products.add(new Product("Павлака", 3.1, true));
        products.add(new Product("Сирење", 1.0, true));
        products.add(new Product("Кечап", 2.0, true));
        products.add(new Product("Мајонез", 0.0, false));
        products.add(new Product("Сланина", 3.1, true));
        products.add(new Product("Свински врат", 1.0, true));
        products.add(new Product("Печурки", 2.0, true));
        products.add(new Product("Јогурт", 1.1, true));
        products.add(new Product("Моцарела", 2.5, true));
        products.add(new Product("Млеко", 1.0, true));

        return products;
    }
}
