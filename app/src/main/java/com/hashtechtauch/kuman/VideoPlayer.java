package com.hashtechtauch.kuman;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Random randomizer = new Random();
        List<Integer> listVideo = new ArrayList<Integer>();
        listVideo.add(R.raw.babyshark);
        listVideo.add(R.raw.babyshark2);
        listVideo.add(R.raw.pikotaro);
        listVideo.add(R.raw.pinkfong_animated);

        int random = listVideo.get(randomizer.nextInt(listVideo.size()));


        VideoView videoView = findViewById(R.id.video_view);
        String path = "android.resource://" + getPackageName() + "/" + random;
        Log.d("link",path);
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
    }
}