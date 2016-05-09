package com.example.admin.s5630213009;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    itemAdapter adapter;
    ListView listView;
    menuList m;
    public static ArrayList<menuList> arrayList = new ArrayList<menuList>();
    int x =1;
    MyProductDB db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.add)).setOnClickListener(this);
        ((Button) findViewById(R.id.refresh)).setOnClickListener(this);
        ((Button) findViewById(R.id.search)).setOnClickListener(this);

        Uri u = Uri.parse("content://product");
        Cursor c =getContentResolver().query(u,null,null,null,null);
        while (c.moveToNext()){
            arrayList.add(new menuList(c.getInt(1),c.getString(2),c.getInt(3)));
        }


        adapter = new itemAdapter(this, arrayList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add :
                Add();
                break;
            case  R.id.refresh:
                Refresh();
                break;
            case  R.id.search:
                Search();
                break;
        }

    }

    public void  Add(){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(intent);
    }
    public  void Refresh(){
        adapter.clear();
        Uri u = Uri.parse("content://product");
        Cursor c =getContentResolver().query(u,null,null,null,null);
        while (c.moveToNext()){
            arrayList.add(new menuList(c.getInt(1), c.getString(2), c.getInt(3)));
            ShowMS("Refresh !!!");
        }
        adapter = new itemAdapter(this, arrayList);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
    }
    public  void Search(){
        Uri u = Uri.parse("content://product");
        EditText search = (EditText)findViewById(R.id.menuName);
        String ex = "productname =?";
        String [] a = new String[]{search.getText().toString()};
        Cursor c =getContentResolver().query(u, null, ex, a, null);
        c.moveToNext();
        if(c.getCount()<=0){
            ShowMS("Not Found");
        }else{
            String line = c.getString(2)+" "+c.getString(3);
            ShowMS(line);
        }
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
        adb.setTitle("Select");
        final CharSequence[] items = { "Delete"};
        final int positionToRemove = position;
        adb.setItems(items, new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                arrayList.remove(positionToRemove);
                Uri u = Uri.parse("content://product");
                Cursor c =getContentResolver().query(u, null, null, null, null);
                String ex = "_id =?";
                c.moveToPosition(position);
                String name = c.getString(c.getColumnIndex(MyProductDB.COL_ID));
                String []e = new String[]{String.valueOf(name)};
                int row = getContentResolver().delete(u,ex,e);
                ShowMS("Delete");
                listView.setAdapter(adapter);
            }});
        adb.show();

    }
    public void ShowMS (String ms){
        Toast.makeText(this, ms, Toast.LENGTH_SHORT).show();
    }








}
