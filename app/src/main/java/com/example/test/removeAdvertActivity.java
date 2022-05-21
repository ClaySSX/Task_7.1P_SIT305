package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;


import com.example.test.data.DatabaseHelper;
import com.example.test.model.itemAdvertised;

public class removeAdvertActivity extends AppCompatActivity {
    DatabaseHelper db;

    TextView itemName;
    TextView itemDate;
    TextView itemLocation;

    Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_advert);

        db = new DatabaseHelper(this);

        itemName = findViewById(R.id.itemTextView);
        itemDate = findViewById(R.id.dateTextView);
        itemLocation = findViewById(R.id.locationTextView);
        removeButton = findViewById(R.id.removeButton);

        Intent intent = getIntent();
        String  currentItemsName = intent.getStringExtra("selectedad");

        itemName.setText(currentItemsName);

        //Declaring current Items name
        int foundAdNumber = db.findAdNumber(currentItemsName);
        itemAdvertised recievedAd = db.findItem(foundAdNumber);

        itemName.setText(recievedAd.getItemName());
        itemDate.setText(recievedAd.getItemDate());
        itemLocation.setText(recievedAd.getItemLocation());

        //Function for removal of adverts
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removeAdvert(foundAdNumber);
                Intent intentRemove = new Intent(removeAdvertActivity.this, MainActivity.class);
                startActivity(intentRemove);
            }
        });
    }
}