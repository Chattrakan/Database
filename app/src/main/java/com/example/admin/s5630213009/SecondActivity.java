package com.example.admin.s5630213009;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity  implements View.OnClickListener {
    EditText menu;
    EditText price;
    int type;
    MyProductDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        menu = (EditText) findViewById(R.id.menu);
        price = (EditText) findViewById(R.id.price);

        ((Button)findViewById(R.id.food)).setOnClickListener(this);
        ((Button)findViewById(R.id.drink)).setOnClickListener(this);
        ((Button)findViewById(R.id.entertain)).setOnClickListener(this);
        ((Button)findViewById(R.id.energy)).setOnClickListener(this);
        ((Button)findViewById(R.id.monthly)).setOnClickListener(this);
        ((Button)findViewById(R.id.partTime)).setOnClickListener(this);
        ((Button)findViewById(R.id.luckky)).setOnClickListener(this);

        ((Button)findViewById(R.id.save)).setOnClickListener(this);
        ((Button)findViewById(R.id.Back)).setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.food :
                Food();
                break;
            case R.id.drink :
                Drink();
                break;
            case R.id.entertain :
                Entertain();
                break;
            case R.id.energy :
                Energy();
                break;
            case R.id.monthly :
                Monthly();
                break;
            case R.id.partTime :
                PartTime();
                break;
            case R.id.luckky :
                Luckky();
                break;
            case R.id.save :
                Save();
                break;
            case R.id.Back :
                Back();
                break;
        }
    }
    public  void Food(){
        type=R.drawable.food;
        ShowMS("Food");

    }
    public  void Drink(){
        type = R.drawable.drink;
        ShowMS("Drink");
    }
    public  void Entertain(){
        type = R.drawable.entertain;
        ShowMS("Entertain");

    }
    public  void Energy(){
        type = R.drawable.energy;
        ShowMS("Energy");
    }
    public  void Monthly(){
        type = R.drawable.monthly;
        ShowMS("Monthly");

    }
    public  void PartTime(){
        ShowMS("PartTime");
        type = R.drawable.job;
    }
    public  void Luckky(){
        type = R.drawable.lucky;
        ShowMS("Luckky");
    }
    public void  Save(){
        Uri u =Uri.parse("content://product");
        ContentValues cv = new ContentValues();
        cv.put("icon",type);
        cv.put("productname", menu.getText().toString());
        cv.put("price", Integer.parseInt(price.getText().toString()));
        Uri nari = getContentResolver().insert(u,cv);
        finish();
    }
    public  void Back(){
        finish();
    }
    public void ShowMS (String ms){
        Toast.makeText(this, ms, Toast.LENGTH_SHORT).show();

    }
}