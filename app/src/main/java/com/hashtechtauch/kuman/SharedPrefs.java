package com.hashtechtauch.kuman;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs
{
    //Biar gampang lihat datanya, gw tulis aja di sini nama-namanya
    //nama, email, gambar, id

    private static SharedPrefs store;
    private SharedPreferences SP;

    private SharedPrefs(Context context)
    {
        String filename = "Keys";
        SP = context.getApplicationContext().getSharedPreferences(filename,0);
    }

    public static SharedPrefs getInstance(Context context)
    {
        if (store == null)
        {
            store = new SharedPrefs(context);
        }
        return store;
    }

    public void putData(String key, String value)
    {
        SharedPreferences.Editor editor = SP.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public String getData(String key, String value)
    {
        return SP.getString(key, value);
    }


    public void clear()
    {
        SharedPreferences.Editor editor = SP.edit();
        editor.clear();
        editor.apply();
    }

}
