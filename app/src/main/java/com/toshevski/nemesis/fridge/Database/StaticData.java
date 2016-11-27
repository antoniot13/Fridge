package com.toshevski.nemesis.fridge.Database;

import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;

import java.util.ArrayList;


public class StaticData {

    public static ArrayList<Market> getMarkets() {



        ArrayList<Market> markets = new ArrayList<Market>();

        Location targetLocation = new Location("");
        targetLocation.setLatitude(41.9713929);//your coords of course
        targetLocation.setLongitude(21.4183397);

        markets.add(new Market("СП", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(42.0026635);//your coords of course
        targetLocation.setLongitude(21.3740188);

        markets.add(new Market("Тинекс", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.993978);//your coords of course
        targetLocation.setLongitude(21.4325983);

        markets.add(new Market("Веро", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.9914047);//your coords of course
        targetLocation.setLongitude(21.425609);

        markets.add(new Market("Рамстор", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.993957);//your coords of course
        targetLocation.setLongitude(21.3996543);

        markets.add(new Market("КАМ", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(42.0068395);//your coords of course
        targetLocation.setLongitude(21.3979474);

        markets.add(new Market("Гранап", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.9801143);//your coords of course
        targetLocation.setLongitude(21.473186);

        markets.add(new Market("Рептил", targetLocation));

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
