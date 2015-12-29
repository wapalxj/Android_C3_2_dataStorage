package com.c3.vero.c3_2_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Button editbtn;
    private Button readbtn;
    private CheckBox checkBox;
    private EditText editText;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        sharedPreferences=getSharedPreferences("vnix",MODE_PRIVATE);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("vero", checkBox.isChecked());
                editor.putString("name", editText.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"commit success!",Toast.LENGTH_SHORT).show();
            }
        });
        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取对象
                if (sharedPreferences!=null){
                    checkBox.setChecked(sharedPreferences.getBoolean("vero", false));
                    editText.setText(sharedPreferences.getString("name", null));
                }else {
                    Toast.makeText(MainActivity.this,"no xml",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void findView(){
        editbtn=(Button)findViewById(R.id.editbtn);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        editText=(EditText)findViewById(R.id.edittext);
        readbtn=(Button)findViewById(R.id.readbtn);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
