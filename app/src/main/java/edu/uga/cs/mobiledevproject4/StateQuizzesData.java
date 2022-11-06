package edu.uga.cs.mobiledevproject4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StateQuizzesData {

    public static final String DEBUG_TAG = "StateQuizzesData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase   db;
    private SQLiteOpenHelper stateProjectDbHelper;
    private static final String[] allColumns = {
            StateProjectDBHelper.PROJECTSTATES_COLUMN_STATE,
            StateProjectDBHelper.PROJECTSTATES_COLUMN_CAPITAL_CITY,
            StateProjectDBHelper.PROJECTSTATES_COLUMN_SECOND_CITY,
            StateProjectDBHelper.PROJECTSTATES_COLUMN_THIRD_CITY,
            StateProjectDBHelper.PROJECTSTATES_COLUMN_STATEHOOD,
            StateProjectDBHelper.PROJECTSTATES_COLUMN_CAPITAL_SINCE,
            StateProjectDBHelper.PROJECTSTATES_COLUMN_CAPITAL_RANK,

            StateProjectDBHelper.STATE_QUIZ_COLUMN_ID,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_DATE,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1A,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2A,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3A,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4A,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5A,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6A,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1B,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2B,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3B,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4B,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5B,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6B,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_NUMCORRECT,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED,
    };

    public StateQuizzesData(Context context ) {
        this.stateProjectDbHelper = StateProjectDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = stateProjectDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "StateQuizzesData: db open" );
    }

    // Close the database
    public void close() {
        if( stateProjectDbHelper != null ) {
            stateProjectDbHelper.close();
            Log.d(DEBUG_TAG, "StateQuizzesData: db closed");
        }
    }

    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    // Retrieve all job leads and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    // For each retrieved row, we create a new JobLead (Java POJO object) instance and add it to the list.
    public List<StateQuiz> retrieveAllStateQuizzes() {
        ArrayList<StateQuiz> jobLeads = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(StateProjectDBHelper.TABLE_STATEQUIZ, allColumns,
                    null, null, null, null, null );

            // collect all job leads into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 5) {

                        // get all attribute values of this job lead
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_DATE );
                        String date = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1A );
                        String questionOne = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2A );
                        String questionTwo = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3A );
                        String questionThree = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4A );
                        String questionFour = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5A );
                        String questionFive = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6A );
                        String questionSix = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1B );
                        String questionOneTF = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2B );
                        String questionTwoTF = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3B );
                        String questionThreeTF = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4B );
                        String questionFourTF = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5B );
                        String questionFiveTF = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6B );
                        String questionSixTF = cursor.getString( columnIndex );


                        // create a new StateQuiz object and set its state to the retrieved values
                        StateQuiz stateQuiz = new StateQuiz( date, questionOne , questionTwo, questionThree, questionFour, questionFive, questionSix, questionOneTF, questionTwoTF, questionThreeTF, questionFourTF, questionFiveTF, questionSixTF );
                        stateQuiz.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        stateQuiz.add( stateQuiz );
                        Log.d(DEBUG_TAG, "Retrieved JobLead: " + stateQuiz);
                    }
                }
            }
            if( cursor != null )
                Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
            else
                Log.d( DEBUG_TAG, "Number of records from DB: 0" );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved job leads
        return statequiz;
    }

    // Store a new job lead in the database.
    public StateQuiz storeStateQuiz( StateQuiz stateQuiz ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        ContentValues values = new ContentValues();
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_DATE, stateQuiz.getDate());
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1A, stateQuiz.getQuestionOne() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2A, stateQuiz.getQuestionTwo() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3A, stateQuiz.getQuestionThree() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4A, stateQuiz.getQuestionFour());
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5A, stateQuiz.getQuestionFive() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6A, stateQuiz.getQuestionSix() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1B, stateQuiz.getQuestionOneTF() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2B, stateQuiz.getQuestionTwoTF());
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3B, stateQuiz.getQuestionThreeTF() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4B, stateQuiz.getQuestionFourTF() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5B, stateQuiz.getQuestionFiveTF() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6B, stateQuiz.getQuestionSixTF());
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_NUMCORRECT, stateQuiz.getNumCorrect() );
        values.put( StateProjectDBHelper.STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED, stateQuiz.getQuestionsAnswered() );


        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( StateProjectDBHelper.TABLE_STATEQUIZ, null, values );

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        stateQuiz.setId( id );

        Log.d( DEBUG_TAG, "A new quiz is stored with id: " + String.valueOf( stateQuiz.getId() ) );

        return stateQuiz;
    }


}
