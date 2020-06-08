package com.hashtechtauch.kuman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountSettingActivity extends AppCompatActivity
{
    private String strImage, strNama, strEmail, strId;
    private String jsonLati, jsonLong;
    private FirebaseFirestore mFirebaseFirestore;
    private final static int REQUEST_PLACE_PICKER = 1001;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        ImageView accountImage = findViewById(R.id.profilePicture);
        TextView accountName = findViewById(R.id.tvName);
        TextView accountEmail = findViewById(R.id.tvEmail);

        TextView counter = findViewById(R.id.counter);
        AppCompatImageView btn_Back = findViewById(R.id.backBtn);
        ConstraintLayout btn_Change_Name = findViewById(R.id.changeNameBtn);
        ConstraintLayout btn_Change_Location = findViewById(R.id.changeLocBtn);
        ConstraintLayout btn_Toggle_Notification = findViewById(R.id.toggleNotifBtn);
        ConstraintLayout btn_Remove_SharedPrefence = findViewById(R.id.removeUserPrefsBtn);

        //dari class SharedPrefs
        sharedPrefs = SharedPrefs.getInstance(AccountSettingActivity.this);
        strNama = sharedPrefs.getData("nama","Kanon Kanade");
        strEmail = sharedPrefs.getData("email","kanonkanade@naver.jp");
        strImage = sharedPrefs.getData("gambar",null);
        strId = sharedPrefs.getData("id",null);

        Glide.with(this).load(strImage).into(accountImage);
        accountName.setText(strNama);
        accountEmail.setText(strEmail);

        if(sharedPrefs != null)
        {
            counter.setText("0");
        }

        btn_Back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AccountSettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_Change_Name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        btn_Change_Location.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        btn_Toggle_Notification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        btn_Remove_SharedPrefence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPrefs.clear();
                if(sharedPrefs == null)
                {
                    Toast.makeText(AccountSettingActivity.this, "Data successfully removed.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AccountSettingActivity.this, "No data to be deleted.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
