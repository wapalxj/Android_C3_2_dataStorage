package com.c31_03_hm_file_permission;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 创建一个私有文件
     * 使用data/data/packageName
     * 只有当前的应用做自己可用去访问，其他应用不能访问(读、写)
     * @param v
     */
    public void getPrivateFile(View v){
        File file=new File(getFilesDir(),"private.txt");
        OutputStream os= null;
        try {
            os = new FileOutputStream(file);
            os.write("private".getBytes());
            Toast.makeText(getApplicationContext(),"写入私有文件成功",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"文件目录错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"写入私有文件失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个只读的文件，其他的应用可读不可写
     * @param v
     */
    public void getReadOnlyFile(View v){
        FileOutputStream fos=null;
        try {
            fos=openFileOutput("readonly.txt", Context.MODE_WORLD_READABLE);
            fos.write("readonly".getBytes());
            Toast.makeText(getApplicationContext(),"写入私有文件成功",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"文件目录错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"写入私有文件失败",Toast.LENGTH_SHORT).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *只可写文件
     * @param v
     */
    public void getWriteOnlyFile(View v){
        FileOutputStream fos=null;
        try {
            fos=openFileOutput("writeonly.txt", Context.MODE_WORLD_WRITEABLE);
            fos.write("writeonly".getBytes());
            Toast.makeText(getApplicationContext(),"写入 只可写文件成功",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"文件目录错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"写入 只可写文件失败",Toast.LENGTH_SHORT).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *public
     * @param v
     */
    public void getPublicFile(View v){
        FileOutputStream fos=null;
        try {
            fos=openFileOutput("public.txt", Context.MODE_WORLD_WRITEABLE+Context.MODE_WORLD_READABLE);
            fos.write("public".getBytes());
            Toast.makeText(getApplicationContext(),"写入public文件成功",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"文件目录错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"写入public文件失败",Toast.LENGTH_SHORT).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
