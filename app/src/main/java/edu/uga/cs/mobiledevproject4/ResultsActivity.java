package edu.uga.cs.mobiledevproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class ResultsActivity extends AppCompatActivity {

    private boolean isFinish;
    public boolean keepVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        Fragment rFragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (intent.getBooleanExtra("finish", false) == true) {
            rFragment = new QuizFinishFragment(intent.getIntExtra("score", 25));
            isFinish = true;
        } else {
            rFragment = new PrevResultsFragment();
            isFinish = false;
            keepVal = intent.getBooleanExtra("keepMAVal", false);
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, rFragment, null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!isFinish)
            super.onBackPressed();
    }
}