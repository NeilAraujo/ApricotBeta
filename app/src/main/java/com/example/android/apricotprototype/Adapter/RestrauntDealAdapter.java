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

import java.util.List;

public class RestrauntDealAdapter extends RecyclerView.Adapter<RestrauntDealAdapter.RestrauntDealViewHolder> {

    private List<Dealentry> deals;

    public RestrauntDealAdapter(){

    }

    public class RestrauntDealViewHolder extends RecyclerView.ViewHolder{

        private TextView deal;

        public RestrauntDealViewHolder(@NonNull View itemView) {
            super(itemView);
            deal=(TextView)itemView.findViewById(R.id.restraunt_item_deals);
        }
    }

    @NonNull
    @Override
    public RestrauntDealAdapter.RestrauntDealViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_dealsforrestrauntlistitem,viewGroup,false);
        return new RestrauntDealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestrauntDealAdapter.RestrauntDealViewHolder restrauntDealViewHolder, int i) {
            Dealentry deal=deals.get(i);
            String total=""+deal.getMdealpercentage()+"% off on purchase above Rs"+deal.getMtotal();
            restrauntDealViewHolder.deal.setText(total);
    }

    @Override
    public int getItemCount() {
        if(deals==null)
        return 0;
        else
            return deals.size();
    }

    public void setDeals(List<Dealentry> mDealEntry){
        deals=mDealEntry;
    }
}
