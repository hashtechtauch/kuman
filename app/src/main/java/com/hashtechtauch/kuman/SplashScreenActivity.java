package com.hashtechtauch.kuman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_bg);
        new Handler().postDelayed(() -> {
            SharedPrefs sharedPreferences = SharedPrefs.getInstance(SplashScreenActivity.this);
            String user = sharedPreferences.getData("user", "");
            if(user.equals("")){
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
            finish();
        }, 3000);

    }
}
