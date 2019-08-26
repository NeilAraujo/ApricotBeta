package com.example.android.apricotprototype.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.apricotprototype.model.Dealentry;
import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.model.restrauntentry;

import java.util.List;

public class Maindealadapter extends RecyclerView.Adapter<Maindealadapter.MaindealViewHolder> {

    private List<restrauntentry> Restraunts;

    public Maindealadapter() {
    }

    public class MaindealViewHolder extends RecyclerView.ViewHolder{

        private TextView HotDeals;

        public MaindealViewHolder(@NonNull View itemView) {
            super(itemView);
            HotDeals=(TextView)itemView.findViewById(R.id.hotdealstext);
        }
    }
    @NonNull
    @Override
    public Maindealadapter.MaindealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_dealsformainactivity,viewGroup,false);
        return new MaindealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Maindealadapter.MaindealViewHolder maindealVeiwHolder, int i) {
        final restrauntentry restraunt=Restraunts.get(i);
        List<Dealentry> dealentries=restraunt.getMdealentries();
        for(int j=0;j<dealentries.size();j++){
            Dealentry deal=dealentries.get(j);
            String perc=""+deal.getMdealpercentage();
            String rest=restraunt.getMrestrauntname();
            String dealentry=perc+"% off at "+rest;
            maindealVeiwHolder.HotDeals.setText(dealentry);
        }
    }

    @Override
    public int getItemCount() {
        if(Restraunts==null) {
            return 0;
        }
        else {
            return Restraunts.size();
        }
    }

    public void setRestraunts(List<restrauntentry> mRestraunts){
        Restraunts=mRestraunts;
    }
}
