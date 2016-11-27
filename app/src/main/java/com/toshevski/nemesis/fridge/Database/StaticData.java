package com.toshevski.nemesis.fridge.Database;

import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;

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

    public static ArrayList<Recipe> getRecipes() {
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        products.add(new Product("Јајца",2.0,false));
        products.add(new Product("Млеко",0.5,false));
        products.add(new Product("Шеќер",1.0,false));
        products.add(new Product("Брашно",4.0,false));
        products.add(new Product("Кисела вода",100.0,false));
        String opis="Изматете ги јајцата, додајте им сол, шеќер и млеко. Додајте брашно и матете додека не ги стопите грутките кои се прават во тестото.\n" +
                "\n" +
                "Ова главно зависи од брашното, но треба да добиете многу густа смеса за палачинки.\n" +
                "\n" +
                "Додајте ја киселата вода и промешајте.\n" +
                "\n" +
                "Оставете ја смесата да отстои 20-ина минути, а потоа печете ги палачинките на силен оган";

        recipes.add(new Recipe("Палачинки",products,opis));
        products = new ArrayList<Product>();
        products.add(new Product("Макарони",500.0,false));
        products.add(new Product("Јајца",4.0,false));
        products.add(new Product("Сирење бело",200.0,false));
        products.add(new Product("Кисело млеко",800.0,false));
        products.add(new Product("Млеко",100.0,false));
        products.add(new Product("Сланина",150.0,false));
        opis="1. Макароните со форма по избор (овде се спирални), се варат по упатството, се цедат и се ставаат во тава намастена со малку масло.\n" +
                "2. Јајцата се матат, во нив се става дробено или рендано сирење, се меша и оваа смеса само се распоредува врз варените макарони, но не се измешува. Се става во загреана рерна на 200 степени околу 15 минути.\n" +
                "3. Тавата се вади од рерната, сланината се сечка на коцкички и се распоредува одозгора врз макароните.\n" +
                "4. Во киселото млеко се става прашокот за крем супа од печурки и млекото, се меша убаво и се истура рамномерно врз макароните и сланината. Се враќа во рерната и се допекува уште 15-20 минути.\n" ;

        recipes.add(new Recipe("Макарони со сланина",products,opis));

        return recipes;


    }
}
