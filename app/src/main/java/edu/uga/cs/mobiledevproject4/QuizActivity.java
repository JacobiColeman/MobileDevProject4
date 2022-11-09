package edu.uga.cs.mobiledevproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ArrayList<QuizPagerItem> quizPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewPager = findViewById(R.id.qViewPager);
        int pN = 1;
        int[] pNums = {pN++, pN++, pN++, pN++, pN++, pN};
        String[] sNames = {"Georgia", "Florida", "South Carolina", "North Carolina", "Michigan",
        "Hawaii"}; //access resources with DBHelper here, random assortment of states

        quizPagerItemArrayList = new ArrayList<>();

        for (int i = 0; i < sNames.length; i++) {
            quizPagerItemArrayList.add(new QuizPagerItem(pNums[i], sNames[i]));
        }


        QPAdapter qpAdapter = new QPAdapter(quizPagerItemArrayList);
        viewPager.setAdapter(qpAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(QuizActivity.this, MainActivity.class);
        mIntent.putExtra("unfinished", true);
        startActivity(mIntent);
    }
}