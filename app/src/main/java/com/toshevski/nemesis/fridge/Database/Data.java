package com.toshevski.nemesis.fridge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;

import java.util.ArrayList;

public class Data {

    private static DB dbc;

    public Data(Context ctx) {
         dbc = new DB(ctx);
    }

    public void insertIntoMarket(Market m) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Markets.NAME, m.Name);
        cv.put(DB.Markets.LAT, m.Location.getLatitude());
        cv.put(DB.Markets.LON, m.Location.getLongitude());

        dbc.getWritableDatabase().insert(DB.Markets.TABLE_NAME, null, cv);
    }


    public void insertIntoProducts(Product m) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Products.NAME, m.Name);
        cv.put(DB.Products.AVAIL, m.IsAvailable ? 1 : 0);
        cv.put(DB.Products.QTY, m.Quantity);

        dbc.getWritableDatabase().insert(DB.Products.TABLE_NAME, null, cv);
    }

    public ArrayList<Market> getAllMarkets() {
        ArrayList<Market> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Markets.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Market mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                double LAT = Double.parseDouble(c.getString(2));
                double LON = Double.parseDouble(c.getString(3));

                Location l = new Location("");
                l.setLatitude(LAT);
                l.setLongitude(LON);

                mark = new Market(name, l);

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Products.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Product mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                boolean avail = Integer.parseInt(c.getString(2)) != 0;
                double qty = Double.parseDouble(c.getString(3));

                mark = new Product(name, qty, avail);

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }

    public ArrayList<Recipe> getAllReceipts() {
        ArrayList<Recipe> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Products.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Recipe mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                boolean avail = Integer.parseInt(c.getString(2)) != 0;
                double qty = Double.parseDouble(c.getString(3));

                //mark = new Recipe();

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }
}
