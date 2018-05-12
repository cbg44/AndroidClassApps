package com.elayagabay.birthdaysreminderapp;

public class QueryHolder {
    public static final String DB_NAME = "db_bdays.db";
    public static final String TABLE_NAME_BDS = "birthdays";
    public static final String BD_ENT_ID_FIELD_NAME = "birthday_id";
    public static final String BD_ENT_NAME_FIELD_NAME = "person_name";
    public static final String BD_ENT_DATE_FIELD_NAME = "birth_date";
    public static final String BD_ENT_IMG_ID_FIELD_NAME = "image_id";


    public static final String QUERY_GET_ALL_BDAYS = "SELECT * FROM " + TABLE_NAME_BDS + " ;";

    public static final String QUERY_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS birthdays (" +
                    "birthday_id INTEGER," +
                    "birth_date VARCHAR(128), " +
                    "person_name VARCHAR(128), " +
                    "image_id   INTEGER, " +
                    "PRIMARY KEY(birthday_id))";
}
