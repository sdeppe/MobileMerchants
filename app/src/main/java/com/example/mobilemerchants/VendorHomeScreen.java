package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilemerchants.Adapters.Restaurant;
import com.example.mobilemerchants.Adapters.RestaurantsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class VendorHomeScreen extends AppCompatActivity {

    public static final String TAG = "VendorHomeScreen";

    TextView tvVendorName;
    RecyclerView rvVendorFoodDisplay;
    Button btnAddFood;
    Button btnOrders;
    List<Restaurant> vendorRestaurants;
    RestaurantsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_screen);

        tvVendorName = findViewById(R.id.tvVendorName);
        rvVendorFoodDisplay = findViewById(R.id.rvVendorFoodDisplay);
        btnAddFood = findViewById(R.id.btnAddRestaurant);
        btnOrders = findViewById(R.id.btnVendorLogout);

        // todo set tvVendorName
        tvVendorName.setText(ParseUser.getCurrentUser().getUsername());
        // todo set RecycleViewer
        // todo set click listener/redirect
        vendorRestaurants = new ArrayList<>();
        adapter = new RestaurantsAdapter(this, vendorRestaurants);
        rvVendorFoodDisplay.setAdapter(adapter);
        rvVendorFoodDisplay.setLayoutManager(new LinearLayoutManager(this));
        queryRestaurants();

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorHomeScreen.this, VendorAddFood.class);
                startActivity(i);
                // todo change to start for result
            }
        });

        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorHomeScreen.this, VendorOrderDisplay.class);
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

                    if(restaurant.getOwner() == ParseUser.getCurrentUser() && restaurant.getConfirmed()){
                        vendorRestaurants.add(restaurant);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}