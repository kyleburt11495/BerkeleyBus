package com.example.bus;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.bus.data.DatabaseContract;
import com.example.bus.data.DatabaseHelper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class ClassScheduleActivity extends AppCompatActivity  {

    static final int CREATE_CLASS_REQUEST = 1;

    private Toolbar toolbar;

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    //priority queues to sort classes by starting time. String is concatenation of hour and minute
    //separated by :
    //14:30 is 2:30 PM
    TreeSet<String> mondayPriority;
    TreeSet<String> tuesdayPriority;
    TreeSet<String> wednesdayPriority;
    TreeSet<String> thursdayPriority;
    TreeSet<String> fridayPriority;

    //arrays hold a column of buttons
    Button mondayColumn[];
    Button tuesdayColumn[];
    Button wednesdayColumn[];
    Button thursdayColumn[];
    Button fridayColumn[];

    //Buttons that represent student's classes
    Button mondayFirstClass;
    Button mondaySecondClass;
    Button mondayThirdClass;
    Button mondayFourthClass;
    Button tuesdayFirstClass;
    Button tuesdaySecondClass;
    Button tuesdayThirdClass;
    Button tuesdayFourthClass;
    Button wednesdayFirstClass;
    Button wednesdaySecondClass;
    Button wednesdayThirdClass;
    Button wednesdayFourthClass;
    Button thursdayFirstClass;
    Button thursdaySecondClass;
    Button thursdayThirdClass;
    Button thursdayFourthClass;
    Button fridayFirstClass;
    Button fridaySecondClass;
    Button fridayThirdClass;
    Button fridayFourthClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //instantiate database if first time run
        dbHelper = new DatabaseHelper(this);

        //keep reference to database
        db = dbHelper.getWritableDatabase();

        mondayFirstClass = (Button) findViewById(R.id.mondayFirstClass);
        mondaySecondClass = (Button) findViewById(R.id.mondaySecondClass);
        mondayThirdClass = (Button) findViewById(R.id.mondayThirdClass);
        mondayFourthClass = (Button) findViewById(R.id.mondayFourthClass);
        tuesdayFirstClass = (Button) findViewById(R.id.tuesdayFirstClass);
        tuesdaySecondClass = (Button) findViewById(R.id.tuesdaySecondClass);
        tuesdayThirdClass = (Button) findViewById(R.id.tuesdayThirdClass);
        tuesdayFourthClass = (Button) findViewById(R.id.tuesdayFourthClass);
        wednesdayFirstClass = (Button) findViewById(R.id.wednesdayFirstClass);
        wednesdaySecondClass = (Button) findViewById(R.id.wednesdaySecondClass);
        wednesdayThirdClass = (Button) findViewById(R.id.wednesdayThirdClass);
        wednesdayFourthClass = (Button) findViewById(R.id.wednesdayFourthClass);
        thursdayFirstClass = (Button) findViewById(R.id.thursdayFirstClass);
        thursdaySecondClass = (Button) findViewById(R.id.thursdaySecondClass);
        thursdayThirdClass = (Button) findViewById(R.id.thursdayThirdClass);
        thursdayFourthClass = (Button) findViewById(R.id.thursdayFourthClass);
        fridayFirstClass = (Button) findViewById(R.id.fridayFirstClass);
        fridaySecondClass = (Button) findViewById(R.id.fridaySecondClass);
        fridayThirdClass = (Button) findViewById(R.id.fridayThirdClass);
        fridayFourthClass = (Button) findViewById(R.id.fridayFourthClass);

        mondayPriority = new TreeSet<>();
        tuesdayPriority = new TreeSet<>();
        wednesdayPriority = new TreeSet<>();
        thursdayPriority = new TreeSet<>();
        fridayPriority = new TreeSet<>();

        mondayColumn = new Button[4];
        tuesdayColumn = new Button[4];
        wednesdayColumn = new Button[4];
        thursdayColumn = new Button[4];
        fridayColumn = new Button[4];

        //populate arrays representing column in layout
        mondayColumn[0] = mondayFirstClass;
        mondayColumn[1] = mondaySecondClass;
        mondayColumn[2] = mondayThirdClass;
        mondayColumn[3] = mondayFourthClass;

        tuesdayColumn[0] = tuesdayFirstClass;
        tuesdayColumn[1] = tuesdaySecondClass;
        tuesdayColumn[2] = tuesdayThirdClass;
        tuesdayColumn[3] = tuesdayFourthClass;

        wednesdayColumn[0] = wednesdayFirstClass;
        wednesdayColumn[1] = wednesdaySecondClass;
        wednesdayColumn[2] = wednesdayThirdClass;
        wednesdayColumn[3] = wednesdayFourthClass;

        thursdayColumn[0] = thursdayFirstClass;
        thursdayColumn[1] = thursdaySecondClass;
        thursdayColumn[2] = thursdayThirdClass;
        thursdayColumn[3] = thursdayFourthClass;

        fridayColumn[0] = fridayFirstClass;
        fridayColumn[1] = fridaySecondClass;
        fridayColumn[2] = fridayThirdClass;
        fridayColumn[3] = fridayFourthClass;

        //load times from database into priority queues

        new getDatabaseTask().execute();




    }

    public class getDatabaseTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {
            db = dbHelper.getWritableDatabase();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            //get values from database and populate the layout

            //define projection used to specify which columns will be used
            String[] projectionMonday = {DatabaseContract.DatabaseEntry.COLUMN_MONDAY};
            String[] projectionTuesday = {DatabaseContract.DatabaseEntry.COLUMN_TUESDAY};
            String[] projectionWednesday = {DatabaseContract.DatabaseEntry.COLUMN_WEDNESDAY};
            String[] projectionThursday = {DatabaseContract.DatabaseEntry.COLUMN_THURSDAY};
            String[] projectionFriday = {DatabaseContract.DatabaseEntry.COLUMN_FRIDAY};



            //Declare cursor for each day's table
            Cursor cursorMonday = db.query(DatabaseContract.DatabaseEntry.MONDAY_TABLE_NAME,
                    projectionMonday,
                    null,
                    null,
                    null,
                    null,
                    null);

            Cursor cursorTuesday = db.query(DatabaseContract.DatabaseEntry.TUESDAY_TABLE_NAME,
                    projectionTuesday,
                    null,
                    null,
                    null,
                    null,
                    null);

            Cursor cursorWednesday = db.query(DatabaseContract.DatabaseEntry.WEDNESDAY_TABLE_NAME,
                    projectionWednesday,
                    null,
                    null,
                    null,
                    null,
                    null);

            Cursor cursorThursday = db.query(DatabaseContract.DatabaseEntry.THURSDAY_TABLE_NAME,
                    projectionThursday,
                    null,
                    null,
                    null,
                    null,
                    null);

            Cursor cursorFriday = db.query(DatabaseContract.DatabaseEntry.FRIDAY_TABLE_NAME,
                    projectionFriday,
                    null,
                    null, null,
                    null,
                    null);

            //insert monday times into priority queue
            while(cursorMonday.moveToNext())
            {
                String current = cursorMonday.getString
                        (cursorMonday.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_MONDAY));

                mondayPriority.add(current);
            }

            while(cursorTuesday.moveToNext())
            {
                String current = cursorTuesday.getString
                        (cursorTuesday.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_TUESDAY));

                tuesdayPriority.add(current);
            }

            while(cursorWednesday.moveToNext())
            {
                String current = cursorWednesday.getString
                        (cursorWednesday.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_WEDNESDAY));

                wednesdayPriority.add(current);
            }

            while(cursorThursday.moveToNext())
            {
                String current = cursorThursday.getString
                        (cursorThursday.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_THURSDAY));

                thursdayPriority.add(current);
            }

            while(cursorFriday.moveToNext())
            {
                String current = cursorFriday.getString
                        (cursorFriday.getColumnIndexOrThrow(DatabaseContract.DatabaseEntry.COLUMN_FRIDAY));

                fridayPriority.add(current);
            }

            //close all cursors
            cursorMonday.close();
            cursorTuesday.close();
            cursorWednesday.close();
            cursorThursday.close();
            cursorFriday.close();

            //display times
            displayInLayout(mondayPriority);
            displayInLayout(tuesdayPriority);
            displayInLayout(wednesdayPriority);
            displayInLayout(thursdayPriority);
            displayInLayout(fridayPriority);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_add_class:
                Intent intent = new Intent(this, EnterTime.class);
                startActivityForResult(intent, CREATE_CLASS_REQUEST );
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Check which request is being responeded to
        if(requestCode == CREATE_CLASS_REQUEST)
        {
            //check if result code was RESULT_OK
            if (resultCode == RESULT_OK)
            {
                if (data.hasExtra("cancel"))
                {
                    //do nothing
                    return;
                }
                else
                {
                    //get the returned data from the child activity EnterTime
                    String daySelected = data.getStringExtra("day");
                    int hourSelected = data.getIntExtra("hour", -1);
                    int minuteSelected = data.getIntExtra("minute", -1);
                    String amPmSelected = data.getStringExtra("am_pm");


                    Log.d("Hour", String.valueOf(hourSelected));
                    Log.d("minute", String.valueOf(minuteSelected));

                    addClassToSchedule(daySelected, hourSelected, minuteSelected, amPmSelected);
                }
            }
        }

    }

    public void addClassToSchedule(String daySelected, int hourSelected, int minuteSelected, String
            amPmSelected)
    {
        TreeSet<String> priorityQueue;
        //determine which priority queue the class will be placed in based on day selected
        switch(daySelected)
        {
            case "Monday":
                priorityQueue = mondayPriority;
                break;
            case "Tuesday":
                priorityQueue = tuesdayPriority;
                break;
            case "Wednesday":
                priorityQueue = wednesdayPriority;
                break;
            case "Thursday":
                priorityQueue = thursdayPriority;
                break;
            case "Friday":
                priorityQueue = fridayPriority;
                break;
            default:
                priorityQueue = mondayPriority;
        }
        // not converted yet to AM/PM format
        String timeUnformatted = String.valueOf(hourSelected)+":"+String.valueOf(minuteSelected);

        //add the unformatted time to the priority queue
        priorityQueue.add(timeUnformatted);

        //add the time to the database
        addToDatabase(priorityQueue, timeUnformatted);

        //pass priority queue to displayInLayout to update column and display new class
        displayInLayout(priorityQueue);


    }

    public void displayInLayout(TreeSet<String> priorityQueue)
    {
        if(priorityQueue == mondayPriority)
        {
            updateColumn(priorityQueue, mondayColumn);
        }
        else if(priorityQueue == tuesdayPriority)
        {
            updateColumn(priorityQueue, tuesdayColumn);
        }

        else if(priorityQueue == wednesdayPriority)
        {
            updateColumn(priorityQueue, wednesdayColumn);
        }
        else if(priorityQueue == thursdayPriority)
        {
            updateColumn(priorityQueue, thursdayColumn);
        }
        else if(priorityQueue == fridayPriority)
        {
            updateColumn(priorityQueue, fridayColumn);
        }
    }

    public void updateColumn(TreeSet<String> priorityQueue, Button[] column)
    {
        Iterator<String> iterator = priorityQueue.iterator();
        int buttonCounter = 0;
        while(iterator.hasNext())
        {
            String currentTime = iterator.next();

            //convert currentTime into HH:MM format
            currentTime = convertTime(currentTime);

            Button currentButton = column[buttonCounter];

            Log.d("buttonCounter:", Integer.toString(buttonCounter));
            Log.d("CurrentTime", currentTime);
            currentButton.setText(currentTime);
            buttonCounter++;
        }
    }

    public String convertTime(String time)
    {
        //split time into hour and minute
        String[] splitArray = time.split(":");
        String hour = splitArray[0];
        String minute = splitArray[1];
        //set default to PM, will be set to AM if hour is covered by default in switch statement
        String amPm = "PM";

        //convert hour into correct format
        switch(Integer.valueOf(hour))
        {
            case 13:
                hour = "1";
                break;
            case 14:
                hour = "2";
                break;
            case 15:
                hour = "3";
                break;
            case 16:
                hour = "4";
                break;
            case 17:
                hour = "5";
                break;
            case 18:
                hour = "6";
                break;
            case 19:
                hour = "7";
                break;
            case 29:
                hour = "8";
                break;
            case 21:
                hour = "9";
                break;
            case 22:
                hour = "10";
                break;
            case 23:
                hour = "11";
                break;
            case 24:
                hour = "12";
                break;
            default:
                amPm = "AM";
                break;
        }

        //convert minutes into correct format by adding 0 to front if needed
        if(minute.length() == 1)
        {
            minute = "0" + minute;
        }

        //concat hour and minute together to create final time string
        return hour + ":" + minute + "\n" + amPm;
    }

    public String unconvertTime(String toUnconvert)
    {
        //split string on newLine char to determine whether time is AM or PM
        String[] newlineSplit = toUnconvert.split("\n");

        //split string to left of newline to get hour and minute
        String[] colonSplit = newlineSplit[0].split(":");

        //convert hour value into an int
        int hourInt = Integer.valueOf(colonSplit[0]);

        //check if first digit of minute is a 0, if so then remove 0
        String minute = colonSplit[1];
        if(minute.charAt(0) == '0')
            minute = minute.substring(1);


        // amPm will be initialized to 0 or 12 depending on if time is AM or PM and added to hour
        int amPmInt = 0;
        if(newlineSplit[1].equals("PM"))
            amPmInt = 12;

        //add amPmInt to hour to unconvert back to 24 hour time
        String hourString = String.valueOf(hourInt+amPmInt);

        //concatenate hour with : and mnute and
        return hourString + ":" + minute;


    }

    public long addToDatabase(TreeSet<String> priorityQueue, String time)
    {
        ContentValues contentValues = new ContentValues();

        //insert time into the correct column
        if(priorityQueue == mondayPriority)
        {
            contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_MONDAY, time);
            return db.insert(DatabaseContract.DatabaseEntry.MONDAY_TABLE_NAME, null, contentValues);

        }
        else if(priorityQueue == tuesdayPriority)
        {
            contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_TUESDAY, time);
            return db.insert(DatabaseContract.DatabaseEntry.TUESDAY_TABLE_NAME, null, contentValues);

        }
        else if(priorityQueue == wednesdayPriority)
        {
            contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_WEDNESDAY, time);
            return db.insert(DatabaseContract.DatabaseEntry.WEDNESDAY_TABLE_NAME, null, contentValues);

        }
        else if(priorityQueue == thursdayPriority)
        {
            contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_THURSDAY, time);
            return db.insert(DatabaseContract.DatabaseEntry.THURSDAY_TABLE_NAME, null, contentValues);

        }
        else
        {
            contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_FRIDAY, time);
            return db.insert(DatabaseContract.DatabaseEntry.FRIDAY_TABLE_NAME, null, contentValues);

        }




    }

    public class timeButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            //get time stored in the button
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            //convert time back into the format it is stored in database
            String databaseString = unconvertTime(buttonText);

            if(Arrays.asList(mondayColumn).contains(button))
            {

            }
        }
    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }




}
