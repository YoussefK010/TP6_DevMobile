package com.example.tp6_devmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    private static final String DB_NAME = "productManager";
    private static final int DB_VERSION = 1;

    public static final String TABLE = "produit";
    public static final String COL_ID = "_id";
    public static final String COL_NOM = "nom";
    public static final String COL_PRIX = "prix";

    public Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOM + " TEXT, "
                + COL_PRIX + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertProduct(Produit p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOM, p.getNom());
        values.put(COL_PRIX, p.getPrix());
        db.insert(TABLE, null, values);
        db.close();
    }

    public void updateProduct(Produit p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOM, p.getNom());
        values.put(COL_PRIX, p.getPrix());
        db.update(TABLE, values, COL_ID + " = ?",
                new String[]{String.valueOf(p.getId())});
        db.close();
    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, COL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    public Cursor getAllProducts() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE, null);
    }

    public Produit getOneProduct(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE,
                new String[]{COL_ID, COL_NOM, COL_PRIX},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        Produit p = null;
        if (c.moveToFirst()) {
            p = new Produit(c.getInt(0), c.getString(1), c.getDouble(2));
        }
        c.close();
        db.close();
        return p;
    }
}
