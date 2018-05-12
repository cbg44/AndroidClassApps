package com.elayagabay.birthdaysreminderapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

public class CreateBirthday extends AppCompatActivity {
    Birthday bday = null;
    private BirthdaysAdapter mBirthayAdapter;
   String dateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);

      final EditText mPersonName = findViewById(R.id.dialogPersonName);
        final CalendarView mDateDay = findViewById(R.id.calendarView);
        final Button mBtnDone = findViewById(R.id.btnAddDone);
        final Button btnaddbd  =  findViewById(R.id.btnAddBirthday);

        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo: sort the list
                /*if(!mPersonName.getText().toString().isEmpty() &&
                        !mDateDay.getDate()*/

                    String name =  mPersonName.getText().toString();
                long l = mDateDay.getDate();
                dateString= android.text.format.DateFormat.format("MM/dd/yyyy", new Date(l)).toString();
                mDateDay.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@android.support.annotation.NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        dateString = dayOfMonth+"."+month+"."+year;
                    }
                });

                if(!mPersonName.getText().toString().isEmpty()) {
                    bday = new Birthday(name, dateString, randAvatar());

                    //add the record to db:
                    BirthdaysDatabase.getInstance(CreateBirthday.this).writeToBirthdays(bday);

                    //on-screen msg
                    Toast.makeText(CreateBirthday.this, R.string.success, Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(CreateBirthday.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                }
            }

       // mBirthayAdapter.notifyDataSetChanged();

    });
    }
    String fixDateFormat(String day, String month, String year){
        StringBuilder fixedDate = new StringBuilder(day);
        fixedDate.append("/");
        fixedDate.append(month);
        fixedDate.append("/");
        fixedDate.append(year);
        return fixedDate.toString();
    }

    private int randAvatar() {
        return R.drawable.avatar13;

    }

}

