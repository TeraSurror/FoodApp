package com.example.foodapp.Models;

import com.example.foodapp.helpingClasses.statics;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String number;
    public String email;
    public String token;


    public User(){

    }

    public User(String name, String email, Double latitude, Double longitude, Double altitude, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
        if (statics.token != null)
        {
            this.token = statics.token;
        }
    }




}

