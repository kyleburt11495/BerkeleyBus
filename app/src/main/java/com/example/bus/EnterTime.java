package com.example.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;

public class EnterTime extends AppCompatActivity {
    Spinner daySpinner;
    Button cancelButton;
    Button selectButton;
    TimePicker timePicker;

    //variables returned to parent activity to
    String selectedDay;
    int hourSelected;
    int minuteSelected;
    String amPm;

    //string keys for intent extras, returned to parent activity
    static final String CANCEL_EXTRA = "cancel";
    static final String DAY_EXTRA = "day";
    static final String HOUR_EXTRA = "hour";
    static final String MINUTE_EXTRA = "minute";
    static final String AM_PM_EXTRA = "am_pm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_time);

        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        selectButton = (Button) findViewById(R.id.select_button);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        //initialize selectedDay, hourSelected, and minuteSelected
        selectedDay = null;
        hourSelected = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minuteSelected = Calendar.getInstance().get(Calendar.MINUTE);

        //set onItemSelectedListener for spinner
        daySpinner.setOnItemSelectedListener(new daySpinnerListener());
        //create ArrayAdapter for daySpinner
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.daysOfWeek, android.R.layout.simple_spinner_item);
        //set the drop down view resource of arrayAdapter
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set arrayAdapter as adapter for daySpinner
        daySpinner.setAdapter(arrayAdapter);

        //set the default time and am/pm style for the spinner
        timePicker.setIs24HourView(false);
        timePicker.setHour(7);
        timePicker.setOnTimeChangedListener(new timePickerListener());

        cancelButton.setOnClickListener(new cancelButtonListener());
        selectButton.setOnClickListener((new selectButtonListener()));

    }

    public class cancelButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Intent data = new Intent();
            data.putExtra(CANCEL_EXTRA, "true");
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public class selectButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Intent data = new Intent();
            data.putExtra(DAY_EXTRA, selectedDay);
            data.putExtra(HOUR_EXTRA, hourSelected);
            data.putExtra(MINUTE_EXTRA, minuteSelected);
            data.putExtra(AM_PM_EXTRA, amPm);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public class daySpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            //get the day selected from spinner
            selectedDay = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            //do nothing
        }
    }

    public class timePickerListener implements TimePicker.OnTimeChangedListener
    {

        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
        {

            hourSelected = hourOfDay;
            minuteSelected = minute;


            if(hourSelected < 12)
                amPm = "AM";
            else
                amPm = "PM";
        }


    }
}
