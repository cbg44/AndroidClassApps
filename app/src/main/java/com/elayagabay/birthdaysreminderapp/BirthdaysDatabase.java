package com.elayagabay.birthdaysreminderapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import java.util.List;

@Database(entities = {Birthday.class}, version=2)
public abstract class BirthdaysDatabase extends RoomDatabase{

    private static BirthdaysDatabase instance;

    public static BirthdaysDatabase getInstance(Context context) {
        synchronized (BirthdaysDatabase.class) {
            if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        BirthdaysDatabase.class, QueryHolder.DB_NAME)
                        .addMigrations(MIGRATION_1_2) // placeholder for future db versions
                        .build();
            }
            return instance;
        }
    }

    public abstract BirthdayDao getBirthdayDao();

    public LiveData<List<Birthday>> readBirthdays() {
        LiveData<List<Birthday>> list = getBirthdayDao().loadBirthdays();
        return list;
    }

    public void writeToBirthdays(final Birthday bday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getBirthdayDao().insert(bday);
            }
        }).start();
    }

    //use full queries instead of constants representing them (due to android dev. docs for migration)
    //see here: https://developer.android.com/training/data-storage/room/migrating-db-versions
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS birthdays (" +
                            "birthday_id INTEGER," +
                            "birth_date VARCHAR(128), " +
                            "person_name VARCHAR(128), " +
                            "image_id   INTEGER, " +
                            "PRIMARY KEY(birthday_id))"
            );
        }
    };

}
