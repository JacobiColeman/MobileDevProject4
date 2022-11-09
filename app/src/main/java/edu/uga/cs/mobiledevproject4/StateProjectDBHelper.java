package edu.uga.cs.mobiledevproject4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;


import com.opencsv.CSVReader;


import java.io.InputStream;
import java.io.InputStreamReader;

public class StateProjectDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "StateProjectDBHelper";

    private static final String DB_NAME = "projectSTATES.db";
    private static final int DB_VERSION = 1;


    public static final String TABLE_PROJECTSTATES = "States";
    public static final String PROJECTSTATES_COLUMN_ID = "_id";
    public static final String PROJECTSTATES_COLUMN_STATE = "state";
    public static final String PROJECTSTATES_COLUMN_CAPITAL_CITY = "capital city";
    public static final String PROJECTSTATES_COLUMN_SECOND_CITY = "second city";
    public static final String PROJECTSTATES_COLUMN_THIRD_CITY = "third city";
    public static final String PROJECTSTATES_COLUMN_STATEHOOD = "statehood";
    public static final String PROJECTSTATES_COLUMN_CAPITAL_SINCE = "capital since";
    public static final String PROJECTSTATES_COLUMN_CAPITAL_RANK = "capital rank";

    public static final String TABLE_STATEQUIZ = "stateQuiz";
    public static final String STATE_QUIZ_COLUMN_ID = "_id";
    public static final String STATE_QUIZ_COLUMN_DATE = "date";
    public static final String STATE_QUIZ_COLUMN_TIME = "time";
    public static final String STATE_QUIZ_COLUMN_Q1A = "questionOne";
    public static final String STATE_QUIZ_COLUMN_Q2A = "questionTwo";
    public static final String STATE_QUIZ_COLUMN_Q3A = "questionThree";
    public static final String STATE_QUIZ_COLUMN_Q4A = "questionFour";
    public static final String STATE_QUIZ_COLUMN_Q5A = "questionFive";
    public static final String STATE_QUIZ_COLUMN_Q6A = "questionSix";
    public static final String STATE_QUIZ_COLUMN_Q1B = "questionOneTF";
    public static final String STATE_QUIZ_COLUMN_Q2B = "questionTwoTF";
    public static final String STATE_QUIZ_COLUMN_Q3B = "questionThreeTF";
    public static final String STATE_QUIZ_COLUMN_Q4B = "questionFourTF";
    public static final String STATE_QUIZ_COLUMN_Q5B = "questionFiveTF";
    public static final String STATE_QUIZ_COLUMN_Q6B = "questionSixTF";
    public static final String STATE_QUIZ_COLUMN_NUMCORRECT = "numberCorrect";
    public static final String STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED = "questionsAnswered";

    private static StateProjectDBHelper helperInstance;

    private static final String CREATE_PROJECTSTATES =
            "create table " + TABLE_PROJECTSTATES + " ("
                    + PROJECTSTATES_COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PROJECTSTATES_COLUMN_STATE + "TEXT, "
                    + PROJECTSTATES_COLUMN_CAPITAL_CITY + "TEXT, "
                    + PROJECTSTATES_COLUMN_SECOND_CITY + "TEXT, "
                    + PROJECTSTATES_COLUMN_THIRD_CITY + "TEXT, "
                    + PROJECTSTATES_COLUMN_STATEHOOD + "TEXT, "
                    + PROJECTSTATES_COLUMN_CAPITAL_SINCE + "TEXT,"
                    + PROJECTSTATES_COLUMN_CAPITAL_RANK + "INTEGER "
                    + ")";


    private static final String CREATE_STATEQUIZ =
            "create table " + TABLE_STATEQUIZ + " ( "
                    + STATE_QUIZ_COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + STATE_QUIZ_COLUMN_DATE + "TEXT, "
                    + STATE_QUIZ_COLUMN_TIME + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q1A + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q2A + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q3A + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q4A + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q5A + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q6A + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q1B + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q2B + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q3B + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q4B + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q5B + "TEXT, "
                    + STATE_QUIZ_COLUMN_Q6B + "TEXT, "
                    + STATE_QUIZ_COLUMN_NUMCORRECT + "INTEGER, "
                    + STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED + "INTEGER "
                    + ")";

    private Context appContext;

    private StateProjectDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.appContext = context;
    }


    public static synchronized StateProjectDBHelper getInstance(Context context) {

        if (helperInstance == null) {
            helperInstance = new StateProjectDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }



    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL(CREATE_PROJECTSTATES);
        try {
            // Open the CSV data file in the assets folder
            InputStream in_s = appContext.getAssets().open( "state_capitals.csv" );

            // read the CSV data
            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
            String[] nextRow;

            // Skip first csv row because it contains column names
            reader.readNext();

            // for every state
            while( ( nextRow = reader.readNext() ) != null ) {
                // Prepare the values for all of the necessary columns in the table
                // then store as new row in the database table representing states.
                ContentValues values = new ContentValues();

                // nextRow[] is an array of values from the csv line
                values.put( PROJECTSTATES_COLUMN_STATE, nextRow[0]);
                values.put( PROJECTSTATES_COLUMN_CAPITAL_CITY, nextRow[1]);
                values.put( PROJECTSTATES_COLUMN_SECOND_CITY, nextRow[2]);
                values.put( PROJECTSTATES_COLUMN_THIRD_CITY, nextRow[3]);
                values.put( PROJECTSTATES_COLUMN_STATEHOOD, nextRow[4]);
                values.put( PROJECTSTATES_COLUMN_CAPITAL_SINCE, nextRow[5]);
                values.put( PROJECTSTATES_COLUMN_CAPITAL_RANK, nextRow[6]);

                // Insert the new row into the database table;
                // The id (primary key) is automatically generated by the database system
                // and returned as from the insert method call.
                long id = db.insert( TABLE_PROJECTSTATES, null, values );
            } // while reading table
        } catch (Exception e) {
            Log.e( DEBUG_TAG, e.toString() );
        } // catch error
        Log.d(DEBUG_TAG, "Table " + TABLE_PROJECTSTATES + " created");

        db.execSQL(CREATE_STATEQUIZ);
        Log.d(DEBUG_TAG, "Table " + TABLE_STATEQUIZ + " created");

    }


    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_PROJECTSTATES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_PROJECTSTATES + " upgraded" );
        db.execSQL( "drop table if exists " + TABLE_STATEQUIZ );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_STATEQUIZ + " upgraded" );
    }
}
