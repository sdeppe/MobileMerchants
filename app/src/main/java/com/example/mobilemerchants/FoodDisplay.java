package com.example.mobilemerchants;


import androidx.annotation.LongDef;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mobilemerchants.Adapters.Food;
import com.example.mobilemerchants.Adapters.FoodItemAdapter;
import com.example.mobilemerchants.Adapters.PreviousOrders;
import com.example.mobilemerchants.Adapters.Restaurant;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

import static com.example.mobilemerchants.Adapters.FoodItemAdapter.*;

public class FoodDisplay extends AppCompatActivity implements FoodItemAdapter.OnFoodListener {

    private Toolbar toolbar;
    TextView tvFoodName;
    TextView tvFoodPrice;

    public static final String TAG = "FoodDisplay";

    RecyclerView rvFoodDisplay;
    Button btnGoBack;

    List<Food> allOrders;
    FoodItemAdapter adapter;

    String restaurantID;
    ParseUser user;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_display);

        Intent intent = getIntent();
        intent.getStringExtra("restaurant");
        Log.d(TAG, "Restaurantid = " + restaurantID);
        restaurantID = "vQPJ2pwAer";
        Log.d(TAG,"Restaurant id:" + restaurantID);

        user = ParseUser.getCurrentUser();
        userId = user.getObjectId();

        tvFoodName = findViewById(R.id.tvUser);
        rvFoodDisplay = findViewById(R.id.rvFoodDisplay);
        tvFoodPrice = findViewById(R.id.tvCost);
        btnGoBack = findViewById(R.id.btnGoBack);

        allOrders = new ArrayList<>();
        // adding interface to constructor. ar
        adapter = new FoodItemAdapter(this, allOrders, this);
        rvFoodDisplay.setAdapter(adapter);
        rvFoodDisplay.setLayoutManager(new LinearLayoutManager(this));
        queryFoods();

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDisplay.this, UserAccountDisplay.class);
                Log.i(TAG, "test");
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
    private void queryFoods() {
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
    // navigation to new activity
    @Override
    public void onFoodClick(int position) {
        Log.i(TAG,"OnClick");
        Food food = allOrders.get(position);
        alertMaker(food);
    }

    public void createOrder(String userID, Double foodPrice, String foodID, String restaurantID) {
        PreviousOrders previousOrders = new PreviousOrders();
        previousOrders.put("User",userID);
        previousOrders.put("OrderedItems",1);
        previousOrders.put("OrderTotal",foodPrice);
        previousOrders.put("Approved", false);
        previousOrders.put("OrderedItem",foodID);
        previousOrders.put("Restaurant",restaurantID);

        // Other fields can be set just like any other ParseObject,
        // using the "put" method, like this: user.put("attribute", "its value");
        // If this field does not exists, it will be automatically created
        previousOrders.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(FoodDisplay.this,"Success!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(FoodDisplay.this, RestaurantDisplay.class);
                    startActivity(i);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
//                    Log.e(TAG,"Error", e);
//                    Toast.makeText(FoodDisplay.this,"Error!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void alertMaker(final Food food) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(FoodDisplay.this);

        alertBuilder.setMessage("Would you like to purchase this " + food.getFoodName() + "?");

        alertBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                        Log.i(TAG, " You selected 'Yes' to purchase the food: " + food.getFoodName());
                        createOrder(userId,food.getFoodPrice(),food.getObjectId(),restaurantID);
                        Toast.makeText(FoodDisplay.this, food.getFoodName() + " was confirmed.", Toast.LENGTH_SHORT).show();
                    }
                });

        alertBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, " You selected 'No' to confirm the food: " + food.getFoodName());
                        Toast.makeText(FoodDisplay.this, food.getFoodName() + " was not purchased.", Toast.LENGTH_SHORT).show();
                    }
                });

        alertBuilder.setCancelable(true);

        alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.i(TAG, " You cancelled the Alert Dialog");
                Toast.makeText(FoodDisplay.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        alertBuilder.create().show();
    }
}
