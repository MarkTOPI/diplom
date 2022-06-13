package com.example.healthtracking.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;

public class splash_screen extends AppCompatActivity {

    private ImageView image;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image = findViewById(R.id.splashScreenImage);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        image.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash_screen.this, first_page.class));
                finish();
            }
        }, 3000);
    }
}