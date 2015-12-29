package com.c3.vero.c3_19_sqlite_exise;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    private MyDboparation myDboparation;
    private SimpleCursorAdapter mAdapter;
    private Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        registerForContextMenu(getListView());
        myDboparation =new MyDboparation(MainActivity.this);
        myDboparation.openOrCreateDb();
        bindData();
    }

    public void bindData(){
        mCursor=myDboparation.selectAll();
        mAdapter=new SimpleCursorAdapter(MainActivity.this,android.R.layout.simple_expandable_list_item_2,mCursor,new String[]{"_id","age"},new int[]{android.R.id.text1,android.R.id.text2});
        setListAdapter(mAdapter);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        Bundle bundle =args;
        String arg=bundle.getString("value");
        final int position=bundle.getInt("position");
        mCursor.moveToPosition(position);
        final int temp_id=mCursor.getInt(0);
        final String temp_name=mCursor.getString(1);
        final int temp_age=mCursor.getInt(2);

        View dialogView=getLayoutInflater().from(this).inflate(R.layout.dialog, null);
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);
        AlertDialog dialog;

        if (id==1&&arg=="add"){
                    builder
                    .setTitle("add")
                    .setView(R.layout.dialog)
                    .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog alertDialog = (AlertDialog) dialog;
                            EditText username = (EditText) alertDialog.findViewById(R.id.username);
                            EditText age = (EditText) alertDialog.findViewById(R.id.age);
                            long i =myDboparation.insert(username.getText().toString(), Integer.parseInt(age.getText().toString()));
                            Toast.makeText(MainActivity.this,"插入返回值："+i,Toast.LENGTH_SHORT).show();
                            bindData();
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
                           int i= myDboparation.update(username.getText().toString(), Integer.parseInt(age.getText().toString()),"_id=?",new String[]{String.valueOf(temp_id)});
                            Toast.makeText(MainActivity.this,"修改返回值："+i,Toast.LENGTH_SHORT).show();
                            bindData();
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
            AlertDialog delete_dialog =new AlertDialog.Builder(MainActivity.this)
                    .setTitle("delete???")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int i=myDboparation.delete("_id=?", new String[]{String.valueOf(temp_id)});
                            Toast.makeText(MainActivity.this,"删除返回值："+i,Toast.LENGTH_SHORT).show();
                            bindData();
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
