package com.example.mobilemerchants;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobilemerchants.Adapters.Restaurant;
import com.example.mobilemerchants.Adapters.RestaurantsAdapter;
import com.example.mobilemerchants.UserAccountDisplay;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class AdminConfirmActivity extends AppCompatActivity {

    public static final String TAG = "AdminConfirmActivity";

    private Toolbar toolbar;
    Button btnEditProfile;
    RecyclerView rvRestaurants;
    List<Restaurant> allRestaurants;
    RestaurantsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_confirm);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurants");

        btnEditProfile = findViewById(R.id.btnEditProfile);
        rvRestaurants = findViewById(R.id.rvRestaurantItems);

//        RestaurantsAdapter.OnClickListener onClickListener = new RestaurantsAdapter.OnClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                Restaurant restaurant = allRestaurants.get(position);
//                Log.d(TAG, restaurant.getName() + restaurant.getConfirmed());
//                alertMaker(restaurant);
//            }
//        };

        RestaurantsAdapter.OnClickListener onClickListener = new RestaurantsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Restaurant restaurant = allRestaurants.get(position);
                Log.d(TAG, restaurant.getName() + restaurant.getConfirmed());
                alertMaker(restaurant);
            }
        };

        allRestaurants = new ArrayList<>();
        adapter = new RestaurantsAdapter(this, allRestaurants, onClickListener);
        rvRestaurants.setAdapter(adapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        queryRestaurants();

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminConfirmActivity.this, UserAccountDisplay.class);
                Log.i(TAG, "Profile in Action bar clicked");
                startActivity(intent);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminConfirmActivity.this, UserAccountDisplay.class);
                startActivity(i);
            }
        });
    }

    private void alertMaker(final Restaurant restaurant) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AdminConfirmActivity.this);

        alertBuilder.setMessage("Would you like to confirm this restaurant? (This will make it visible to customers)");

        alertBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            restaurant.setConfirmed(true);
                            restaurant.saveInBackground();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                        Log.i(TAG, " You selected 'Yes' to confirm the restaurant: " + restaurant.getName());
                        Toast.makeText(AdminConfirmActivity.this, restaurant.getName() + " was confirmed.", Toast.LENGTH_SHORT).show();
                    }
                });

        alertBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, " You selected 'No' to confirm the restaurant: " + restaurant.getName());
                        Toast.makeText(AdminConfirmActivity.this, restaurant.getName() + " was not confirmed.", Toast.LENGTH_SHORT).show();
                    }
                });

        alertBuilder.setCancelable(true);

        alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.i(TAG, " You cancelled the Alert Dialog");
                Toast.makeText(AdminConfirmActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        alertBuilder.create().show();
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
                    if(!restaurant.getConfirmed()){
                        allRestaurants.add(restaurant);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}