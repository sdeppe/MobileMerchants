package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobilemerchants.Adapters.Restaurant;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class VendorHomeScreen extends AppCompatActivity {

    public static final String TAG = "VendorHomeScreen";

    private TextView tvVendorUsername;
    private TextView tvVendorRestaurantName;
    private TextView tvVendorRestaurantDescription;
    private TextView tvConfirmed;
    private RecyclerView rvVendorPendingDisplay;
    private Button btnCreateRestaurant;
    private Button btnVendorLogout;
    Restaurant currentRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_screen);

        tvVendorUsername = findViewById(R.id.tvVendorUsername);
        tvVendorRestaurantName = findViewById(R.id.tvVendorRestaurantName);
        tvVendorRestaurantDescription = findViewById(R.id.tvVendorRestaurantDescription);
        tvConfirmed = findViewById(R.id.tvConfirmed);
        rvVendorPendingDisplay = findViewById(R.id.rvVendorPendingDisplay);
        btnCreateRestaurant = findViewById(R.id.btnCreateRestaurant);
        btnVendorLogout = findViewById(R.id.btnVendorLogout);

        tvVendorUsername.setText(ParseUser.getCurrentUser().getUsername());

        if (currentRestaurant != null) {
            tvVendorRestaurantName.setText(currentRestaurant.getName());
            tvVendorRestaurantDescription.setText(currentRestaurant.getDescription());
            if (currentRestaurant.getConfirmed()) {
                tvConfirmed.setText("Confirmed: True");
            } else {
                tvConfirmed.setText("Confirmed: Pending");
            }
            btnCreateRestaurant.setText("Manage Items");
            btnCreateRestaurant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(VendorHomeScreen.this, VendorRestaurantDisplay.class);
                    startActivity(i);
                }
            });
        }
        else {
            btnCreateRestaurant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(VendorHomeScreen.this, VendorRestaurantApply.class);
                    startActivity(i);
                }
            });
        }
        btnVendorLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i = new Intent(VendorHomeScreen.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    // todo fix query
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
                    if(restaurant.getOwner() == ParseUser.getCurrentUser() && restaurant.getConfirmed()){
                        currentRestaurant = restaurant;
                    }
                }
                //adapter.notifyDataSetChanged();
            }
        });
    }
}