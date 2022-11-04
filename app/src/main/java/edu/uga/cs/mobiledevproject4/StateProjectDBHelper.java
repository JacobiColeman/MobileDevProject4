package edu.uga.cs.mobiledevproject4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StateProjectDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "StateProjectDBHelper";

    private static final String DB_NAME = "projectSTATES.db";
    private static final int DB_VERSION = 1;


    public static final String TABLE_PROJECTSTATES = "States";
    public static final String PROJECTSTATES_COLUMN_STATE = "state";
    public static final String PROJECTSTATES_COLUMN_CAPITAL_CITY = "capital city";
    public static final String PROJECTSTATES_COLUMN_SECOND_CITY = "second city";
    public static final String PROJECTSTATES_COLUMN_THIRD_CITY = "third city";
    public static final String PROJECTSTATES_COLUMN_STATEHOOD = "statehood";
    public static final String PROJECTSTATES_COLUMN_CAPITAL_SINCE = "capital since";
    public static final String PROJECTSTATES_COLUMN_CAPITAL_RANK = "capital rank";

    public static final String TABLE_STATEQUIZ = "state quiz";
    public static final String STATE_QUIZ_COLUMN_ID = "_id";
    public static final String STATE_QUIZ_COLUMN_DATE = "date";
    public static final String STATE_QUIZ_COLUMN_Q1 = "question one";
    public static final String STATE_QUIZ_COLUMN_Q2 = "question two";
    public static final String STATE_QUIZ_COLUMN_Q3 = "question three";
    public static final String STATE_QUIZ_COLUMN_Q4 = "question four";
    public static final String STATE_QUIZ_COLUMN_Q5 = "question five";
    public static final String STATE_QUIZ_COLUMN_Q6 = "question six";
    public static final String STATE_QUIZ_COLUMN_NUMCORRECT = "number correct";
    public static final String STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED = "questions answered";

    private static StateProjectDBHelper helperInstance;

    private static final String CREATE_PROJECTSTATES =
            "create table " + TABLE_PROJECTSTATES + " ("
            + PROJECTSTATES_COLUMN_STATE + "TEXT PRIMARY KEY, "
            + PROJECTSTATES_COLUMN_CAPITAL_CITY + "TEXT, "
            + PROJECTSTATES_COLUMN_SECOND_CITY + "TEXT, "
            + PROJECTSTATES_COLUMN_THIRD_CITY + "TEXT, "
            + PROJECTSTATES_COLUMN_STATEHOOD + "TEXT, "
            + PROJECTSTATES_COLUMN_CAPITAL_SINCE + "TEXT,"
            + PROJECTSTATES_COLUMN_CAPITAL_RANK + "INTEGER "
            + ")";

    private static final String CREATE_STATEQUIZ =
            "create table " + TABLE_STATEQUIZ + " ( "
                    + STATE_QUIZ_COLUMN_ID  + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + STATE_QUIZ_COLUMN_DATE + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q1 + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q2 + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q3 + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q4 + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q5 + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q6 + "TEXT, "
                    + STATE_QUIZ_COLUMN_NUMCORRECT + "INTEGER, "
                    + STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED + "INTEGER "
                    + ")";


    private StateProjectDBHelper(Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }


    public static synchronized StateProjectDBHelper getInstance(Context context ) {

        if( helperInstance == null ) {
            helperInstance = new StateProjectDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }


    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_PROJECTSTATES );
        Log.d( DEBUG_TAG, "Table " + TABLE_PROJECTSTATES + " created" );

        db.execSQL( CREATE_STATEQUIZ );
        Log.d( DEBUG_TAG, "Table " + TABLE_STATEQUIZ + " created" );
    }


    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_PROJECTSTATES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_PROJECTSTATES + " upgraded" );
    }
}
