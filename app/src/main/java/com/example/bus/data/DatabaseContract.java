package com.example.bus.data;

import android.provider.BaseColumns;

/**
 * Created by Kyle on 9/29/2017.
 */

public class DatabaseContract
{
    //prevent accidental instantiation
    private DatabaseContract() {}

    //inner class that defines table constants
    public static final class DatabaseEntry implements BaseColumns
    {
        //table constants
        public static final String MONDAY_TABLE_NAME = "MondayTable";
        public static final String TUESDAY_TABLE_NAME = "TuesdayTable";
        public static final String WEDNESDAY_TABLE_NAME = "WednesdayTable";
        public static final String THURSDAY_TABLE_NAME = "ThursdayTable";
        public static final String FRIDAY_TABLE_NAME = "FridayTable";

        public static final String COLUMN_TIME = "time";


    }
}
