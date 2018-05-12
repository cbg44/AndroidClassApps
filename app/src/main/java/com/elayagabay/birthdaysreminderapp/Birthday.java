package com.elayagabay.birthdaysreminderapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;



@Entity(tableName = QueryHolder.TABLE_NAME_BDS)

public class Birthday {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = QueryHolder.BD_ENT_ID_FIELD_NAME)
    private int id;

    @ColumnInfo (name = QueryHolder.BD_ENT_NAME_FIELD_NAME)
    private String mName;

    @ColumnInfo (name = QueryHolder.BD_ENT_DATE_FIELD_NAME)
    private String mDate;

    @ColumnInfo (name = QueryHolder.BD_ENT_IMG_ID_FIELD_NAME)
    public int mImageId;


    public Birthday(String name, String date, int imageId) {
        setName(name);
        setDate(date);
        setmImageId(imageId);
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "id=" + id +
                ", mDate='" + mDate + '\'' +
                ", mName='" + mName + '\'' +
                ", mImageId=" + mImageId +
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

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }
}
