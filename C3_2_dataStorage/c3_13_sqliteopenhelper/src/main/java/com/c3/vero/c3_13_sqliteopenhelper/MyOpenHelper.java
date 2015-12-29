package com.c3.vero.c3_13_sqliteopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vero on 2015/11/25.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE vero(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,userName VARCHAR(10) NOT NULL,userAge INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
