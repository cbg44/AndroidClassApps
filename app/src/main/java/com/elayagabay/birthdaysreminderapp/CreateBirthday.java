package com.elayagabay.birthdaysreminderapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateBirthday extends AppCompatActivity {
    Birthday bday = null;
    Boolean flagToCheckIsFirst = true;

    private BirthdaysAdapter mBirthayAdapter;
   String dateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);

      final EditText mPersonName = findViewById(R.id.dialogPersonName);
        final DatePicker datePicker =  findViewById(R.id.calendarView);
        final Button mBtnDone = findViewById(R.id.btnAddDone);
        final Button btnaddbd  =  findViewById(R.id.btnAddBirthday);



        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo: sort the list
                /*if(!mPersonName.getText().toString().isEmpty() &&
                        !mDateDay.getDate()*/

                    String name =  mPersonName.getText().toString();
                int   day  = datePicker.getDayOfMonth();
                int   month= datePicker.getMonth();
                int   year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
             /*   Date _bday = new Date(datePicker.getYear()-1900,datePicker.getMonth(),datePicker.getDayOfMonth());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");*/

                CharSequence s  = DateFormat.format("yyyy-MM-dd", calendar.getTime());

                //convert it to string array



                if(!mPersonName.getText().toString().isEmpty()) {
                    bday = new Birthday(name,s.toString());

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



}

