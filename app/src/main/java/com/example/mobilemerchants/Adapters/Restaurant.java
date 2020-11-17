package com.example.mobilemerchants.Adapters;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Restaurants")
public class Restaurant extends ParseObject {

    public static final String KEY_NAME = "RestaurantName";
    public static final String KEY_DESCRIPTION = "RestaurantDescription";
    // public static final String KEY_CONFIRMED = "Confirmed";

    public Restaurant() {
        super();
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setRestaurantName(String name) {
        put(KEY_NAME, name);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setRestaurantDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }
}
