package com.example.foodapp.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class donations implements Serializable {
    public String contact;
    public String desc;
    public Double lat;
    public Double lon;
    public String name;
    public Integer novol;
    public String currentTime;

    public donations(){}
}
