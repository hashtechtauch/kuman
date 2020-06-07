package com.hashtechtauch.kuman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity
{
    private String strHomeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatImageView btn_Home_Image = findViewById(R.id.profilePicture);
        AppCompatImageView btn_Home_Notif = findViewById(R.id.notifBtn);
        AppCompatButton btn_Scan = findViewById(R.id.scanBtn);
        AppCompatImageView homeProfilePicture = findViewById(R.id.image);

        strHomeImage = getIntent().getStringExtra("gambar");
        Glide.with(this).load(strHomeImage).into(homeProfilePicture);

        btn_Home_Image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, PopUpWindowActivity.class));
            }
        });

        btn_Home_Notif.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        btn_Scan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });


    }
}
