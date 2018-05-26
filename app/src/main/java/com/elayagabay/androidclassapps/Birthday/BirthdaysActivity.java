package com.elayagabay.androidclassapps.Birthday;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Build.VERSION_CODES;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.elayagabay.androidcassapps.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BirthdaysActivity  extends AppCompatActivity {
    private ArrayList<Birthday> mBirthdayEntityList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BirthdaysAdapter mBirthdayAdapter;

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
        btnAddBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirthdaysActivity.this, CreateBirthday.class);
                startActivity(intent);
                mBirthdayAdapter.notifyDataSetChanged();
            }
        });
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


    @RequiresApi(api = VERSION_CODES.N)
    private void addBdayToList(Birthday bday) {
        mBirthdayEntityList.add(bday);
        mBirthdayEntityList.sort(new Comparator<Birthday>() {
            @Override
            public int compare(Birthday o1, Birthday o2) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String date1[] = o1.getDate().split("-");   //date1[0] = year, date1[1] = moth ...etc
                String date2[] = o2.getDate().split("-");   //date1[0] = year, date1[1] = moth ...etc
                String today[] = dateFormat.format(date).split("-");
                int todayTime = ((Integer.parseInt(today[1])-1) * 30 ) + Integer.parseInt(today[2]);
                int date1Time = ((Integer.parseInt(date1[1])-1) * 30 ) + Integer.parseInt(date1[2]);
                int date2Time = ((Integer.parseInt(date2[1])-1) * 30 ) + Integer.parseInt(date2[2]);
                date1Time  = getFixedUnixDateTime(date1Time , todayTime);
                date2Time  = getFixedUnixDateTime(date2Time , todayTime);
                return getClosestDate(date1Time, date2Time);

            }
        });

    }

    private int getClosestDate(int date1UnixTime, int date2UnixTime) {
        if(date1UnixTime < date2UnixTime)
            return  -1;
        else
            return  1;
    }
    private int getFixedUnixDateTime(int dateUnixTime, int todayUnixTime) {
        int res;

        if(dateUnixTime >= todayUnixTime)
            res = dateUnixTime-todayUnixTime;
        else
            res = dateUnixTime+todayUnixTime;
        System.out.println(res);
        return res;
    }

}
