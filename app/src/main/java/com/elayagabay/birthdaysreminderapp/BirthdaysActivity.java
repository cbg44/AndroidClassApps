package com.elayagabay.birthdaysreminderapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build.VERSION_CODES;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BirthdaysActivity  extends AppCompatActivity {
    private ArrayList<Birthday> mBirthdayEntityList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BirthdaysAdapter mBirthdayAdapter;
    LiveData<List<Birthday>> captainsLogEntityLiveData;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdays_list);
        FloatingActionButton btnAddBday = findViewById(R.id.btnAddBirthday);


        mRecyclerView = findViewById(R.id.bday_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mBirthdayAdapter = new BirthdaysAdapter(mBirthdayEntityList);
        mRecyclerView.setAdapter(mBirthdayAdapter);
        mBirthdayAdapter.notifyDataSetChanged();
        final Observer<List<Birthday>> bdayObserver = new Observer<List<Birthday>>() {
            @RequiresApi(api = VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable List<Birthday> birthdayEntities) {


                if(birthdayEntities.isEmpty()){
                    Toast.makeText(BirthdaysActivity.this, "list is currently empty.", Toast.LENGTH_LONG).show();
                }

                for(Birthday bday : birthdayEntities){
                    if(!isBdayAlreadyDisplayed(bday)){

                        addBdayToList(bday);
                        mBirthdayAdapter.notifyDataSetChanged();
                    }
                }
            }
        };

        LiveData<List<Birthday>> BdayLiveData = BirthdaysDatabase.getInstance(BirthdaysActivity.this).readBirthdays();
        BdayLiveData.observe(this, bdayObserver);

        //captainsLogEntityLiveData.observe(BirthdaysActivity.this, bdayObserver);
        mBirthdayAdapter.notifyDataSetChanged();

        btnAddBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirthdaysActivity.this, CreateBirthday.class);
                startActivity(intent);
                LiveData<List<Birthday>> BdayLiveData = BirthdaysDatabase.getInstance(BirthdaysActivity.this).readBirthdays();

                BdayLiveData.observe(BirthdaysActivity.this, bdayObserver);

                mBirthdayAdapter.notifyDataSetChanged();

            }
        });
        BdayLiveData.observe(this, bdayObserver);
        mBirthdayAdapter.notifyDataSetChanged();
    }



    private boolean isBdayAlreadyDisplayed(Birthday bday) {
        for(Birthday bde : mBirthdayEntityList){
            if(bde.getId() == bday.getId()){
                return true;
            }
        }
        return false;
    }



    private void addBdayToList(Birthday bday) {
        mBirthdayEntityList.add(bday);
      //  Collections.sort(mBirthdayEntityList, new UnixSort());

    }

}
