package dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vero on 2016/5/19.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper{
    public MySqliteOpenHelper(Context context) {
        //context：应用上下文
        //name:数据库名称
        //factory:创建游标工厂:操作游标
        //version:数据库版本
        super(context,"mydb1", null, 2);
    }



    /**
    mysql:
        create table students (
            _id integer primary key auto_increment,
            name varchar(30)
         );
    android:
        create table students (
            _id integer primary key autoincrement,
            name varchar(30)
         );
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //数据库首次创建时调用
        db.execSQL(
                "create table students (_id integer primary key autoincrement," +
                "name varchar(30), " +
                "sex  varchar(10))"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库版本升级时调用
        Log.i("onUpgrade","onUpgrade");
        db.execSQL(
                "alter table students add number varchar(10)"
        );
    }
}
