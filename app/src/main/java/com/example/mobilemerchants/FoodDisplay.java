package com.example.mobilemerchants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodDisplay extends AppCompatActivity {

    TextView tvResturantName;
    RecyclerView rvFoodDisplay;
    Button btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);

        tvResturantName = findViewById(R.id.tvResturantName);
        rvFoodDisplay = findViewById(R.id.rvFoodDisplay);
        btnGoBack = findViewById(R.id.btnGoBack);

        // todo set restaurant name




        // todo set RecycleViewer



        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodDisplay.this, RestaurantDisplay.class);
                startActivity(i);
            }
        });
    }
}