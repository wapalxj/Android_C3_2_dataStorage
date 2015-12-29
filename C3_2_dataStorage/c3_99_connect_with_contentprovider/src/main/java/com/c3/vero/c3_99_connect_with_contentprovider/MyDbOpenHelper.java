package com.c3.vero.c3_99_connect_with_contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vero on 2015/11/26.
 */
public class MyDbOpenHelper extends SQLiteOpenHelper {
    public MyDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE vero1(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name VARCHAR(20) NOT NULL,age INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
