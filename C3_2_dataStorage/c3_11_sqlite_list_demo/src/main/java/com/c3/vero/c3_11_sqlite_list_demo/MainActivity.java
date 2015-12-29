package com.c3.vero.c3_11_sqlite_list_demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends ListActivity {
    private final int DIALOG_ADD=123;
    private final int DIALOG_DEL=456;
    private DBOperation dbOperation;
    private AlertDialog alertDialog;
    private SimpleCursorAdapter adapter;
    private  Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        dbOperation= new DBOperation(MainActivity.this);
        dbOperation.openOrCreateDatabae();
        display();
    }

    public void display(){
        cursor =dbOperation.selectALL();
        if (cursor!=null) {
            adapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_2,
                    cursor, new String[]{"userName", "userAge"}, new int[]{android.R.id.text1, android.R.id.text2});
            setListAdapter(adapter);
        }
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        //add
        if (id == DIALOG_ADD) {
            alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("add ?")
                    .setView(R.layout.dialog)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog alertDialog = (AlertDialog) dialog;
                            EditText username=(EditText)alertDialog.findViewById(R.id.username);
                            EditText age=(EditText)alertDialog.findViewById(R.id.age);
                            dbOperation.insert(username.getText().toString(), Integer.parseInt(age.getText().toString()));
                            display();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbOperation.delete();
                            alertDialog.dismiss();
                        }
                    })
                    .create();
            return alertDialog;
        }
        //delete
        if (id == DIALOG_DEL) {
            alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("delete ?")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbOperation.delete();
                            display();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    })
                    .create();
            return alertDialog;
        }
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        return super.onCreateDialog(id, args);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(1,1,1,"add");
        menu.add(1,2,2,"delete");
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1) {
            onCreateDialog(123).show();
            return true;
        }else if (id==2){
            onCreateDialog(456).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        dbOperation.close();
        super.onStop();
    }
}
