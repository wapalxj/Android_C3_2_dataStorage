package com.c31_2_hm_sdcard;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CheckBox cSave;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        remember();//账号密码的记录
    }

    public void init(){
        username= (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        cSave= (CheckBox)findViewById(R.id.checkbox);
        save= (Button)findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cSave.isChecked()) {
                    if (!checkSdCardStatus()){
                        return;
                    }
//                    File file = new File("/mnt/sdcard/info.txt");
                    File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
                    try {
                        String value = username.getText() + "------>" + password.getText();
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(value.getBytes());
                        fos.close();
                        Toast.makeText(getApplicationContext(), "勾选了，保存成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "勾选了，没有保存成功", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "没有勾选，没有保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void remember(){
        File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
        if(file.exists()&&file.length()>0){
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                String line=br.readLine();
                String username=line.split("------>")[0];
                String password=line.split("------>")[1];

                this.username.setText(username);
                this.password.setText(password);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //检测SD卡状态
    public boolean checkSdCardStatus(){
        String status=Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(status)){
            Toast.makeText(getApplicationContext(), "请检查SD卡状态", Toast.LENGTH_SHORT).show();
            return false;
        }

        //获取剩余空间(字节)
        long freeSpace=Environment.getExternalStorageDirectory().getFreeSpace();

        //自动转化为B/KB/MB/GB
        String availableSize= Formatter.formatFileSize(getApplicationContext(),freeSpace);
        Toast.makeText(getApplicationContext(), "SD卡可用的空间有"+availableSize, Toast.LENGTH_SHORT).show();

        return true;
    }
}
