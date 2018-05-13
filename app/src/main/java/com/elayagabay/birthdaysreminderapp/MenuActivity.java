package com.elayagabay.birthdaysreminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Task> mTaskList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;


    public static final String CALCULATOR = "Calculator";
    public static final String BIRTHDAYS = "Birthdays Reminder";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mTaskAdapter = new TaskAdapter(mTaskList);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mTaskAdapter);


        mTaskList.add(new Task(CALCULATOR));
        mTaskList.add(new Task(BIRTHDAYS));


        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch(mTaskList.get(position).getTitle()){
                    case CALCULATOR:
                        startActivity(new Intent(MenuActivity.this, CalculatorActivity.class));
                        break;
                    case BIRTHDAYS:
                        System.out.println("BIRTHDAY");
                        startActivity(new Intent(MenuActivity.this, BirthdaysActivity.class));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mTaskAdapter.notifyDataSetChanged();
    }
}
