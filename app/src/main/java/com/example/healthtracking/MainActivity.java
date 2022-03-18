package com.example.healthtracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        setContentView(R.layout.activity_main);
    }

    public void btnWaterPlus(View view) {
        TextView count = (TextView) findViewById(R.id.textWaterNow);
        CharSequence text = count.getText();
        int pz = Integer.valueOf(text.toString());
        pz++;
        count.setText(Integer.toString(pz));
    }

    public void btnWaterMinus(View view) {
        TextView count = (TextView) findViewById(R.id.textWaterNow);
        CharSequence text = count.getText();
        int pz = Integer.valueOf(text.toString());
        if (pz <= 0) {
            Toast toast = Toast.makeText(this, "The number of glasses cannot be less than 0!",Toast.LENGTH_LONG);
            toast.show();
            pz = 0;
        } else {
            pz--;
            count.setText(Integer.toString(pz));
        }
    }
}