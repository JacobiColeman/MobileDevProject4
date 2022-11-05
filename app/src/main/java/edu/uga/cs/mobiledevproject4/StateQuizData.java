package edu.uga.cs.mobiledevproject4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StateQuizData {

    public static final String DEBUG_TAG = "StateQuizData";

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
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q1,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q2,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q3,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q4,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q5,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_Q6,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_NUMCORRECT,
            StateProjectDBHelper.STATE_QUIZ_COLUMN_QUESTIONS_ANSWERED,
    };

    public JobLeadsData( Context context ) {
        this.stateProjectDbHelper = StateProjectDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = stateProjectDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "JobLeadsData: db open" );
    }

    // Close the database
    public void close() {
        if( stateProjectDbHelper != null ) {
            stateProjectDbHelper.close();
            Log.d(DEBUG_TAG, "JobLeadsData: db closed");
        }
    }

    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    // Retrieve all job leads and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    // For each retrieved row, we create a new JobLead (Java POJO object) instance and add it to the list.
    public List<JobLead> retrieveAllJobLeads() {
        ArrayList<JobLead> jobLeads = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(StateProjectDBHelper.TABLE_JOBLEADS, allColumns,
                    null, null, null, null, null );

            // collect all job leads into a List
            if( cursor != null && cursor.getCount() > 0 ) {

                while( cursor.moveToNext() ) {

                    if( cursor.getColumnCount() >= 5) {

                        // get all attribute values of this job lead
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.JOBLEADS_COLUMN_ID );
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.JOBLEADS_COLUMN_NAME );
                        String name = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.JOBLEADS_COLUMN_PHONE );
                        String phone = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.JOBLEADS_COLUMN_URL );
                        String uri = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateProjectDBHelper.JOBLEADS_COLUMN_COMMENTS );
                        String comments = cursor.getString( columnIndex );

                        // create a new JobLead object and set its state to the retrieved values
                        JobLead jobLead = new JobLead( name, phone, uri, comments );
                        jobLead.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        jobLeads.add( jobLead );
                        Log.d(DEBUG_TAG, "Retrieved JobLead: " + jobLead);
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
        return jobLeads;
    }

    // Store a new job lead in the database.
    public JobLead storeJobLead( JobLead jobLead ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        ContentValues values = new ContentValues();
        values.put( StateProjectDBHelper.JOBLEADS_COLUMN_NAME, jobLead.getCompanyName());
        values.put( StateProjectDBHelper.JOBLEADS_COLUMN_PHONE, jobLead.getPhone() );
        values.put( StateProjectDBHelper.JOBLEADS_COLUMN_URL, jobLead.getUrl() );
        values.put( StateProjectDBHelper.JOBLEADS_COLUMN_COMMENTS, jobLead.getComments() );

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( StateProjectDBHelper.TABLE_JOBLEADS, null, values );

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        jobLead.setId( id );

        Log.d( DEBUG_TAG, "Stored new job lead with id: " + String.valueOf( jobLead.getId() ) );

        return jobLead;
    }


}
