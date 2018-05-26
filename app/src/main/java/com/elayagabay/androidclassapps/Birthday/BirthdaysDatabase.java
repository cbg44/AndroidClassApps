package com.elayagabay.androidclassapps.Birthday;

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
                        BirthdaysDatabase.class, "db_bdays.db")
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
            return instance;
        }
    }

    public abstract BirthdayDao getBirthdayDao();

    public void writeToBirthdays(final Birthday bday) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getBirthdayDao().insert(bday);
            }
        }).start();
    }

    public LiveData<List<Birthday>> readBirthdays() {
        LiveData<List<Birthday>> list = getBirthdayDao().loadBirthdays();
        return list;
    }




    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS birthdays (" +
                            "birthday_id INTEGER," +
                            "birth_date VARCHAR(128), " +
                            "person_name VARCHAR(128), " +
                            "PRIMARY KEY(birthday_id))"
            );
        }
    };

}
