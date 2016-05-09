package com.example.admin.s5630213009;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class itemAdapter extends ArrayAdapter<menuList> {
    itemAdapter(Context context, ArrayList<menuList> ob) {
        super(context, 0,ob);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        menuList item = getItem(position);
        item.toString();



        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.menubar, parent,false);
        }


        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtMenu);
        TextView cash = (TextView) convertView.findViewById(R.id.money);

        icon.setImageResource(item.getIcon());
        txtName.setText(item.getMenuName().toString());
        cash.setText(String.valueOf(item.getCash()).toString());



        return convertView;


    }


}