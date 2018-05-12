package com.elayagabay.birthdaysreminderapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import java.util.List;

public class BirthdaysActivity  extends AppCompatActivity {
    private ArrayList<Birthday> mBirthdayEntityList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BirthdaysAdapter mBirthdayAdapter;
    public static Birthday bday = null;
    public static final String DRAWABLE_PATH = "../../res/drawable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdays_list);
        FloatingActionButton btnAddBday = findViewById(R.id.btnAddBirthday);

        mBirthdayAdapter = new BirthdaysAdapter(mBirthdayEntityList);

        mRecyclerView = findViewById(R.id.bday_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBirthdayAdapter);

        //add some pre made birthdays for a better LAF (Look And Feel):
        //addPremadeBdays(mBirthdayEntityList);

        //displayStoragedEntities();

        // Create the observer which updates the UI.
        final Observer<List<Birthday>> bdayObserver = new Observer<List<Birthday>>() {
            @Override
            public void onChanged(@Nullable List<Birthday> birthdayEntities) {
                if(birthdayEntities.isEmpty()){
                    Toast.makeText(BirthdaysActivity.this, R.string.nobody_has_birthday, Toast.LENGTH_LONG).show();
                }
                for(Birthday bday : birthdayEntities){
                    if(!isBdayAlreadyDisplayed(bday)){
                        addBdayToList(bday);
                        mBirthdayAdapter.notifyDataSetChanged();
                    }
                }
            }
        };

        LiveData<List<Birthday>> captainsLogEntityLiveData = BirthdaysDatabase.getInstance(this).readBirthdays();

        captainsLogEntityLiveData.observe(this, bdayObserver);

        btnAddBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        Intent intent = new Intent(BirthdaysActivity.this, CreateBirthday.class);
                        startActivity(intent);



            }
        });

        //tell rv about the change:
        mBirthdayAdapter.notifyDataSetChanged();
    }

    private int randAvatar() {
        return R.drawable.avatar13;
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
    }


    String fixDateFormat(String day, String month, String year){
        StringBuilder fixedDate = new StringBuilder(day);
        fixedDate.append("/");
        fixedDate.append(month);
        fixedDate.append("/");
        fixedDate.append(year);
        return fixedDate.toString();
    }
}
