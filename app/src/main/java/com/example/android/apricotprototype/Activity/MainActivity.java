package com.example.android.apricotprototype.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.android.apricotprototype.Adapter.Maindealadapter;
import com.example.android.apricotprototype.Adapter.RestaurantAdapter;
import com.example.android.apricotprototype.R;
import com.example.android.apricotprototype.data.masterdataclass;
import com.example.android.apricotprototype.model.Filters;
import com.example.android.apricotprototype.model.Image;
import com.example.android.apricotprototype.model.Rating;
import com.example.android.apricotprototype.model.restrauntentry;
import com.example.android.apricotprototype.viewmodel.MainViewModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.WriteBatch;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.opencensus.tags.Tag;

public class MainActivity extends AppCompatActivity implements RestaurantAdapter.onRestaurantSelectedListener{

    private static String LOG_TAG=MainActivity.class.getSimpleName();

    private static final int RC_SIGN_IN=4001;

    @BindView(R.id.hotdealsrecyler)
    RecyclerView HotDealsRecyclerView;


    private FirebaseFirestore mfirestore;
    private Query mquery;

    @BindView(R.id.restaurant_recycler)
   RecyclerView RestaurantRecyclerView;

    private RestaurantAdapter mAdapter;
    private masterdataclass mcuisinedata;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseFirestore.setLoggingEnabled(true);
        mcuisinedata=new masterdataclass();
        mfirestore=FirebaseFirestore.getInstance();

        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        mquery=mfirestore.collection("Restaurant").orderBy("avgRating",Query.Direction.DESCENDING);

        mAdapter=new RestaurantAdapter(mquery,this){
            @Override
            protected void onDatachanged() {
                super.onDatachanged();
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                super.onError(e);
                Snackbar.make(findViewById(android.R.id.content),
                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
            }
        };
       /** HotDealsRecyclerView=(RecyclerView)findViewById(R.id.hotdeals);
        LinearLayoutManager dealslayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        HotDealsRecyclerView.setLayoutManager(dealslayoutManager);
        HotDealsRecyclerView.setHasFixedSize(true);
        mDealsAdapter=new Maindealadapter();
        mDealsAdapter.setRestraunts(mcuisinedata.getRestrauntentries());
        HotDealsRecyclerView.setAdapter(mDealsAdapter);
        **/
       RestaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       RestaurantRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(shouldStartSignIn()){
            startSignIn();
            return;
        }

        if(mAdapter!=null){
            mAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAdapter!=null) {
            mAdapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            IdpResponse response=IdpResponse.fromResultIntent(data);
            viewModel.setSigningIn(false);

            if(response==null){
                //user pressed back
            }
            else if(response.getError()!=null && response.getError().getErrorCode()== ErrorCodes.NO_NETWORK){
                showSignInErrorDialog(R.string.nonetwork);
            }


        }
    }




    private boolean shouldStartSignIn(){
        return (!(viewModel.getisSignIn())&& FirebaseAuth.getInstance().getCurrentUser()==null);
    }

    private void startSignIn(){
        Intent intent=AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.PhoneBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build();

        startActivityForResult(intent,RC_SIGN_IN);
        viewModel.setSigningIn(true);
    }

    private void showSignInErrorDialog(@StringRes int message){
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle(R.string.errorinsignin)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startSignIn();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();

        alertDialog.show();
    }

    @Override
    public void onRestaurantSelected(DocumentSnapshot restaurant) {
        Intent intent=new Intent(this,RestrauntActivity.class);
        intent.putExtra("Restaurant_ID",restaurant.getId());
        startActivity(intent);
    }

    public void onDataSync() {
        WriteBatch batch = mfirestore.batch();

        DocumentReference DocRef = mfirestore.collection("Restaurant").document();
        restrauntentry restaurant = mcuisinedata.getRestrauntentries();
        batch.set(DocRef,restaurant);
        Rating rating=mcuisinedata.getRating();
        Image photos=mcuisinedata.getPhoto();
        batch.set(DocRef.collection("ratings").document(),rating);
        batch.set(DocRef.collection("Menu").document(),photos);
        batch.set(DocRef.collection("images").document(),photos);
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d(LOG_TAG, "report successful");
                }else {
                    Log.d(LOG_TAG,"oops an error occured");
                }
            }
        });
    }
}
