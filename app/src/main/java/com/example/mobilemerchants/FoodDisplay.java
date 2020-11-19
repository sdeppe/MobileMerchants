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
import android.widget.Toolbar;


import com.example.mobilemerchants.Adapters.Food;
import com.example.mobilemerchants.Adapters.FoodItemAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FoodDisplay extends AppCompatActivity {

    public static final String TAG = "RestaurantDisplay";

    private Toolbar toolbar;
    TextView tvFoodName;
    TextView tvFoodPrice;
    RecyclerView rvFoodDisplay;
    Button btnGoBack;

    List<Food> allOrders;
    FoodItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);

        tvFoodName = findViewById(R.id.tvFoodName);
        rvFoodDisplay = findViewById(R.id.rvFoodDisplay);
        tvFoodPrice = findViewById(R.id.tvFoodPrice);
        btnGoBack = findViewById(R.id.btnGoBack);

        allOrders = new ArrayList<>();
        adapter = new FoodItemAdapter(this, allOrders);
        rvFoodDisplay.setAdapter(adapter);
        rvFoodDisplay.setLayoutManager(new LinearLayoutManager(this));
        queryRestaurants();

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDisplay.this, UserAccountDisplay.class);
                Log.i(TAG, "Profile in Action bar clicked");
                startActivity(intent);
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodDisplay.this, RestaurantDisplay.class);
                startActivity(i);
            }
        });
    }
    private void queryRestaurants() {
        ParseQuery<Food> query = ParseQuery.getQuery(Food.class);
        query.findInBackground(new FindCallback<Food>() {
            @Override
            public void done(List<Food> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (Food food : items) {
                    Log.i(TAG, "food: " + food.getFoodName() + ", Description: " + food.getFoodDescription() + ", Price: " + food.getFoodPrice());
                }
                allOrders.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
