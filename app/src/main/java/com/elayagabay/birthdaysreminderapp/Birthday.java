package com.elayagabay.birthdaysreminderapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;



@Entity(tableName = QueryHolder.TABLE_NAME_BDS)

public class Birthday {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo (name="name")
    private String mName;

    @ColumnInfo (name="birthday")
    private String mDate;



    public Birthday(String name, String date) {
        setName(name);
        setDate(date);
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "id=" + id +
                ", mDate='" + mDate + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

}
