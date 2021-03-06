package com.example.mobilemerchants;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.mobilemerchants.Adapters.AccountDisplayAdapter;
import com.example.mobilemerchants.Adapters.PreviousOrders;
import com.example.mobilemerchants.Adapters.PreviousOrdersAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UserAccountDisplay extends AppCompatActivity  {

    public static final String TAG = "UserAccountDisplay";

    private Toolbar toolbar;


    List<PreviousOrders> allorders;

    //AccountDisplayAdapter adapter;

    PreviousOrdersAdapter adapter1;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("itemName");



    TextView tvUserFirstName;
    TextView tvUserLastName;
    TextView tvUserUsername;

    RecyclerView rvPastOrders;
    // add ar
    EditText etUpdateRole;
    Button btnConfirm;
    Button btnLogout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_account_display);

        tvUserFirstName = findViewById(R.id.etUserFirstName);
        tvUserLastName = findViewById(R.id.etUserLastName);
        tvUserUsername = findViewById(R.id.etUserUsername);

        rvPastOrders = findViewById(R.id.rvCurrentOrders);

        etUpdateRole = findViewById(R.id.etRole);


        btnConfirm = findViewById(R.id.btnConfirm);
        btnLogout = findViewById(R.id.btnLogout);


       // info = new ArrayList<PreviousOrders>();
        allorders = new ArrayList<>();

       // adapter = new AccountDisplayAdapter(this, info);

        adapter1 = new PreviousOrdersAdapter(this,allorders);

        rvPastOrders.setAdapter(adapter1);

        rvPastOrders.setLayoutManager(new LinearLayoutManager(this));
        queryUserInfo();

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);



//        ParseQuery<ParseObject> query = ParseQuery.getQuery("itemName");
//        query.whereEqualTo("user", "testUser");
        //  toolbar.setNavigationOnClickListener(new View.OnClickListener() {





        // todo set RecycleView with user's past orders if any



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserAccountDisplay.this, RestaurantDisplay.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Logout button clicked.");
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Intent i = new Intent(UserAccountDisplay.this, LoginActivity.class);
                startActivity(i);
                Toast.makeText(UserAccountDisplay.this, "Signed out", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void  queryUserInfo() {
        ParseQuery<PreviousOrders> query = ParseQuery.getQuery(PreviousOrders.class);
        query.findInBackground(new FindCallback<PreviousOrders>() {
            @Override
            public void done(List<PreviousOrders> orders, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (PreviousOrders user : orders) {
                    Log.i(TAG, "Your order: " + user.getOrder() + ", Restaurant Name: " + user.getRestaurant() + ", Total: " + user.getTotal() );
                }
                allorders.addAll(orders);
                adapter1.notifyDataSetChanged();
            }
        });
    }
}