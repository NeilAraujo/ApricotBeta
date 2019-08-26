package com.example.android.apricotprototype.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class restrauntentry{

    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_POPULARITY = "numRatings";
    public static final String FIELD_AVG_RATING = "avgRating";

    private String mrestrauntname;
    private String mcuisines;
    private List<String> mMenu;
    private List<Dealentry> mdealentries;
    private String mphotos;
    private String mphnumber;
    private String maddress;
    private int numRatings;
    private double avgRating;

    public restrauntentry(){}

    public  restrauntentry(String Url, String restrauntname, String cuisines,int numRatings, double rating, List<String> Menu, List<Dealentry> dealentries, String photos, String phnumber, String address){
        mrestrauntname=restrauntname;
        mcuisines=cuisines;
        this.numRatings=numRatings;
        avgRating=rating;
        mMenu=Menu;
        mdealentries=dealentries;
        mphotos=photos;
        mphnumber=phnumber;
        maddress=address;
    }


    //Getter methods
    public String getMrestrauntname() {
        return mrestrauntname;
    }
    public String getMcuisines() {
        return mcuisines;
    }
    public double getAvgRating() { return avgRating; }
    public int getNumRatings() { return numRatings; }
    public List<String> getmMenu() {
        return mMenu;
    }
    public List<Dealentry> getMdealentries() {
        return mdealentries;
    }
    public String getMphotos() {
        return mphotos;
    }
    public String getMphnumber() {
        return mphnumber;
    }
    public String getMaddress() {
        return maddress;
    }

    //Setter methods
    public void setMaddress(String maddress) { this.maddress = maddress; }
    public void setMcuisines(String mcuisines) { this.mcuisines = mcuisines; }
    public void setAvgRating(double avgRating) { this.avgRating = avgRating; }
    public void setNumRatings(int numRatings) { this.numRatings = numRatings; }
    public void setmMenu(List<String> mMenu) { this.mMenu = mMenu; }
    public void setMdealentries(List<Dealentry> mdealentries) { this.mdealentries = mdealentries; }
    public void setMphotos(String mphotos) { this.mphotos = mphotos; }
    public void setMphnumber(String mphnumber) { this.mphnumber = mphnumber; }
    public void setMrestrauntname(String mrestrauntname) { this.mrestrauntname = mrestrauntname; }




}
