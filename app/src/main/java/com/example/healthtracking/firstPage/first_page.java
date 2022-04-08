package com.example.healthtracking.firstPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.healthtracking.MainActivity;
import com.example.healthtracking.login.LoginPage;
import com.example.healthtracking.R;
import com.example.healthtracking.registration.RegistrationPage;

public class first_page extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_first_page);

        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        if (token != "") {
            goMain();
        }
    }

    public void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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