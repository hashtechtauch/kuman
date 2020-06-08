package com.hashtechtauch.kuman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class PopUpWindowActivity extends Activity
{
    private String strPopNama, strPopEmail, strPopImage, strPopId;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow_cardview);
        AppCompatImageView popImage = findViewById(R.id.profilePicture);
        TextView popNama = findViewById(R.id.tvName);
        TextView popEmail = findViewById(R.id.tvEmail);
        ConstraintLayout btn_accSetting = findViewById(R.id.accSettingsBtn);
        ConstraintLayout btn_Logout = findViewById(R.id.logoutBtn);

        //untuk button popout
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*.9), (int) (height*.8));

        //dari class SharedPrefs
        sharedPrefs = SharedPrefs.getInstance(PopUpWindowActivity.this);
        strPopNama = sharedPrefs.getData("nama","Kanon Kanade");
        strPopEmail = sharedPrefs.getData("email","kanonkanade@naver.jp");
        strPopImage = sharedPrefs.getData("gambar",null);
        strPopId = sharedPrefs.getData("id",null);


        Glide.with(this).load(strPopImage).into(popImage);
        popNama.setText(strPopNama);
        popEmail.setText(strPopEmail);

        btn_accSetting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PopUpWindowActivity.this, AccountSettingActivity.class);
                startActivity(intent);
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PopUpWindowActivity.this, LoginActivity.class);
                sharedPrefs.clear();
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(intent);
                finish();
            }
        });





    }
}
