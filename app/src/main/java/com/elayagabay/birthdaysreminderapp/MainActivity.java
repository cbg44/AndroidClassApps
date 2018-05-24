package com.elayagabay.birthdaysreminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Exercise> mExerciseList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ExerciseRecycler mExerciseRecycler;


    public static final String CALCULATOR = "Calculator";
    public static final String BIRTHDAYS = "Birthdays Reminder";
    public static final String ANIMATION = "Animation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mExerciseRecycler = new ExerciseRecycler(mExerciseList);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mExerciseRecycler);


        mExerciseList.add(new Exercise(CALCULATOR));
        mExerciseList.add(new Exercise(BIRTHDAYS));
        mExerciseList.add(new Exercise(ANIMATION));


        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch(mExerciseList.get(position).getTitle()){
                    case CALCULATOR:
                        startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                        break;
                    case BIRTHDAYS:
                        System.out.println("BIRTHDAY");
                        startActivity(new Intent(MainActivity.this, BirthdaysActivity.class));
                        break;
                    case ANIMATION:
                        System.out.println("ANIMATION");
                        startActivity(new Intent(MainActivity.this, Animation.class));
                    default:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) { }
        }));

        mExerciseRecycler.notifyDataSetChanged();
    }
}
