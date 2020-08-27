package com.example.smartdictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.smartdictionary.database.WordDBSchema.*;

public class WordDBBaseHelper extends SQLiteOpenHelper{


    public WordDBBaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+ WordTable.NAME+"("+
                WordTable.COLS.ID + " integer primary key autoincrement,"+
                WordTable.COLS.UUID + " text," +
                WordTable.COLS.FRENCH + " text," +
                WordTable.COLS.ARABIC + " text," +
                WordTable.COLS.ENGLISH + " text," +
                WordTable.COLS.PERSIAN + " text"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
