package com.example.mobilemerchants;

import com.example.mobilemerchants.Adapters.Food;
import com.example.mobilemerchants.Adapters.PreviousOrders;
import com.example.mobilemerchants.Adapters.Restaurant;
import com.example.mobilemerchants.Adapters.UserAccount;
import com.parse.Parse;
import android.app.Application;
import com.parse.ParseObject;

public class ParseApplication extends Application{
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(PreviousOrders.class);
        ParseObject.registerSubclass(Restaurant.class);
        ParseObject.registerSubclass(Food.class);
        ParseObject.registerSubclass(UserAccount.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("z3ROWYCFJBRCZMbcyd5L8WqBxcOJIofRuKqCuHJO")
                .clientKey("FUxWhtG5tqqAZGsUFaM3gtMA6urPFMcvDe57wQpH")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
