package com.elayagabay.birthdaysreminderapp;

public class Task {
    private String mTitle, mId, mDueDate;
    private int mImageId;

    public Task() {
    }

    public Task(String title, String id, String dueDate, int imageUrl) {
        this.mTitle = title;
        this.mId = id;
        this.mDueDate = dueDate;
        this.mImageId = imageUrl;
    }

    public int getImage() {
        return mImageId;
    }

    public void setImage(int imageUrl) {
        this.mImageId = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String name) {
        this.mTitle = name;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String year) {
        this.mDueDate = year;
    }

    public String getId() {
        return mId;
    }

    public void setId(String genre) {
        this.mId = genre;
    }
}
