package com.elayagabay.birthdaysreminderapp;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> implements View.OnClickListener {
    private ArrayList<Task> mTaskList;

    public TaskAdapter(ArrayList<Task> tasksList) {
        this.mTaskList = tasksList;

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
        Task task = mTaskList.get(position);
        holder.name.setText(task.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }


}
