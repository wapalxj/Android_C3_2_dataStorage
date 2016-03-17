package com.c31_04_hm_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        remember();
    }
    public void init(){
        username= (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        cSave= (CheckBox)findViewById(R.id.checkbox);
        save= (Button)findViewById(R.id.save);
        sp=getSharedPreferences("Config",MODE_PRIVATE);
        editor=sp.edit();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cSave.isChecked()) {
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.putBoolean("cSave", cSave.isChecked());
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "勾选，保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    editor.clear();
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "没有勾选，没有保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void remember(){
        String username=sp.getString("username", "xxx");
        String password=sp.getString("password","xxx");
        boolean cSave=sp.getBoolean("cSave",false);
        this.username.setText(username);
        this.password.setText(password);
        this.cSave.setChecked(cSave);

    }
}
