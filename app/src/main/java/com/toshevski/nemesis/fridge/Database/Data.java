package com.toshevski.nemesis.fridge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

import com.toshevski.nemesis.fridge.Model.Fridge;
import com.toshevski.nemesis.fridge.Model.Market;
import com.toshevski.nemesis.fridge.Model.Product;
import com.toshevski.nemesis.fridge.Model.Recipe;

import java.util.ArrayList;

public class Data {

    private static DB dbc;
    private Context ctx;

    public Data(Context ctx) {
        this.ctx = ctx;
        dbc = new DB(ctx);
    }

    public void saveCredentials(String username, String password, boolean saveMe) {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("saveme", saveMe);
        editor.apply();
    }

    public void setSaveMe(boolean saveme) {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("saveme", saveme);
        editor.apply();
    }

    public boolean getSaveMe() {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        return settings.getBoolean("saveme", false);
    }

    public void insertIntoMarket(Market m) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Markets.NAME, m.Name);
        cv.put(DB.Markets.LAT, m.Location.getLatitude());
        cv.put(DB.Markets.LON, m.Location.getLongitude());

        dbc.getWritableDatabase().insert(DB.Markets.TABLE_NAME, null, cv);
    }

    public void setBudget(int budget) {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Budget", budget);
        editor.apply();
    }

    public void setLimit(int budget) {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Limit", budget);
        editor.apply();
    }

    public int reduceBudget(int howMuchToReduce) {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        int budget = settings.getInt("Budget", 0);
        budget -= howMuchToReduce;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Budget", budget);
        editor.apply();
        return budget;
    }

    public int increaseBudget(int howMuchToReduce) {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        int budget = settings.getInt("Budget", 0);
        budget += howMuchToReduce;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Budget", budget);
        editor.apply();
        return budget;
    }

    public int getBudget() {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        return settings.getInt("Budget", 0);
    }

    public int getLimit() {
        SharedPreferences settings = ctx.getSharedPreferences("Pref", 0);
        return settings.getInt("Limit", 0);
    }

    public void cleanTable(String TABLE_NAME) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE * FROM " + TABLE_NAME;
        db.rawQuery(query, null);
    }

    public void deleteFromMarkets(Market m) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE FROM " + DB.Markets.TABLE_NAME + " WHERE name = " + m.Name;

        db.rawQuery(query, null);
    }

    public void deleteMarkets() {

        String TEXT_TYPE = " TEXT";
        String DECIMAL_TYPE = " DECIMAL";
        String COMMA_SEP = ",";
        SQLiteDatabase db = dbc.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DB.Markets.TABLE_NAME);
        db.execSQL(
                "CREATE TABLE " + DB.Markets.TABLE_NAME + " (" +
                        DB.Markets._ID + " INTEGER PRIMARY KEY," +
                        DB.Markets.NAME + TEXT_TYPE + COMMA_SEP +
                        DB.Markets.LAT + DECIMAL_TYPE + COMMA_SEP +
                        DB.Markets.LON + DECIMAL_TYPE + " )");
    }

    public void insertIntoReceipts(Recipe r) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Recipes.NAME, r.Name);
        cv.put(DB.Recipes.DESC, r.Description);

        long RID = dbc.getWritableDatabase().insert(DB.Recipes.TABLE_NAME, null, cv);
        for (Product p : r.Products) {
            int PID = getID(p.Name);
            if (PID == -1) {
                p.Quantity = -66;
                PID = (int) insertIntoProducts(p);
            }
            insertIntoReceiptsProducts((int) RID, PID);
        }
    }

    public void insertIntoReceiptsProducts(int RID, int PID) {
        ContentValues cv = new ContentValues();
        cv.put(DB.ReceiptsProducts.PID, PID);
        cv.put(DB.ReceiptsProducts.RID, RID);

        dbc.getWritableDatabase().insert(DB.ReceiptsProducts.TABLE_NAME, null, cv);
    }

    public int getID(String NAME) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        Cursor mCursor = db.rawQuery(
                "SELECT * FROM " + DB.Products.TABLE_NAME + " WHERE name = '" + NAME + "'", null);
        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                int id = mCursor.getInt(0);
                mCursor.close();
                return id;
            }
        }
        return -1;
    }


    public long insertIntoProducts(Product m) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Products.NAME, m.Name);
        cv.put(DB.Products.AVAIL, m.IsAvailable ? 1 : 0);
        cv.put(DB.Products.QTY, m.Quantity);

        return dbc.getWritableDatabase().insert(DB.Products.TABLE_NAME, null, cv);
    }

    public Product getProduct(int id) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "SELECT  * FROM " + DB.Products.TABLE_NAME + " WHERE _id = " + id;

        Cursor c = db.rawQuery(query, null);

        Product mark = null;
        if (c.moveToFirst()) {
            String name = c.getString(1);
            boolean avail = Integer.parseInt(c.getString(2)) != 0;
            double qty = Double.parseDouble(c.getString(3));
            mark = new Product(name, qty, avail);
            c.close();
            return mark;
        }
        c.close();
        return null;
    }

    public void deleteFromProducts(Product m) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE FROM " + DB.Products.TABLE_NAME + " WHERE name = " + m.Name;

        db.rawQuery(query, null);
    }

    public void insertIntoFridges(Fridge f) {
        ContentValues cv = new ContentValues();
        cv.put(DB.Products.NAME, f.Name);
        dbc.getWritableDatabase().insert(DB.Fridge.TABLE_NAME, null, cv);
    }

    public void deleteFromFridges(Fridge m) {
        SQLiteDatabase db = dbc.getReadableDatabase();
        String query = "DELETE FROM " + DB.Fridge.TABLE_NAME + " WHERE name = " + m.Name;

        db.rawQuery(query, null);
    }

    public ArrayList<Recipe> getAllReceiptsWithProducts() {
        ArrayList<Recipe> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Recipes.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);
        Cursor c2;

        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String desc = c.getString(2);
                Recipe r = new Recipe(name, new ArrayList<Product>(), desc);

                String insideQuery = "SELECT  * FROM " + DB.ReceiptsProducts.TABLE_NAME +
                        " WHERE RID = " + id;
                c2 = db.rawQuery(insideQuery, null);
                if (c2.moveToFirst()) {
                    do {
                        int pid = c2.getInt(1);
                        if (pid == -1)
                            continue;
                        Product p = getProduct(pid);
                        r.Products.add(p);
                    } while (c2.moveToNext());
                }
                c2.close();
                m.add(r);
            } while (c.moveToNext());
        }

        c.close();
        return m;
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

        String query = "SELECT  * FROM " + DB.Recipes.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Recipe mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                Log.d("INFOOOR:", name);
                boolean avail = Integer.parseInt(c.getString(2)) != 0;
                double qty = Double.parseDouble(c.getString(3));

                //mark = new Recipe();

                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }

    public ArrayList<Fridge> getAllFridges() {
        ArrayList<Fridge> m = new ArrayList<>();

        SQLiteDatabase db = dbc.getReadableDatabase();

        String query = "SELECT  * FROM " + DB.Fridge.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);

        Fridge mark = null;
        if (c.moveToFirst()) {
            do {
                String name = c.getString(1);
                mark = new Fridge();
                mark.Name = name;


                m.add(mark);
            } while (c.moveToNext());
        }

        c.close();
        return m;
    }
}
