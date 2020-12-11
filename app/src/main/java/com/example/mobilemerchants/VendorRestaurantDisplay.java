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

import com.example.mobilemerchants.Adapters.Food;
import com.example.mobilemerchants.Adapters.FoodItemAdapter;
import com.example.mobilemerchants.Adapters.PreviousOrders;
import com.example.mobilemerchants.Adapters.PreviousOrdersAdapter;
import com.example.mobilemerchants.Adapters.Restaurant;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class VendorRestaurantDisplay extends AppCompatActivity {
    public static final String TAG = "VendorRestaurantDisplay";

    //todo display items
    private TextView tvRestaurantName;
    private RecyclerView rvRestaurantItems;
    private Button btnVendorBack;
    private Button btnVendorAddItem;

    protected List<Food> allFood;
    FoodItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_restaurant_display);

        final String restaurantId = getIntent().getStringExtra("restaurant");

        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        rvRestaurantItems = findViewById(R.id.rvRestaurantItems);
        btnVendorBack = findViewById(R.id.btnVendorBack);
        btnVendorAddItem = findViewById(R.id.btnVendorAddItem);

        allFood = new ArrayList<>();
        adapter = new FoodItemAdapter(this, allFood);
        rvRestaurantItems.setAdapter(adapter);
        rvRestaurantItems.setLayoutManager(new LinearLayoutManager(this));
        queryFood();

        btnVendorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorRestaurantDisplay.this, VendorHomeScreen.class);
                startActivity(i);
            }
        });

        btnVendorAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorRestaurantDisplay.this, VendorAddFood.class);
                i.putExtra("restaurant", restaurantId);
                startActivity(i);
            }
        });
    }

    private void queryFood() {
        ParseQuery<Food> query = ParseQuery.getQuery(Food.class);
        query.findInBackground(new FindCallback<Food>() {
            @Override
            public void done(List<Food> foods, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (Food food : foods) {
                    if (food.getVendor().getObjectId().equals("vQPJ2pwAer")) {
                        Log.i(TAG, food.getFoodName() + " -> " + food.getFoodDescription());
                        allFood.add(food);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}