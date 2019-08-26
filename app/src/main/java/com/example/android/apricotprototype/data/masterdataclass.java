package com.example.android.apricotprototype.data;


import android.support.annotation.NonNull;

import com.example.android.apricotprototype.model.Dealentry;
import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.model.Image;
import com.example.android.apricotprototype.model.Rating;
import com.example.android.apricotprototype.model.restrauntentry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class masterdataclass {


    public masterdataclass(){
        }



    public restrauntentry getRestrauntentries() {
        return new restrauntentry(null,"Lassi shop","Beverage",4,4.3,null,null,null ,"30909239","Vellore Tamil Nadu");
    }

    public Rating getRating(){
        return new Rating(null,"Random user","Sugoi",4.3,null);
    }
    public Dealentry getdealentry(){
        return new Dealentry("KFC",15,100,"12:00-2:00",null);
    }

    public Image getPhoto(){
        return new Image("www.google.com",1234);
    }


}
