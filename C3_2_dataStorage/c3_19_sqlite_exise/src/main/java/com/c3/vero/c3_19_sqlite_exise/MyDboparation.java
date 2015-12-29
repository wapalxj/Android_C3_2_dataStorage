package com.c3.vero.c3_19_sqlite_exise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by vero on 2015/11/26.
 */
public class MyDboparation {
    private SQLiteDatabase mSqliteDatabase=null;
    private Context mContext;
    private MyDbOpenHelper myDbOpenHelper=null;
    private Cursor mcursor=null;
    public MyDboparation(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDatabase openOrCreateDb(){
        myDbOpenHelper=new MyDbOpenHelper(mContext,"vero_db",null,1);
        mSqliteDatabase=myDbOpenHelper.getWritableDatabase();
        return  mSqliteDatabase;
    }

    public Cursor selectAll(){
        mcursor=mSqliteDatabase.query("vero1", new String[]{"_id", "name", "age"}, null, null, null, null, null);
        return mcursor;
    }
    public Cursor select(String table, String[] columns, String selection,
                         String[] selectionArgs, String groupBy, String having,
                         String orderBy){
        Cursor temp= mSqliteDatabase.query("vero1",columns,selection,selectionArgs,groupBy,having,orderBy);
        return temp;
    }

    public long insert(String name,Integer age){
        ContentValues values =new ContentValues();
//        values.put("_id",id);
        values.put("name", name);
        values.put("age", age);
        long i=mSqliteDatabase.insert("vero1", "null", values);

        return i;
    }

    public int delete(String whereClause,String[] whereArgs){
        int d=mSqliteDatabase.delete("vero1", whereClause, whereArgs);
        return d;
    }
    public int update(String name,Integer age,String whereClause, String[] whereArgs){
        ContentValues values =new ContentValues();
//        values.put("_id",id);
        values.put("name", name);
        values.put("age", age);
        int u=mSqliteDatabase.update("vero1", values, whereClause, whereArgs);
        return u;
    }
    public void close(){
        mSqliteDatabase.close();
        mSqliteDatabase=null;
    }
}
