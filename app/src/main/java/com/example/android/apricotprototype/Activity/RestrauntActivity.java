package com.example.android.apricotprototype.Activity;

import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.apricotprototype.Adapter.RestrauntMenuAdapter;
import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.Adapter.RestrauntDealAdapter;
import com.example.android.apricotprototype.Adapter.RestrauntPicturesAdapter;
import com.example.android.apricotprototype.model.restrauntentry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RestrauntActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {

    @BindView(R.id.restaurant_image)
    ImageView image;
    @BindView(R.id.restaurant_name)
    TextView name;
    @BindView(R.id.restaurant_rating)
    MaterialRatingBar rating;
    @BindView(R.id.restaurant_category)
    TextView cuisines;
    @BindView(R.id.restraunt_item_number)
    TextView number;
    @BindView(R.id.restraunt_item_address)
    TextView address;
    @BindView(R.id.textView6)
    TextView menutitle;

    @BindView(R.id.restraunt_item_photos)
    RecyclerView imagerecyclerview;

    private RestrauntPicturesAdapter imageadapter;

    private Query imagequery;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restraunt);

        ButterKnife.bind(this);

        mFirestore=FirebaseFirestore.getInstance();

        String restaurantId=getIntent().getExtras().getString("Restaurant_ID");
        DocumentReference DocRef=mFirestore.collection("Restaurant").document(restaurantId);
        DocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot=task.getResult();
                onRestaurantSelected(documentSnapshot.toObject(restrauntentry.class));
            }
        });
        imagequery=DocRef.collection("images").orderBy("ImageId", Query.Direction.ASCENDING).limit(10);

        imageadapter=new RestrauntPicturesAdapter(imagequery){
            @Override
            protected void onDatachanged() {
                super.onDatachanged();
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                super.onError(e);
            }
        };
       ;
        imagerecyclerview.setLayoutManager(new LinearLayoutManager(this));
        imagerecyclerview.setAdapter(imageadapter);

        //TODO
        //dealAdapter.setDeals(restrauntentry.getMdealentries());
        //dealsrecylerview.setAdapter(dealAdapter);
        //TODO
        //List<Integer> menu=restrauntentry.getmMenu();
    /**
        menurecyclerview=(RecyclerView)findViewById(R.id.restraunt_item_menu);
        if (menu.size()!=0) {

            LinearLayoutManager menuLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            menurecyclerview.setLayoutManager(menuLayoutManager);
            menurecyclerview.setHasFixedSize(true);
            menuAdapter=new RestrauntMenuAdapter();
            menuAdapter.setPictures(restrauntentry.getmMenu());
            menurecyclerview.setAdapter(menuAdapter);
        }
        else
        {
            menutitle.setVisibility(View.GONE);
            menurecyclerview.setVisibility(View.GONE);
        }
        **/

    }


    private void onRestaurantSelected(restrauntentry restaurant){
        Glide.with(image.getContext()).load(restaurant.getMphotos()).into(image);
        name.setText(restaurant.getMrestrauntname());
        cuisines.setText(restaurant.getMcuisines());
        rating.setRating(restaurant.getNumRatings());
        number.setText(restaurant.getMphnumber());
        address.setText(restaurant.getMrestrauntname());
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        onRestaurantSelected(snapshot.toObject(restrauntentry.class));
    }
}
