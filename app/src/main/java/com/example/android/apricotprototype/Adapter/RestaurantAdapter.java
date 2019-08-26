package com.example.android.apricotprototype.Adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.model.restrauntentry;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RestaurantAdapter extends FirestoreAdapter<RestaurantAdapter.ViewHolder>{



    public interface onRestaurantSelectedListener{
        void onRestaurantSelected(DocumentSnapshot restaurant);
    }

    private onRestaurantSelectedListener mListener;

    public RestaurantAdapter(Query query,onRestaurantSelectedListener Listener){
        super(query);
        mListener=Listener;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(inflater.inflate(R.layout.activity_categorylistitem,viewGroup,false));
    }

    @Override
            public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder viewHolder, int i) {
                viewHolder.bind(getSnapshot(i),mListener);
            }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_restraunt_name)
        TextView name;

       @BindView(R.id.list_restraunt_image)
        ImageView image;

        @BindView(R.id.list_restraunt_cuisines)
        TextView cuisines;

        @BindView(R.id.list_restaurant_item_rating)
        MaterialRatingBar ratingBar;

        @BindView(R.id.list_restraunt_ratings_num)
        TextView ratingsnum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final DocumentSnapshot snapshot,final onRestaurantSelectedListener listener){
            restrauntentry restaurant=snapshot.toObject(restrauntentry.class);
            Resources resources=itemView.getResources();

            //TODO set up glide
            Glide.with(image.getContext()).load(restaurant.getMphotos()).into(image);
            name.setText(restaurant.getMrestrauntname());
            cuisines.setText(restaurant.getMcuisines());
            ratingBar.setRating((float)restaurant.getAvgRating());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onRestaurantSelected(snapshot);
                    }
                }
            });
        }

    }

}
