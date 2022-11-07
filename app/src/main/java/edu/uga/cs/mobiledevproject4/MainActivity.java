package edu.uga.cs.mobiledevproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public boolean unfinishedQuiz;
    public boolean backAllowed = false;
    //protected StateProjectDBHelper dbHelp = StateProjectDBHelper.getInstance(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unfinishedQuiz = true; //Access db with dbHelp here to determine unfinishedQuiz value
    }

    @Override
    public void onBackPressed() {
        if (backAllowed)
            super.onBackPressed();
    }
}