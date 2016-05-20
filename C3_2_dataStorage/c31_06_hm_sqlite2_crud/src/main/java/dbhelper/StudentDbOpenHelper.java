package dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vero on 2016/5/20.
 */
public class StudentDbOpenHelper extends SQLiteOpenHelper{
    public StudentDbOpenHelper(Context context) {
        super(context, "student_db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table students (_id integer primary key autoincrement," +
                        "name varchar(30), " +
                        "sex  varchar(10))"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
