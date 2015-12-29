package com.c3.vero.c3_99_connect_with_contentprovider;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends ListActivity {
    private MyDboparation myDboparation=null;
    private SimpleCursorAdapter mAdapter=null;
    private ContentResolver mResolver=null;
    private Uri mUri=null;
    private Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerForContextMenu(getListView());
        mResolver=getContentResolver();
        mUri=Uri.parse("content://vero.demo.myprovider/user");

        reveiveData();
    }

    public void reveiveData(){
        mCursor=mResolver.query(mUri, null, null, null, null);
        mAdapter=new SimpleCursorAdapter(MainActivity.this,android.R.layout.simple_expandable_list_item_2,mCursor,new String[]{"name","age"},new int[]{android.R.id.text1,android.R.id.text2});
        setListAdapter(mAdapter);

    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        Bundle bundle =args;
        String arg=bundle.getString("value");
        final int position=bundle.getInt("position");



        View dialogView=getLayoutInflater().from(this).inflate(R.layout.dialog, null);
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);
        AlertDialog dialog;

        if (id==1&&arg.equals("add")){
                    builder
                    .setTitle("add")
                    .setView(R.layout.dialog)
                    .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog alertDialog = (AlertDialog) dialog;
                            EditText username = (EditText) alertDialog.findViewById(R.id.username);
                            EditText age = (EditText) alertDialog.findViewById(R.id.age);
                            ContentValues values=new ContentValues();
                            values.put("name",username.getText().toString());
                            values.put("age", Integer.parseInt(age.getText().toString()));
                            Uri insert=mResolver.insert(mUri,values);
                            reveiveData();
                            Toast.makeText(MainActivity.this,"修改返回值："+ insert,Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            dialog =builder.create();
            return dialog;
        }
        if (id==2&&arg.equals("edit")){
            mCursor.moveToPosition(position);
            final int temp_id=mCursor.getInt(0);
            final String temp_name = mCursor.getString(1);
            final int temp_age=mCursor.getInt(2);
            EditText username = (EditText) dialogView.findViewById(R.id.username);
            EditText age = (EditText) dialogView.findViewById(R.id.age);
            username.setText(temp_name);
            age.setText(String.valueOf(temp_age));

            builder
                    .setTitle("edit")
                    .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog alertDialog = (AlertDialog) dialog;
                            EditText username = (EditText) alertDialog.findViewById(R.id.username);
                            EditText age = (EditText) alertDialog.findViewById(R.id.age);
                            ContentValues values=new ContentValues();
                            values.put("name",username.getText().toString());
                            values.put("age", Integer.parseInt(age.getText().toString()));
                            int update=mResolver.update(mUri,values,"_id=?",new String[]{String.valueOf(temp_id)});
                            Toast.makeText(MainActivity.this,"修改返回值："+update,Toast.LENGTH_SHORT).show();
                            reveiveData();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });


            dialog =builder.create();
            return dialog;
        }
        if (id==3&&arg.equals("del")){
            mCursor.moveToPosition(position);
            final int temp_id=mCursor.getInt(0);
            final String temp_name = mCursor.getString(1);
            final int temp_age=mCursor.getInt(2);
            AlertDialog delete_dialog =new AlertDialog.Builder(MainActivity.this)
                    .setTitle("delete???")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int delete=mResolver.delete(mUri,"_id=?",new String[]{String.valueOf(temp_id)});
                            reveiveData();
                            Toast.makeText(MainActivity.this,"删除返回值："+delete,Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            return delete_dialog;
        }
        return  null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(1,1,1,"add");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1) {
            Bundle bundle=new Bundle();
            bundle.putString("value", "add");
            onCreateDialog(1,bundle).show();
//            Toast.makeText(MainActivity.this, "" +id, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.dialog_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id=item.getItemId();
        Bundle bundle=new Bundle();

        if (id==R.id.update){
            bundle.putString("value", "edit");
            bundle.putInt("position",menuInfo.position);
            onCreateDialog(2, bundle).show();
        }
        if (id==R.id.delete){
            bundle.putString("value", "del");
            bundle.putInt("position",menuInfo.position);
            onCreateDialog(3, bundle).show();

        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
    }

}
