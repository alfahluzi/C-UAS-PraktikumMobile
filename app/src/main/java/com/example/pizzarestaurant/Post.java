package com.example.pizzarestaurant;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int id;
    private String price;
    private String number;
    private String details;
    private String foodName;


    public int getId() {
        return id;
    }

    public String getPrice(){
        return price;
    }

    public String getNumber(){
        return number;
    }

    public String getDetails() {
        return details;
    }

    public String getFoodName() {
        return foodName;
    }
}
