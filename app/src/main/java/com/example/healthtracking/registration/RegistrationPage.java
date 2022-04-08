package com.example.healthtracking.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthtracking.R;
import com.example.healthtracking.firstPage.first_page;
import com.example.healthtracking.login.LoginPage;

public class RegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_registration_page);
    }

    public void btnRegistration(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void btnBack(View view) {
        Intent intent = new Intent(this, first_page.class);
        startActivity(intent);
    }
}