package com.c3.vero.c3_13_sqliteopenhelper;

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
        MyOpenHelper helper=new MyOpenHelper(mcontext,"vero_db",null,1);
        msqLiteDatabase=helper.getWritableDatabase();
        return true;
    }

    //查询
    public Cursor selectALL(){
         cursor=msqLiteDatabase.query("vero", new String[]{"_id", "userName", "userAge"}, null, null, null, null, null);
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
        msqLiteDatabase.insert("vero","null",contentValues);
        return true;
    }
    //删除
    public boolean delete(){
        if(cursor.getCount()-1>0){
            msqLiteDatabase.delete("vero","_id=="+(cursor.getCount()-1),null);
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
