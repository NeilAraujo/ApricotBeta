package com.example.android.apricotprototype.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.model.Image;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestrauntPicturesAdapter extends FirestoreAdapter<RestrauntPicturesAdapter.RestrauntPictureViewHolder>{



    public RestrauntPicturesAdapter(Query query){
        super(query);
    }

    public class RestrauntPictureViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.restaurant_image_object)
        ImageView restaurantImageObject;

        public RestrauntPictureViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void onBind(Image image){
            String imageUrl=image.getImageUrl();
            Glide.with(restaurantImageObject.getContext()).load(imageUrl).into(restaurantImageObject);
        }
    }
    @NonNull
    @Override
    public RestrauntPicturesAdapter.RestrauntPictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RestrauntPictureViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestrauntPicturesAdapter.RestrauntPictureViewHolder restrauntPictureViewHolder, int i) {
        restrauntPictureViewHolder.onBind(getSnapshot(i).toObject(Image.class));
    }
}
