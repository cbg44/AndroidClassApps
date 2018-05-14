package com.elayagabay.birthdaysreminderapp;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class ExerciseRecycler extends RecyclerView.Adapter<ExerciseRecycler.MyViewHolder> implements View.OnClickListener {
    private ArrayList<Exercise> mExerciseList;

    public ExerciseRecycler(ArrayList<Exercise> tasksList) {
        this.mExerciseList = tasksList;

    }

    @Override
    public void onClick(View view) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;


        public MyViewHolder(ViewGroup view) {
            super(view);
            name = view.findViewById(R.id.title);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup itemView =(ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_task, parent, false);
        itemView.setOnClickListener(this);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Exercise exercise = mExerciseList.get(position);
        holder.name.setText(exercise.getTitle());
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }


}
