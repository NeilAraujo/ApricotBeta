package com.example.android.apricotprototype.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

import com.google.firebase.firestore.ServerTimestamp;

public class Dealentry implements Parcelable{
    private int mdealpercentage;
    private int mtotal;
    private String mTimings;
    private String mName;
    private @ServerTimestamp Date mUpdatetime;

    public Dealentry(String name,int dealpercentage,int total,String Timings,Date updatetime){
        mName=name;
        mdealpercentage=dealpercentage;
        mtotal=total;
        mTimings=Timings;
        mUpdatetime=updatetime;
    }

    public Dealentry(int dealpercentage){
        mdealpercentage=dealpercentage;
    }

    protected Dealentry(Parcel in) {
        mdealpercentage = in.readInt();
        mtotal = in.readInt();
    }

    public static final Creator<Dealentry> CREATOR = new Creator<Dealentry>() {
        @Override
        public Dealentry createFromParcel(Parcel in) {
            return new Dealentry(in);
        }

        @Override
        public Dealentry[] newArray(int size) {
            return new Dealentry[size];
        }
    };

    //getter methods
    public String getmName() { return mName; }
    public int getMdealpercentage() { return mdealpercentage; }
    public int getMtotal() { return mtotal; }
    public String getmTimings() { return mTimings; }
    public Date getmUpdatetime() { return mUpdatetime; }

    //setter methods
    public void setmName(String mName) { this.mName = mName; }
    public void setMdealpercentage(int mdealpercentage) { this.mdealpercentage = mdealpercentage; }
    public void setMtotal(int mtotal) { this.mtotal = mtotal; }
    public void setmTimings(String mTimings) { this.mTimings = mTimings; }
    public void setmUpdatetime(Date mUpdatetime) { this.mUpdatetime = mUpdatetime; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mdealpercentage);
        dest.writeInt(mtotal);
    }
}
