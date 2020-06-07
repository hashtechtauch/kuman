package com.hashtechtauch.kuman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class NotificationActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        AppCompatImageView btn_Back = findViewById(R.id.backBtn);
        AppCompatImageView btn_Delete = findViewById(R.id.deleteBtn);

        btn_Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NotificationActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }
}
