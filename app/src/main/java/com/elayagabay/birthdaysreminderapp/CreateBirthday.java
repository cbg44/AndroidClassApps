package com.elayagabay.birthdaysreminderapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class CreateBirthday extends AppCompatActivity {
    Birthday bday = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);
        final EditText mPersonName = findViewById(R.id.dialogPersonName);
        final DatePicker datePicker =  findViewById(R.id.calendarView);
        final Button mBtnDone = findViewById(R.id.btnAddDone);
        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =  mPersonName.getText().toString();
                int   day  = datePicker.getDayOfMonth();
                int   month= datePicker.getMonth();
                int   year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                CharSequence s  = DateFormat.format("yyyy-MM-dd", calendar.getTime());

                if(!mPersonName.getText().toString().isEmpty()) {
                    bday = new Birthday(name,s.toString());

                    //add the record to db:
                    BirthdaysDatabase.getInstance(CreateBirthday.this).writeToBirthdays(bday);

                    //on-screen msg
                    Toast.makeText(CreateBirthday.this, "birthday added.", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(CreateBirthday.this, "please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
            }

       // mBirthayAdapter.notifyDataSetChanged();

    });
    }


}

