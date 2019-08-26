package com.example.android.apricotprototype.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.model.Rating;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RatingAdapter extends FirestoreAdapter<RatingAdapter.Viewholder> {

    public RatingAdapter(Query query){
        super(query);
    }

    @NonNull
    @Override
    public RatingAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rating,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatingAdapter.Viewholder viewholder, int i) {
        viewholder.bind(getSnapshot(i).toObject(Rating.class));
    }

    static class Viewholder extends RecyclerView.ViewHolder {

        private static final SimpleDateFormat FORMAT=new SimpleDateFormat("DD/MM/YYYY", Locale.US);

        @BindView(R.id.rating_item_name)
        TextView ratingname;

        @BindView(R.id.rating_item_text)
        TextView ratingtext;

        @BindView(R.id.rating_item_rating)
        MaterialRatingBar ratingBar;

        @BindView(R.id.rating_item_date)
        TextView ratingdate;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Rating rating){
            ratingname.setText(rating.getUsername());
            ratingtext.setText(rating.getDescription());
            ratingBar.setRating((float)rating.getRating());
            if(rating.getTimestamp()!=null){
                ratingdate.setText(FORMAT.format(rating.getTimestamp()));
            }
        }
    }
}
