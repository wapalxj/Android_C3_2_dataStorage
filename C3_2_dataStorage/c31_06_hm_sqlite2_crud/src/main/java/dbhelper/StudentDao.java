package dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vero on 2016/5/20.
 */
public class StudentDao {
    StudentDbOpenHelper helper;
    public StudentDao(Context context){
        helper=new StudentDbOpenHelper(context);
    }

    public void add(Student st){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("insert into students values(?,?,?)",
                new Object[]{st.getId(),st.getName(),st.getSex()}
        );
    }
    public void delete(String id){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from students where _id=?",
                new Object[]{id}
        );
    }
    public void update(Student st){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("update students set name=?,sex=? where _id=?",
                new Object[]{st.getName(),st.getSex(),st.getId()}
        );
    }
    public Student query(String id){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from students where _id = ?",new String[]{id});
        boolean res=cursor.moveToNext();

        Student st=null;
        if (res){
            st=new Student();
            st.setId(cursor.getString(cursor.getColumnIndex("_id")));
            st.setName(cursor.getString(cursor.getColumnIndex("name")));
            st.setSex(cursor.getString(cursor.getColumnIndex("sex")));
        }
        cursor.close();
        return st;
    }
}
