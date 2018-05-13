package com.elayagabay.birthdaysreminderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BirthdaysAdapter extends RecyclerView.Adapter<BirthdaysAdapter.MyViewHolder> implements View.OnClickListener {

    private ArrayList<Birthday> mBirthdayEntityList;

    public BirthdaysAdapter(ArrayList<Birthday> birthdayEntityList) {
        this.mBirthdayEntityList = birthdayEntityList;

    }

    @Override
    public void onClick(View view) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView personName, birthdate;

        public MyViewHolder(ViewGroup view) {
            super(view);
            personName = view.findViewById(R.id.personName);
            birthdate = view.findViewById(R.id.birthdate);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup itemView =(ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_birthday_row, parent, false);
        itemView.setOnClickListener(this);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Birthday bday = mBirthdayEntityList.get(position);
        holder.personName.setText(bday.getName());
        holder.birthdate.setText(bday.getDate());
    }

    @Override
    public int getItemCount() {
        return mBirthdayEntityList.size();
    }
}
