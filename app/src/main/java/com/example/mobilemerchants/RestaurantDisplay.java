package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mobilemerchants.Adapters.Restaurant;
import com.example.mobilemerchants.Adapters.RestaurantsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDisplay extends AppCompatActivity {

    public static final String TAG = "RestaurantDisplay";

    private Toolbar toolbar;
    Button btnEditProfile;
    RecyclerView rvRestaurants;
    List<Restaurant> allRestaurants;
    RestaurantsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_display);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        rvRestaurants = findViewById(R.id.rvRestaurants);

        allRestaurants = new ArrayList<>();
        adapter = new RestaurantsAdapter(this, allRestaurants);
        rvRestaurants.setAdapter(adapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        queryRestaurants();

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

    private void queryRestaurants() {
        ParseQuery<Restaurant> query = ParseQuery.getQuery(Restaurant.class);
        query.findInBackground(new FindCallback<Restaurant>() {
            @Override
            public void done(List<Restaurant> restaurants, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (Restaurant restaurant : restaurants) {
                    Log.i(TAG, "Restaurant: " + restaurant.getName() + ", Description: " + restaurant.getDescription());
                }
                allRestaurants.addAll(restaurants);
                adapter.notifyDataSetChanged();
            }
        });
    }
}