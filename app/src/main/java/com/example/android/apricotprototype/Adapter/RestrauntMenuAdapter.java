package com.example.android.apricotprototype.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.apricotprototype.R;
import com.google.firebase.firestore.Query;

import java.util.List;

public class RestrauntMenuAdapter extends FirestoreAdapter<RestrauntMenuAdapter.RestrauntMenuViewHolder> {

    public interface RestaurantMenuListener{
        public void onImageClicked();
    }

    public RestrauntMenuAdapter(Query query,RestaurantMenuListener mListener){
        super(query);

    }

    public class RestrauntMenuViewHolder extends RecyclerView.ViewHolder{

        private ImageView images;

        public RestrauntMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            //TODO
            //images=(ImageView)itemView.findViewById(R.id.restraunt_image);
        }
    }
    @NonNull
    @Override
    public RestrauntMenuAdapter.RestrauntMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_dealsformainactivity,viewGroup,false);
        return new RestrauntMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestrauntMenuAdapter.RestrauntMenuViewHolder restrauntMenuViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setPictures(List<Integer> Mmenu){
        notifyDataSetChanged();
    }
}
