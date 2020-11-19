package com.example.mobilemerchants;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.mobilemerchants.Adapters.AccountDisplayAdapter;
import com.example.mobilemerchants.Adapters.UserAccount;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class UserAccountDisplay extends AppCompatActivity  {

    public static final String TAG = "RestaurantDisplay";
   // ParseQuery<ParseObject> query = ParseQuery.getQuery("itemName");
    private Toolbar toolbar;

    RecyclerView rvRestaurants;
    List<UserAccount> allOrders;
    AccountDisplayAdapter adapter;
    EditText etFirstNameUpdate;
    EditText etUpdateLastName;
    EditText etUpdateUsername;
    EditText etUpdatePassword;
    RecyclerView rvPastOrders;
    Button btnUpdate;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_display);

        etFirstNameUpdate = findViewById(R.id.etFirstNameUpdate);
        etUpdateLastName = findViewById(R.id.etUpdateLastName);
        etUpdateUsername = findViewById(R.id.etUpdateUsername);
        etUpdatePassword = findViewById(R.id.etUpdatePassword);
        rvPastOrders = findViewById(R.id.rvPastOrders);
        btnUpdate = findViewById(R.id.btnUpdate);

        allOrders = new ArrayList<>();
        adapter = new AccountDisplayAdapter(this, allOrders);
        rvRestaurants.setAdapter(adapter);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        queryRestaurants();

        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("itemName");
//        query.whereEqualTo("user", "testUser");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccountDisplay.this, UserAccountDisplay.class);
                Log.i(TAG, "Profile in Action bar clicked");
                startActivity(intent);
            }
        });




        // todo set RecycleView with user's past orders if any



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserAccountDisplay.this, RestaurantDisplay.class);
                startActivity(i);
            }
        });
    }



    private void queryRestaurants() {
        ParseQuery<UserAccount> query = ParseQuery.getQuery(UserAccount.class);
        query.findInBackground(new FindCallback<UserAccount>() {
            @Override
            public void done(List<UserAccount> orders, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (UserAccount user : orders) {
                    Log.i(TAG, "User: " + user.getUsername() + ", First Name: " + user.getUserFirstName() + ", Last Name: " + user.getUserLastName() + ", Password:" + user.getPassword());
                }
                allOrders.addAll(orders);
                adapter.notifyDataSetChanged();
            }
        });
    }
}