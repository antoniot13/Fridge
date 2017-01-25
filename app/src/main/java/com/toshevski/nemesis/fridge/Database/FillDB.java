package com.toshevski.nemesis.fridge.Database;

import android.content.Context;
import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;

import java.util.ArrayList;

import static com.toshevski.nemesis.fridge.R.string.markets;

/**
 * Created by nemesis on 27-Nov-16.
 */

public class FillDB {
    private Context ctx;

    public FillDB(Context ctx) {
        this.ctx = ctx;
    }

    public void FillMarkets() {

        Data d = new Data(ctx);
/*
        Location targetLocation = new Location("");
        targetLocation.setLatitude(41.9713929);//your coords of course
        targetLocation.setLongitude(21.4183397);

        d.insertIntoMarket(new Market("СП", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(42.0026635);//your coords of course
        targetLocation.setLongitude(21.3740188);

        d.insertIntoMarket(new Market("Тинекс", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.993978);//your coords of course
        targetLocation.setLongitude(21.4325983);

        d.insertIntoMarket(new Market("Веро", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.9914047);//your coords of course
        targetLocation.setLongitude(21.425609);

        d.insertIntoMarket(new Market("Рамстор", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.993957);//your coords of course
        targetLocation.setLongitude(21.3996543);

        d.insertIntoMarket(new Market("КАМ", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(42.0068395);//your coords of course
        targetLocation.setLongitude(21.3979474);

        d.insertIntoMarket(new Market("Гранап", targetLocation));

        targetLocation = new Location("");
        targetLocation.setLatitude(41.9801143);//your coords of course
        targetLocation.setLongitude(21.473186);

        d.insertIntoMarket(new Market("Рептил", targetLocation));
*/
    }

    public void FillProducts() {
        Data d = new Data(ctx);
        d.insertIntoProducts(new Product("Кашкавал", 1.0, true));
        d.insertIntoProducts(new Product("Салама", 2.0, true));
        d.insertIntoProducts(new Product("Феферони", 0.0, false));
        d.insertIntoProducts(new Product("Павлака", 3.1, true));
        d.insertIntoProducts(new Product("Сирење", 1.0, true));
        d.insertIntoProducts(new Product("Кечап", 2.0, true));
        d.insertIntoProducts(new Product("Мајонез", 0.0, false));
        d.insertIntoProducts(new Product("Сланина", 3.1, true));
        d.insertIntoProducts(new Product("Свински врат", 1.0, true));
        d.insertIntoProducts(new Product("Печурки", 2.0, true));
        d.insertIntoProducts(new Product("Јогурт", 1.1, true));
        d.insertIntoProducts(new Product("Моцарела", 2.5, true));
        d.insertIntoProducts(new Product("Млеко", 1.0, true));
    }
}
