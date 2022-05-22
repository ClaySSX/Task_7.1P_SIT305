package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import com.example.test.data.DatabaseHelper;
import com.example.test.model.itemAdvertised;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class createAdvertActivity extends AppCompatActivity {


    private static final String TAG = "Running";

    DatabaseHelper db;

    EditText usersName;
    EditText usersNumber;
    EditText userItemName;
    EditText itemDate;
    EditText itemLocation ;
    LocationManager locationManager;
    LocationListener locationListener;
    Button saveButton;
    Button locationButton;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        usersName = findViewById(R.id.userEnteredName);
        usersNumber = findViewById(R.id.userEnteredPhone);
        userItemName = findViewById(R.id.enteredItemTextView);
        itemDate = findViewById(R.id.itemFoundDateEditText);
        itemLocation =findViewById(R.id.locationFoundEditText);

        locationButton = findViewById(R.id.locationButton);
        saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);


        // Initialize the SDK
        Places.initialize(getApplicationContext(), getString(R.string.Places_API));

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);


        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // Place onto map >>>>>>
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() +  ", " + place.getLatLng());
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        //--------------------------------------------------------------------------------------------

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Toast.makeText(createAdvertActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else{
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
        }


        //--------------------------------------------------------------------------
        //Converting inserts to String
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameofuser = usersName.getText().toString();
                String usernumber = usersNumber.getText().toString();
                String useritem = userItemName.getText().toString();
                String itemdate = itemDate.getText().toString();
                String itemlocation = itemLocation.getText().toString();

                //Inserting new item into Database with DB helper
                long result = db.insertNewItem(new itemAdvertised(nameofuser, usernumber, useritem, itemdate, itemlocation));

                //Checking whether insertion is correct, if not throwing a toast
                if (result > 0){
                    Intent intent = new Intent(createAdvertActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(createAdvertActivity.this, "Invalid advert, please try to re-enter item", Toast.LENGTH_SHORT).show();
                }
            }

        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

/*
        saveButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {

        String username = sUsernameEditText.getText().toString();
        String password = sPasswordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (password.equals(confirmPassword))
        {
        long result = db.insertUser(new User(username, password));
        if (result > 0)
        {
        Toast.makeText(SignupActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
        Toast.makeText(SignupActivity.this, "Registration error!", Toast.LENGTH_SHORT).show();
        }

        }
        else
        {
        Toast.makeText(SignupActivity.this, "Two passwords do not match!", Toast.LENGTH_SHORT).show();
        }
        }
        });

*/