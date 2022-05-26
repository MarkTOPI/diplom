package com.example.healthtracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
//    public void btnWaterPlus(View view) {
//        TextView count = (TextView) findViewById(R.id.textWaterNow);
//        CharSequence text = count.getText();
//        int pz = Integer.valueOf(text.toString());
//        pz++;
//        count.setText(Integer.toString(pz));
//    }
//
//    public void btnWaterMinus(View view) {
//        TextView count = (TextView) findViewById(R.id.textWaterNow);
//        CharSequence text = count.getText();
//        int pz = Integer.valueOf(text.toString());
//        if (pz <= 0) {
//            btnMinus.setEnabled(false);
//        } else {
//            pz--;
//            count.setText(Integer.toString(pz));
//        }
//    }
}