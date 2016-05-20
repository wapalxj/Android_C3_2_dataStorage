package com.c3_31_06_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dbhelper.MySqliteOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        MySqliteOpenHelper helper=new MySqliteOpenHelper(MainActivity.this);
        helper.getWritableDatabase();
    }
}
