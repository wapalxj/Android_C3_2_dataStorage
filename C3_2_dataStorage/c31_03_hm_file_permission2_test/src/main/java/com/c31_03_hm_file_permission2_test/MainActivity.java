package com.c31_03_hm_file_permission2_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        read();
    }

    /**
     * 测试读取上一个应用保存的文件
     */
    public void read(){
//        File file=new File("/data/data/com.c31_03_hm_file_permission/files/private.txt");
//        File file=new File("/data/data/com.c31_03_hm_file_permission/files/public.txt");
//        File file=new File("/data/data/com.c31_03_hm_file_permission/files/writeonly.txt");
        File file=new File("/data/data/com.c31_03_hm_file_permission/files/readonly.txt");
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line=br.readLine();
            Toast.makeText(getApplicationContext(),line,Toast.LENGTH_SHORT).show();
        }
        catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"访问失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(),"访问失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}
