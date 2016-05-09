package com.example.admin.s5630213009;

import java.util.ArrayList;

/**
 * Created by admin on 20/11/2558.
 */
public class menuList extends MainActivity {

    int icon;
    String menuName;
    int cash ;

    public ArrayList<String> wordarray;
    public menuList(){
    }

    public menuList(int icon, String menuName, int cash){
        this.icon = icon;
        this.menuName = menuName;
        this.cash = cash ;
        this.wordarray = new ArrayList<String>();
    }
    public int getIcon() {
        return icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCash() {
        return cash;
    }


}

