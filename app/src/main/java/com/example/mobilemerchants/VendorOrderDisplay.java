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

import com.example.mobilemerchants.Adapters.VendorOrderDisplayAdapter;
import com.example.mobilemerchants.Adapters.VendorOrders;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class VendorOrderDisplay extends AppCompatActivity {


    public static final String TAG = "VendorOrderDisplay";

    private Toolbar toolbar;
    RecyclerView rvCurrentOrders;
    Button btnBack;
    Button btnSignOut;

    List<VendorOrders> allCurrentOrders; // recheck ....
    VendorOrderDisplayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order_display);

       rvCurrentOrders = findViewById(R.id.rvCurrentOrders);
        btnBack = findViewById(R.id.btnBack);
        btnSignOut = findViewById(R.id.btnSignOut);

        allCurrentOrders = new ArrayList<>();
        adapter = new VendorOrderDisplayAdapter(this, allCurrentOrders);
        rvCurrentOrders.setAdapter(adapter);
        rvCurrentOrders.setLayoutManager(new LinearLayoutManager(this));


        // todo setup RecycleViewer to display current orders
        queryOrders();

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorOrderDisplay.this, UserAccountDisplay.class);
                Log.i(TAG, "Profile in Action bar clicked");
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorOrderDisplay.this, VendorHomeScreen.class);
                startActivity(i);
            }
        });

        // todo make sure user is correctly signed out
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendorOrderDisplay.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private void queryOrders() {
        ParseQuery<VendorOrders> query = ParseQuery.getQuery(VendorOrders.class);
        query.findInBackground(new FindCallback<VendorOrders>() {
            @Override
            public void done(List<VendorOrders> currentOrders, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (VendorOrders order : currentOrders) {
                    Log.i(TAG, "UserName: " + order.getName() + ", FoodItem: " + order.getFoodName() + ", Quant: " + order.getFoodQuant());
                }
                allCurrentOrders.addAll(currentOrders);
                adapter.notifyDataSetChanged();
            }
        });
    }






}