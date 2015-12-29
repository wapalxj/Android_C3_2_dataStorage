package com.c3.vero.c3_09_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DBOperation {
    private Context mcontext;
    private SQLiteDatabase msqLiteDatabase;
    public DBOperation(Context context) {
        mcontext=context;
    }

    public boolean openOrCreateDatabae(){
        //建立数据库并且连接
        msqLiteDatabase=mcontext.openOrCreateDatabase("vero_db",Context.MODE_PRIVATE,null);
        //建表
        msqLiteDatabase.execSQL("CREATE TABLE vero2 (userId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,userName VARCHAR(10) NOT NULL,uerAge INTEGER )");
        //插入
        ContentValues contentValues =new ContentValues();//数据集
        contentValues.put("userName","vero1");
        contentValues.put("userAge", 20);
        msqLiteDatabase.insert("vero2","null",contentValues);
        //关闭数据库连接
        msqLiteDatabase.close();
        msqLiteDatabase=null;
        return  true;
    }
}
