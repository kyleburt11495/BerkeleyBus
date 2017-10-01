package com.example.bus.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kyle on 9/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "BusDatabase.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //sql query to create table
        final String SQL_CREATE_MONDAY_TABLE = "CREATE TABLE " + DatabaseContract.DatabaseEntry.MONDAY_TABLE_NAME +
            " (" + DatabaseContract.DatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.DatabaseEntry.COLUMN_MONDAY + " TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_TUESDAY_TABLE = "CREATE TABLE " + DatabaseContract.DatabaseEntry.TUESDAY_TABLE_NAME +
                " (" + DatabaseContract.DatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.DatabaseEntry.COLUMN_TUESDAY + " TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_WEDNESDAY_TABLE = "CREATE TABLE " + DatabaseContract.DatabaseEntry.WEDNESDAY_TABLE_NAME +
                " (" + DatabaseContract.DatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.DatabaseEntry.COLUMN_WEDNESDAY + " TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_THURSDAY_TABLE = "CREATE TABLE " + DatabaseContract.DatabaseEntry.THURSDAY_TABLE_NAME +
                " (" + DatabaseContract.DatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.DatabaseEntry.COLUMN_THURSDAY + " TEXT NOT NULL" +
                ");";

        final String SQL_CREATE_FRIDAY_TABLE = "CREATE TABLE " + DatabaseContract.DatabaseEntry.FRIDAY_TABLE_NAME +
                " (" + DatabaseContract.DatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.DatabaseEntry.COLUMN_FRIDAY + " TEXT NOT NULL" +
                ");";



        db.execSQL(SQL_CREATE_MONDAY_TABLE);
        db.execSQL(SQL_CREATE_TUESDAY_TABLE);
        db.execSQL(SQL_CREATE_WEDNESDAY_TABLE);
        db.execSQL(SQL_CREATE_THURSDAY_TABLE);
        db.execSQL(SQL_CREATE_FRIDAY_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.MONDAY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.TUESDAY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.WEDNESDAY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.THURSDAY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DatabaseEntry.FRIDAY_TABLE_NAME);


        onCreate(db);
    }
}
