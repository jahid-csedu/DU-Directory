package com.example.jahid.dudirectory;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reminder extends AppCompatActivity implements View.OnClickListener {

    EditText date,time;
    Button dateSet,timeSet,setReminder;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int rYear,rMonth,rDay,rHour,rMin;
    private String teacherName;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        teacherName = getIntent().getExtras().getString("name");

        date = (EditText) findViewById(R.id.reminder_date);
        time = (EditText) findViewById(R.id.reminder_time);
        dateSet = (Button) findViewById(R.id.reminder_date_button);
        timeSet = (Button) findViewById(R.id.reminder_time_button);
        setReminder = (Button) findViewById(R.id.set_reminder_button);

        dateSet.setOnClickListener(this);

        timeSet.setOnClickListener(this);

        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!date.getText().equals("") && !time.getText().equals("")) {
                    Calendar reminderTime = new GregorianCalendar(rYear,
                            rMonth,
                            rDay,
                            rHour,
                            rMin);
                    setReminder(reminderTime);
                    if(!date.getText().toString().equals("") && !time.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "তথ্য যোগ হয়েছে", Toast.LENGTH_SHORT).show();
                }
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == dateSet) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            rYear = year;
                            rMonth = monthOfYear;
                            rDay = dayOfMonth;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == timeSet) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                            rHour = hourOfDay;
                            rMin = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void setReminder(Calendar time){

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        alarmIntent.putExtra("name",teacherName);

        SharedPreferences sharedPreferences = this.getSharedPreferences("getId",MODE_PRIVATE);
        id = sharedPreferences.getInt("id",0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, alarmIntent, 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",id+1);
        editor.commit();

        alarmManager.set(AlarmManager.RTC,time.getTimeInMillis(),pendingIntent);

    }
}
