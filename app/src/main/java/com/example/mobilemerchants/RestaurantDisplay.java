package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RestaurantDisplay extends AppCompatActivity {

    public static final String TAG = "RestaurantDisplay";

    private Toolbar toolbar;
    Button btnEditProfile;
    RecyclerView rvResturants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_display);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        rvResturants = findViewById(R.id.rvResturants);

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantDisplay.this, UserAccountDisplay.class);
                Log.i(TAG, "Profile in Action bar clicked");
                startActivity(intent);
            }
        });

        // todo setup RecycleView

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RestaurantDisplay.this, UserAccountDisplay.class);
                startActivity(i);
            }
        });
    }
}