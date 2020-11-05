package com.example.mobilemerchants;

import com.parse.Parse;
import com.parse.ParseObject;
import android.app.Application;

public class ParseApplication extends Application{
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("JgmH06Ve07EoafgYjGVUQxhvujnbY9aXx7ksHntR")
                .clientKey("ERu4Mpfxqa91coM6hnzhj043ibGwqRA73jF1qUzp")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
