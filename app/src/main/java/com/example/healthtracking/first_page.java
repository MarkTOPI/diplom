package com.example.healthtracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class first_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_first_page);
    }

    public void btnLogin(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void btnSignup(View view) {
        Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);
    }
}