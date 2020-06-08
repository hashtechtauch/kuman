package com.hashtechtauch.kuman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

<<<<<<< Updated upstream
public class MainActivity extends AppCompatActivity {
=======
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.libraries.places.api.model.Place;
import com.google.firebase.auth.FirebaseAuth;
import com.hashtechtauch.kuman.model.Country;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

import io.opencensus.stats.Aggregation;
import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity
{
    private String strHomeImage, strPopNama, strPopEmail, strPopImage, strPopId;
    private TextView CountryName, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered;
    private String jsonLati, jsonLong, countryCode, link;
    private final static int REQUEST_PLACE_PICKER = 1001;
    SharedPrefs sharedPrefs;
    Country CountryData;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
=======

        AppCompatImageView btn_Home_Image = findViewById(R.id.profilePicture);
        AppCompatImageView btn_Home_Notif = findViewById(R.id.notifBtn);
        AppCompatButton btn_Scan = findViewById(R.id.scanBtn);
        AppCompatImageView homeProfilePicture = findViewById(R.id.image);


        CountryName = findViewById(R.id.locationTv);
        newConfirmed = findViewById(R.id.newCaseNumber);
        totalConfirmed = findViewById(R.id.ConfirmedNumber);
        totalDeaths = findViewById(R.id.deathsNumber);
        totalRecovered = findViewById(R.id.recoveredNumber);

        //dari class LoginActivity.java
        sharedPrefs = SharedPrefs.getInstance(MainActivity.this);
        strHomeImage = sharedPrefs.getData("gambar",null);
        Glide.with(this).load(strHomeImage).into(btn_Home_Image);

        LinearLayout washHandLayout = findViewById(R.id.washHandLayout);
        LinearLayout leaderboardLayout = findViewById(R.id.leaderboardLayout);
        LinearLayout scanHandLayout = findViewById(R.id.scanHandLayout);

        washHandLayout.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VideoPlayer.class);
            startActivity(intent);
        });

        leaderboardLayout.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
            startActivity(intent);
        });

        scanHandLayout.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
            startActivity(intent);
        });


        CountryName.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                String[] permissionMessages = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(MainActivity.this, permissionMessages, 1);
                showPlacePicker();
            } else {
                showPlacePicker();
            }
        });

        btn_Home_Image.setOnClickListener(v -> {
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View popUpView = layoutInflater.inflate(R.layout.popupwindow_cardview, null);

            //membuat pop up window
            int width = CardView.LayoutParams.WRAP_CONTENT;
            int height = CardView.LayoutParams.WRAP_CONTENT;
            boolean touchOutside = true;
            final PopupWindow popupWindow = new PopupWindow(popUpView,width,height,touchOutside);

            //tampilkan pop up window
            popupWindow.showAtLocation(popUpView, Gravity.CENTER, 0, 0);

            //untuk gelapkan background ketika ditap
            View container = (View) popupWindow.getContentView().getParent();
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.4f;
            windowManager.updateViewLayout(container, p);

            AppCompatImageView popImage = popUpView.findViewById(R.id.popPicture);
            TextView popNama = popUpView.findViewById(R.id.tvName);
            TextView popEmail = popUpView.findViewById(R.id.tvEmail);
            ConstraintLayout btn_accSetting = popUpView.findViewById(R.id.accSettingsBtn);
            ConstraintLayout btn_Logout = popUpView.findViewById(R.id.logoutBtn);

            //dari class SharedPrefs
//                sharedPrefs = SharedPrefs.getInstance(MainActivity.this);
            strPopNama = sharedPrefs.getData("nama","Kanon Kanade");
            strPopEmail = sharedPrefs.getData("email","kanonkanade@naver.jp");
            strPopImage = sharedPrefs.getData("gambar",null);
            strPopId = sharedPrefs.getData("id",null);

            Glide.with(popUpView).load(strPopImage).into(popImage);
            popNama.setText(strPopNama);
            popEmail.setText(strPopEmail);

            btn_accSetting.setOnClickListener(v1 -> {
                Intent intent = new Intent(MainActivity.this, AccountSettingActivity.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "bottom-to-up");
                finish();
            });

            btn_Logout.setOnClickListener(v2 -> {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                sharedPrefs.clear();
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Logout Success.",
                        Toast.LENGTH_SHORT).show();
                finish();
            });

        });

        btn_Home_Notif.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
            CustomIntent.customType(this, "left-to-right");
        });

        btn_Scan.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoPlayer.class);
            startActivity(intent);
        });

>>>>>>> Stashed changes
    }
}
