package com.hashtechtauch.kuman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.android.libraries.places.api.model.Place;
import com.hashtechtauch.kuman.model.Country;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

import io.opencensus.stats.Aggregation;

public class MainActivity extends AppCompatActivity
{
    private String strHomeImage;
    private TextView CountryName, newConfirmed, totalConfirmed, totalDeaths, totalRecovered;
    private String jsonLati, jsonLong, countryCode, link;
    private final static int REQUEST_PLACE_PICKER = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatImageView btn_Home_Image = findViewById(R.id.profilePicture);
        AppCompatImageView btn_Home_Notif = findViewById(R.id.notifBtn);
        AppCompatButton btn_Scan = findViewById(R.id.scanBtn);
        AppCompatImageView homeProfilePicture = findViewById(R.id.image);
        CountryName = findViewById(R.id.locationTv);
        newConfirmed = findViewById(R.id.newCaseNumber);
        totalConfirmed = findViewById(R.id.ConfirmedNumber);
        totalDeaths = findViewById(R.id.deathsNumber);
        totalRecovered = findViewById(R.id.recoveredNumber);

        strHomeImage = getIntent().getStringExtra("gambar");
        Glide.with(this).load(strHomeImage).into(homeProfilePicture);

        CountryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    String[] permissionMessages = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                    ActivityCompat.requestPermissions(MainActivity.this, permissionMessages, 1);
                    showPlacePicker();
                } else {
                    showPlacePicker();
                }
            }

        });

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
                Intent intent = new Intent(MainActivity.this, VideoPlayer.class);
                startActivity(intent);
            }
        });

    }

    private void showPlacePicker() { ;
        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
        builder.setAndroidApiKey("AIzaSyCVqW4F6qEEo8YZDmHEN2K8j6D3NtTX59g")
                .setMapsApiKey("AIzaSyA6zli3v7J1vh9sdJf30WhePFrM7RLHYuc");

        // If you want to set a initial location rather then the current device location.
        // NOTE: enable_nearby_search MUST be true.
        // builder.setLatLng(new LatLng(37.4219999, -122.0862462))

        try {
            Intent placeIntent = builder.build(MainActivity.this);
            startActivityForResult(placeIntent, REQUEST_PLACE_PICKER);
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_PLACE_PICKER) && (resultCode == RESULT_OK)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
//                Toast.makeText(this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();
//                latitude.setText(String.valueOf(Objects.requireNonNull(place.getLatLng()).latitude));
//                longitude.setText(String.valueOf(Objects.requireNonNull(place.getLatLng()).longitude));

                jsonLati = String.valueOf(Objects.requireNonNull(place.getLatLng()).latitude);
                jsonLong = String.valueOf(Objects.requireNonNull(place.getLatLng()).longitude);

                Request(jsonLati, jsonLong);
            }
        }
    }

    private void Request(String Latitude, String Longitude) {
        link = "https://api.opencagedata.com/geocode/v1/json?q="+Latitude+"%2C"+Longitude+"&key=d091601131184a4db55b15acc8ee6b46";
        String url = link;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("results");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                JSONObject obj2 = obj.getJSONObject("components");

                                countryCode = obj2.getString("country_code");
                            }
                            Check(countryCode);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void Check(String countryCode) {
        String url = "https://api.covid19api.com/summary";
        final Country CountryData = new Country();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("Countries");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj2 = array.getJSONObject(i);
                                if (obj2.getString("CountryCode").equalsIgnoreCase(countryCode)) {
                                    CountryData.setCountry(obj2.getString("Country"));
                                    CountryData.setCountryCode(obj2.getString("CountryCode"));
                                    CountryData.setSlug(obj2.getString("Slug"));
                                    CountryData.setNewConfirmed(obj2.getInt("NewConfirmed"));
                                    CountryData.setTotalConfirmed(obj2.getInt("TotalConfirmed"));
                                    CountryData.setNewDeaths(obj2.getInt("NewDeaths"));
                                    CountryData.setTotalDeaths(obj2.getInt("TotalDeaths"));
                                    CountryData.setNewRecovered(obj2.getInt("NewRecovered"));
                                    CountryData.setTotalRecovered(obj2.getInt("TotalRecovered"));
                                    CountryData.setDate(obj2.getString("Date"));
                                    break;
                                }
                            }
                            CountryName.setText("Current Location : " + CountryData.getCountry());
                            newConfirmed.setText(String.valueOf(CountryData.getNewConfirmed()));
                            totalConfirmed.setText(String.valueOf(CountryData.getTotalConfirmed()));
                            totalDeaths.setText(String.valueOf(CountryData.getTotalDeaths()));
                            totalRecovered.setText(String.valueOf(CountryData.getTotalRecovered()));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}
