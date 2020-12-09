package com.example.mobilemerchants;

import com.example.mobilemerchants.Adapters.Restaurant;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Food")
public class Food extends ParseObject {

    public static final String KEY_NAME = "FoodName";
    public static final String KEY_DESCRIPTION = "FoodDescription";
    public static final String KEY_PRICE = "FoodPrice";
    public static final String KEY_RESTAURANT = "RestaurantShop";

    public Food(){
        super();
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public Number getPrice() {
        return getNumber(KEY_PRICE);
    }

    public ParseObject getRestaurant() {
        return getParseObject(KEY_RESTAURANT);
    }


}
