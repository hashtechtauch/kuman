package com.hashtechtauch.kuman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;


public class ScanActivity extends AppCompatActivity {
    private CameraView cameraKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        cameraKitView = findViewById(R.id.camera);

        final Button button = findViewById(R.id.btnScan);

        button.setOnClickListener(view -> {
            cameraKitView.start();
            cameraKitView.captureImage();

        });

        cameraKitView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraKitView.getWidth(), cameraKitView.getHeight(),false);
                cameraKitView.stop();


                FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);

                FirebaseVisionImageLabeler labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler();

                labeler.processImage(firebaseVisionImage).addOnSuccessListener(
                        firebaseVisionImageLabels -> {
                            for(FirebaseVisionImageLabel label: firebaseVisionImageLabels){
                                String text = label.getText();
                                if(text.equals("Hand")){
                                    //TODO: Launch Virus Alert Dialog Box
                                }
                            }
                        }
                ).addOnFailureListener(e -> {

                });
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.start();
    }

    @Override
    protected void onPause() {
        cameraKitView.stop();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.stop();
        super.onStop();
    }



}