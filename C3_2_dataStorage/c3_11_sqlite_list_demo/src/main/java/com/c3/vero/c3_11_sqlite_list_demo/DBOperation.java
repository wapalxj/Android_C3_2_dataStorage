package com.c3.vero.c3_11_sqlite_list_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBOperation {
    private Context mcontext;
    private SQLiteDatabase msqLiteDatabase;
    private Cursor cursor;
    public DBOperation(Context context) {
        mcontext=context;
    }

    public boolean openOrCreateDatabae(){
        //建立数据库并且连接
        msqLiteDatabase=mcontext.openOrCreateDatabase("vero_db",Context.MODE_PRIVATE,null);
        //建表
//            msqLiteDatabase.execSQL("CREATE TABLE vero2 (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,userName VARCHAR(10) NOT NULL,userAge INTEGER )");
        return true;
    }

    //查询
    public Cursor selectALL(){
         cursor=msqLiteDatabase.query("vero2", new String[]{"_id", "userName", "userAge"}, null, null, null, null, null);
        return cursor;
    }
    private Cursor select(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy){
         cursor=msqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);

        return cursor;
    }
    public boolean insert(String name,int age){
        //插入
        ContentValues contentValues =new ContentValues();//数据集
        contentValues.put("userName", name);
        contentValues.put("userAge", age);
        msqLiteDatabase.insert("vero2","null",contentValues);
        return true;
    }
    //删除
    public boolean delete(){
        if(cursor.getCount()-1>0){
            msqLiteDatabase.delete("vero2","_id=="+(cursor.getCount()-1),null);
            return true;
        }
        return false;
    }
    public boolean close(){
        //关闭数据库连接
        msqLiteDatabase.close();
        msqLiteDatabase=null;
        return  true;
    }
}
