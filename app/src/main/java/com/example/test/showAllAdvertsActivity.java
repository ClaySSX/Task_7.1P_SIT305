package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;

import com.example.test.data.DatabaseHelper;
import com.example.test.model.itemAdvertised;

import java.util.ArrayList;
import java.util.List;

public class showAllAdvertsActivity extends AppCompatActivity {
    DatabaseHelper db;

    ListView allAdsListView;
    ArrayList<String> adsArrayList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adverts);

        allAdsListView = findViewById(R.id.allAdvertsListView);

        adsArrayList = new ArrayList<>();
        db = new DatabaseHelper(showAllAdvertsActivity.this);

        List<itemAdvertised> allAdsList = db.viewAllAds();

        //For statement adding items list to the list array view
        for (itemAdvertised adItem: allAdsList) {
            adsArrayList.add(adItem.getItemName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adsArrayList);
        allAdsListView.setAdapter(adapter);

        //Function for displaying all posted items
        allAdsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), removeAdvertActivity.class);

                String pAd = allAdsList.get(position).getItemName();
                intent.putExtra("selectedad", pAd);

                startActivity(intent);
            }
        });
    }
}

/*(
      updateButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {

        String username = sUsernameEditText2.getText().toString();
        String password = sPasswordEditText2.getText().toString();
        String confirmPassword = confirmPasswordEditText2.getText().toString();

        if (password.equals(confirmPassword)){
        int updateRow  = db.updatePassword(new User(username, password));
            if (updateRow > 0){
        Toast.makeText(ChangePasswordActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(ChangePasswordActivity.this, "No row found!", Toast.LENGTH_SHORT).show();
        }

        }
        else
        {
        Toast.makeText(ChangePasswordActivity.this, "Two passwords do not match!", Toast.LENGTH_SHORT).show();
        }
        }
        });

        }
  }
        */
