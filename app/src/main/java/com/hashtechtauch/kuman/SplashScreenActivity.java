package com.hashtechtauch.kuman;

import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
=======
>>>>>>> devV2
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity
{
<<<<<<< HEAD
=======
    private static int SPLASH_TIME_OUT = 3000;
>>>>>>> devV2
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_bg);
<<<<<<< HEAD
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
=======
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
>>>>>>> devV2

    }
}
