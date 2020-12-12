package com.example.mobilemerchants.Adapters;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Food")
public class Food extends ParseObject {

    public static final String KEY_NAME = "FoodName";
    public static final String KEY_DESCRIPTION = "FoodDescription";
    public static final String KEY_PRICE = "FoodPrice";
    public static final String KEY_VENDOR = "RestaurantShop";

    public Food() {
        super();
    }


    public String getFoodName() {
        return getString(KEY_NAME);
    }

    public void setFoodName(String name) {
        put(KEY_NAME, name);
    }

    public String getFoodDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setFoodDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public void setFoodPrice(String price){
        put(KEY_PRICE, price);
    }

    public double getFoodPrice(){
        return getDouble(KEY_PRICE);
    }

    public ParseObject getVendor() {
        return getParseObject(KEY_VENDOR);
    }
}