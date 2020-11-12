package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserAccountDisplay extends AppCompatActivity {
    ParseQuery<ParseObject> query = ParseQuery.getQuery("itemName");
    EditText etFirstNameUpdate;
    EditText etUpdateLastName;
    EditText etUpdateUsername;
    EditText etUpdatePassword;
    RecyclerView rvPastOrders;
    Button btnUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_display);

        etFirstNameUpdate = findViewById(R.id.etFirstNameUpdate);
        etUpdateLastName = findViewById(R.id.etUpdateLastName);
        etUpdateUsername = findViewById(R.id.etUpdateUsername);
        etUpdatePassword = findViewById(R.id.etUpdatePassword);
        rvPastOrders = findViewById(R.id.rvPastOrders);
        btnUpdate = findViewById(R.id.btnUpdate);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("itemName");
        query.whereEqualTo("user", "testUser");



        // todo update account info if entered
        // todo set RecycleView with user's past orders if any



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserAccountDisplay.this, RestaurantDisplay.class);
                startActivity(i);
            }
        });
    }
}