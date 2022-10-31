package edu.uga.cs.mobiledevproject4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Project4DBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "Project4DBHelper";

    private static final String DB_NAME = "project4.db";
    private static final int DB_VERSION = 1;


    public static final String TABLE_PROJECT4 = "States";
    public static final String PROJECT4_COLUMN_STATE = "state";
    public static final String PROJECT4_COLUMN_CAPITAL_CITY = "capital city";
    public static final String PROJECT4_COLUMN_SECOND_CITY = "second city";
    public static final String PROJECT4_COLUMN_THIRD_CITY = "third city";
    public static final String PROJECT4_COLUMN_STATEHOOD = "statehood";
    public static final String PROJECT4_COLUMN_CAPITAL_SINCE = "capital since";
    public static final String PROJECT4_COLUMN_CAPITAL_RANK = "capital rank";


    private static Project4DBHelper helperInstance;


    private static final String CREATE_PROJECT4 =
            "create table " + TABLE_PROJECT4 + " ("
                    + PROJECT4_COLUMN_STATE + " TEXT PRIMARY KEY, "
                    + PROJECT4_COLUMN_CAPITAL_CITY + " TEXT , "
                    + PROJECT4_COLUMN_SECOND_CITY + " TEXT, "
                    + PROJECT4_COLUMN_THIRD_CITY + " TEXT, "
                    + PROJECT4_COLUMN_STATEHOOD + " TEXT, "
                    + PROJECT4_COLUMN_CAPITAL_SINCE + " TEXT,"
                    + PROJECT4_COLUMN_CAPITAL_RANK + " INTEGER, "
                    + ")";


    private Project4DBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }


    public static synchronized Project4DBHelper getInstance( Context context ) {

        if( helperInstance == null ) {
            helperInstance = new Project4DBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }


    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_PROJECT4 );
        Log.d( DEBUG_TAG, "Table " + TABLE_PROJECT4 + " created" );
    }


    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_PROJECT4 );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_PROJECT4 + " upgraded" );
    }
}
