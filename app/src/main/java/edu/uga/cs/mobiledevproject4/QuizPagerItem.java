package edu.uga.cs.mobiledevproject4;

public class QuizPagerItem {

    int pageNum;
    String stateName;
    int firstQStatus;
    int secondQStatus;
    int points;
    int firstQAnswer;
    int secondQAnswer;

    public QuizPagerItem(int pageNum, String stateName) {
        this.pageNum = pageNum;
        this.stateName = stateName;
        points = firstQStatus = secondQStatus = 0;
        firstQAnswer = R.id.pcapital_butt2; //add parameter for answer choice ids after button ordering and db fetching
        secondQAnswer = R.id.ppop_butt2; //is done
    }


}
