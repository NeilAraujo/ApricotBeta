package com.example.android.apricotprototype.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Rating {
    private String Userid;
    private String Username;
    private String description;
    private double rating;
    private @ServerTimestamp Date timestamp;

    public Rating(String Userid,String Username,String description,double rating,Date timestamp){
        this.Userid=Userid;
        this.Username=Username;
        this.description=description;
        this.rating=rating;
        this.timestamp=timestamp;
    }

    //getter methods
    public String getUserid() { return Userid; }
    public String getUsername() { return Username; }
    public String getDescription() { return description; }
    public double getRating() { return rating; }
    public Date getTimestamp() { return timestamp; }
    //setter methods
    public void setUserid(String userid) { Userid = userid; }
    public void setUsername(String username) { Username = username; }
    public void setDescription(String description) { this.description = description; }
    public void setRating(double rating) { this.rating = rating; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

}

