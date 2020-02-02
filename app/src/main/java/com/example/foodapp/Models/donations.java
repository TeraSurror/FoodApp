package com.example.foodapp.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class donations implements Serializable,Comparable {
    public String id;
    public String userid;
    public String contact;
    public String desc;
    public Double lat;
    public Double lon;
    public String name;
    public Integer novol;
    public String currentTime;
    public Double distance;

    public donations(String id,String userid,String contact, String desc, Double lat, Double lon, String name, Integer novol, String currentTime) {
        this.id = id;
        this.userid =userid;
        this.contact = contact;
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.novol = novol;
        this.currentTime = currentTime;
    }

    public donations(){

    }

    @Override
    public int compareTo(Object o) {
        Double compareDist = ((donations)o).distance;
        return (int)(this.distance - compareDist);
    }

}
