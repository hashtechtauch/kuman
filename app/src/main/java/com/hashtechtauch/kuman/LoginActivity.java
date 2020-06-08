package com.hashtechtauch.kuman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth;
    private CallbackManager mCallbackManager;
    private static final String TAG = "Authenticate Token";
    private AccessTokenTracker accessTokenTracker;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
        ConstraintLayout login_Facebook = findViewById(R.id.fbSignInBtn);

        login_Facebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel()
                    {
                        Log.d(TAG, "facebook:onCancel");
                        Toast.makeText(LoginActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error)
                    {
                        Log.d(TAG, "facebook:onError", error);
                        Toast.makeText(LoginActivity.this, "Error. Please try again later", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        // Jika user sudah login, tidak perlu login lagi
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            i.putExtra("gambar",user.getPhotoUrl().toString());
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token)
    {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();



                            //dari class SharedPrefs
                            sharedPrefs = SharedPrefs.getInstance(LoginActivity.this);
                            sharedPrefs.putData("nama",user.getDisplayName());
                            sharedPrefs.putData("email",user.getEmail());
                            sharedPrefs.putData("gambar",user.getPhotoUrl().toString());
                            sharedPrefs.putData("id",user.getUid());


                            // Untuk kirim data ke MainActivity.java
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            i.putExtra("gambar",user.getPhotoUrl().toString());
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
