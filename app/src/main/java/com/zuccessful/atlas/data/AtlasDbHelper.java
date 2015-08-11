package com.zuccessful.atlas.data;

import android.content.Context;
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CountriesEntry.TABLE_NAME + ";");
        onCreate(db);
    }
}
