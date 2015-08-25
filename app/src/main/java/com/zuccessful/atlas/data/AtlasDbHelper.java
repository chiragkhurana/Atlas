package com.zuccessful.atlas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zuccessful.atlas.data.AtlasContract.CountriesEntry;

public class AtlasDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "countries.db";
    public static final int DATABASE_VERSION = 1;

    public AtlasDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + CountriesEntry.TABLE_NAME + " (" +
                CountriesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CountriesEntry.COLUMN_COUNTRY_NAME + " TEXT NOT NULL," +
                CountriesEntry.COLUMN_CAPITAL + " TEXT NOT NULL," +
                CountriesEntry.COLUMN_CALLING_CODE + " INTEGER NOT NULL " +
                ");";
        db.execSQL(query);

        ContentValues values = new ContentValues();
        values.put(CountriesEntry.COLUMN_COUNTRY_NAME, "India");
        values.put(CountriesEntry.COLUMN_CAPITAL, "Delhi");
        values.put(CountriesEntry.COLUMN_CALLING_CODE, "91");
        db.insert(CountriesEntry.TABLE_NAME, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(CountriesEntry.COLUMN_COUNTRY_NAME, "Germany");
        values2.put(CountriesEntry.COLUMN_CAPITAL, "Belgium");
        values2.put(CountriesEntry.COLUMN_CALLING_CODE, "94");
        db.insert(CountriesEntry.TABLE_NAME, null, values2);

//        values[1].put(CountriesEntry.COLUMN_COUNTRY_NAME, "Australia");
//        values[1].put(CountriesEntry.COLUMN_CAPITAL, "Sydney");
//        values[1].put(CountriesEntry.COLUMN_CALLING_CODE, "44");
//
//        values[2].put(CountriesEntry.COLUMN_COUNTRY_NAME, "Germany");
//        values[2].put(CountriesEntry.COLUMN_CAPITAL, "Belgium");
//        values[2].put(CountriesEntry.COLUMN_CALLING_CODE, "94");
//
//        values[3].put(CountriesEntry.COLUMN_COUNTRY_NAME, "France");
//        values[3].put(CountriesEntry.COLUMN_CAPITAL, "Italy");
//        values[3].put(CountriesEntry.COLUMN_CALLING_CODE, "56");
//
//        values[4].put(CountriesEntry.COLUMN_COUNTRY_NAME, "Pakistan");
//        values[4].put(CountriesEntry.COLUMN_CAPITAL, "Karachi");
//        values[4].put(CountriesEntry.COLUMN_CALLING_CODE, "91");
//
//        for (int i = 0; i < 5; i++) {
//            db.insert(CountriesEntry.TABLE_NAME, null, values[i]);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CountriesEntry.TABLE_NAME + ";");
        onCreate(db);
    }

    public boolean checkWord(String name) {
        SQLiteDatabase db = getReadableDatabase();
        boolean ans = false;
        String query = "SELECT " + CountriesEntry.COLUMN_COUNTRY_NAME + " FROM " + CountriesEntry.TABLE_NAME + " WHERE 1;";
        Cursor c = db.rawQuery(query, null);

        String countryName;

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                if ((countryName = c.getString(c.getColumnIndex(CountriesEntry.COLUMN_COUNTRY_NAME))) != null) {
                    countryName = countryName.trim().toLowerCase();
                    if (name.equals(countryName)) {
                        ans = true;
                        break;
                    }
                }
            }
        } else {
            ans = false;
        }

        c.close();
        db.close();

        return ans;
    }
}
