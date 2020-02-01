package com.example.foodapp.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class HungerSpots implements Serializable {
    public String contact;
    public Double lat;
    public Double lon;
    public String name;
    public Double radius;

    public  HungerSpots(){

    }


    public HungerSpots(String name,String contact,Double lat,Double lon,Double radius) {
        this.name = name;
        this.contact =contact;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;

    }

}
