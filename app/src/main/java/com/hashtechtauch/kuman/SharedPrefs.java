package com.hashtechtauch.kuman;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs
{
    private static SharedPrefs store;
    private SharedPreferences SP;
    private static String filename="Keys";

    private SharedPrefs(Context context)
    {
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
