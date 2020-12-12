package com.example.mobilemerchants.Adapters;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("PreviousOrders")
public class PreviousOrders extends ParseObject {

    public static final String KEY_USER = "User";
    public static final String KEY_RESTAURANT = "Restaurant";
    public static final String KEY_ORDER = "OrderedItem";
    public static final String KEY_TOTAL = "OrderTotal";
    public static final String KEY_APPROVED = "Approved";

    public ParseObject getUser() {
        return getParseObject(KEY_USER);
    }

    public ParseObject getRestaurant() {
        return getParseObject(KEY_RESTAURANT);
    }

    public ParseObject getOrder() {
        return getParseObject(KEY_ORDER);
    }

    public double getTotal() {
        return getDouble(KEY_TOTAL);
    }

    public Boolean getApproved() {
        return getBoolean(KEY_APPROVED);
    }

    public void setConfirmed(Boolean confirmation){put(KEY_APPROVED, confirmation); }


}
