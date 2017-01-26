package com.toshevski.nemesis.fridge.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class DB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Fridge.db";

    public DB(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static class Markets implements BaseColumns {
        public static final String TABLE_NAME = "Markets";
        public static final String NAME = "name";
        public static final String LAT = "lat";
        public static final String LON = "lon";
    }

    public static class ReceiptsProducts implements BaseColumns {
        public static final String TABLE_NAME = "ReceiptsProducts";
        public static final String RID = "rid";
        public static final String PID = "pid";
    }

    public static class Recipes implements BaseColumns {
        public static final String TABLE_NAME = "Recipes";
        public static final String NAME = "name";
        public static final String DESC = "desc";
    }

    public static class Products implements BaseColumns {
        public static final String TABLE_NAME = "Products";
        public static final String NAME = "name";
        public static final String QTY = "qty";
        public static final String AVAIL = "avail";
    }

    public static class Fridge implements BaseColumns {
        static final String TABLE_NAME = "Fridges";
        static final String NAME = "name";
    }

    public static final String TEXT_TYPE = " TEXT";
    public static final String DECIMAL_TYPE = " DECIMAL";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_MARKETS =
            "CREATE TABLE " + Markets.TABLE_NAME + " (" +
                    Markets._ID + " INTEGER PRIMARY KEY," +
                    Markets.NAME + TEXT_TYPE + COMMA_SEP +
                    Markets.LAT + DECIMAL_TYPE + COMMA_SEP +
                    Markets.LON + DECIMAL_TYPE + " )";

    public static final String SQL_CREATE_RECIPES =
            "CREATE TABLE " + Recipes.TABLE_NAME + " (" +
                    Recipes._ID + " INTEGER PRIMARY KEY," +
                    Recipes.NAME + TEXT_TYPE + COMMA_SEP +
                    Recipes.DESC + TEXT_TYPE + " )";

    public static final String SQL_CREATE_PRODUCTS =
            "CREATE TABLE " + Products.TABLE_NAME + " (" +
                    Products._ID + " INTEGER PRIMARY KEY," +
                    Products.NAME + TEXT_TYPE + COMMA_SEP +
                    Products.AVAIL + " INTEGER" + COMMA_SEP +
                    Products.QTY + " DECIMAL" + " )";

    public static final String SQL_CREATE_FRIDGES =
            "CREATE TABLE " + Fridge.TABLE_NAME + " (" +
                    Fridge._ID + " INTEGER PRIMARY KEY," +
                    Fridge.NAME + TEXT_TYPE + ")";

    public static final String SQL_CREATE_RECEIPTSPRODUCTS =
            "CREATE TABLE " + ReceiptsProducts.TABLE_NAME + " (" +
                    ReceiptsProducts.RID + " INTEGER NOT NULL, " +
                    ReceiptsProducts.PID + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + ReceiptsProducts.RID + ", " + ReceiptsProducts.PID + "))";

    public static final String SQL_DELETE_MARKETS =
            "DROP TABLE IF EXISTS " + Markets.TABLE_NAME;

    public static final String SQL_DELETE_RECIPES =
            "DROP TABLE IF EXISTS " + Recipes.TABLE_NAME;

    public static final String SQL_DELETE_PRODUCTS =
            "DROP TABLE IF EXISTS " + Products.TABLE_NAME;

    public static final String SQL_DELETE_FRIDGES =
            "DROP TABLE IF EXISTS " + Fridge.TABLE_NAME;

    public static final String SQL_DELETE_RECEIPTSPRODUCTS=
            "DROP TABLE IF EXISTS " + ReceiptsProducts.TABLE_NAME;

    /**
     * Kreiranje na baza so izvrshuvanje na SQL izrazi za site tabeli
     * @param db baza
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MARKETS);
        db.execSQL(SQL_CREATE_RECIPES);
        db.execSQL(SQL_CREATE_PRODUCTS);
        db.execSQL(SQL_CREATE_FRIDGES);
        db.execSQL(SQL_CREATE_RECEIPTSPRODUCTS);
    }

    /**
     * Promenuvanje na bazata za da odgovara na novata verzija
     * @param db baza
     * @param oldVersion broj na starata verzija
     * @param newVersion broj na novata verzija
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_MARKETS);
        db.execSQL(SQL_DELETE_RECIPES);
        db.execSQL(SQL_DELETE_PRODUCTS);
        db.execSQL(SQL_DELETE_FRIDGES);
        db.execSQL(SQL_DELETE_RECEIPTSPRODUCTS);
        onCreate(db);
    }

    /**
     * Promenuvanje na bazata za da odgovara na poniska verzija
     * @param db baza
     * @param oldVersion stara verzija
     * @param newVersion nova verzija
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
