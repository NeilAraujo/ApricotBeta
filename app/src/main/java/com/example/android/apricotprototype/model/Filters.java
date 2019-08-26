package com.example.android.apricotprototype.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.android.apricotprototype.R;
import com.google.firebase.firestore.Query;

public class Filters  {
    private String category=null;
    private String sortBy=null;
    private Query.Direction direction=null;

    public Filters(){}

    public static Filters getDefault(){
        Filters filters=new Filters();
        filters.setSortBy(restrauntentry.FIELD_AVG_RATING);
        filters.setDirection(Query.Direction.DESCENDING);
        return filters;
    }

    public boolean hasCategory(){ return !(TextUtils.isEmpty(category));}

    public boolean hassortBy(){return !(TextUtils.isEmpty(sortBy));}

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getSortBy() { return sortBy; }

    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public Query.Direction getDirection(){return direction;}

    public void setDirection(Query.Direction direction){this.direction=direction;}

    public String getSearchDescription(Context context){
        StringBuilder builder=new StringBuilder();
        if(category==null){
            builder.append("<b>");
            builder.append("All Restaurants");
            builder.append("<b>");
        }
        else {
            builder.append("<b>");
            builder.append(category);
            builder.append("<b>");
        }
        return  builder.toString();
    }

    public String getOrderDescription(Context context){
        if(restrauntentry.FIELD_AVG_RATING.equals(sortBy)){
            return "Sort by Rating";
        }else
            return "Sort by Popularity";
    }
}
