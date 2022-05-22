package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import com.example.test.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    Button showAdverts;
    Button createAdvert;
    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAdvert = findViewById(R.id.createAdvertButton);
        showAdverts = findViewById(R.id.showAdvertsButton);
        showMap = findViewById(R.id.showMapsButton);

        createAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainActivity.this, createAdvertActivity.class);
                startActivity(createIntent);
            }
        });

        showAdverts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAdsIntent = new Intent(MainActivity.this, showAllAdvertsActivity.class);
                startActivity(showAdsIntent);
            }
        });

        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showMapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(showMapIntent);
            }
        });
    }
}