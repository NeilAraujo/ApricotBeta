package com.example.android.apricotprototype.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.android.apricotprototype.model.Filters;

public class MainViewModel extends ViewModel {
    private boolean isSigningIn;
    private Filters filters;

    public MainViewModel(){
        isSigningIn=false;
        filters=Filters.getDefault();
    }

    public Filters getFilters() { return filters; }

    public void setFilters(Filters filters) { this.filters = filters; }

    public boolean getisSignIn(){return isSigningIn;}

    public void setSigningIn(boolean signingIn) { isSigningIn = signingIn; }
}
