package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import com.example.test.data.DatabaseHelper;
import com.example.test.model.itemAdvertised;

public class createAdvertActivity extends AppCompatActivity {


    DatabaseHelper db;

    EditText usersName;
    EditText usersNumber;
    EditText userItemName;
    EditText itemDate;
    EditText itemLocation ;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        usersName = findViewById(R.id.userEnteredName);
        usersNumber = findViewById(R.id.userEnteredPhone);
        userItemName = findViewById(R.id.enteredItemTextView);
        itemDate = findViewById(R.id.itemFoundDateEditText);
        itemLocation =findViewById(R.id.locationFoundEditText);

        saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

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