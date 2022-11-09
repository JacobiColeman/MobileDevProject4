package edu.uga.cs.mobiledevproject4;



import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class StateQuiz {
    private long   id;
    private String date;
    private String time;
    private String questionOne;
    private String questionTwo;
    private String questionThree;
    private String questionFour;
    private String questionFive;
    private String questionSix;
    private String questionOneTF;
    private String questionTwoTF;
    private String questionThreeTF;
    private String questionFourTF;
    private String questionFiveTF;
    private String questionSixTF;
    private Integer numCorrect;
    private Integer questionsAnswered;

    public StateQuiz()
    {
        this.id = -1;
        this.date = null;
        this.time = null;
        this.questionOne = null;
        this.questionTwo = null;
        this.questionThree = null;
        this.questionFour = null;
        this.questionFive = null;
        this.questionSix = null;
        this.questionOneTF = null;
        this.questionTwoTF = null;
        this.questionThreeTF = null;
        this.questionFourTF = null;
        this.questionFiveTF = null;
        this.questionSixTF = null;
        this.numCorrect = null;
        this.questionsAnswered = null;
    }

    public StateQuiz( String date, String time, String questionOne, String questionTwo, String questionThree, String questionFour, String questionFive, String questionSix, String questionOneTF, String questionTwoTF, String questionThreeTF, String questionFourTF, String questionFiveTF, String questionSixTF, Integer numCorrect, Integer questionsAnswered ) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.date = date;
        this.time = time;
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
        this.questionFour = questionFour;
        this.questionFive = questionFive;
        this.questionSix = questionSix;
        this.questionOneTF = questionOneTF;
        this.questionTwoTF = questionTwoTF;
        this.questionThreeTF = questionThreeTF;
        this.questionFourTF = questionFourTF;
        this.questionFiveTF = questionFiveTF;
        this.questionSixTF = questionSixTF;
        this.numCorrect = numCorrect;
        this.questionsAnswered = questionsAnswered;
    }


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getQuestionOne()
    {
        return questionOne;
    }

    public void setQuestionOne(String questionOne)
    {
        this.questionOne = questionOne;
    }

    public String getQuestionTwo()
    {
        return questionTwo;
    }

    public void setQuestionTwo(String questionTwo)
    {
        this.questionTwo = questionTwo;
    }

    public String getQuestionThree()
    {
        return questionThree;
    }

    public void setQuestionThree(String questionThree)
    {
        this.questionThree = questionThree;
    }

    public String getQuestionFour()
    {
        return questionFour;
    }

    public void setQuestionFour(String questionFour)
    {
        this.questionFour = questionFour;
    }

    public String getQuestionFive()
    {
        return questionFive;
    }

    public void setQuestionFive(String questionFive)
    {
        this.questionFive = questionFive;
    }

    public String getQuestionSix()
    {
        return questionSix;
    }

    public void setQuestionSix(String questionSix)
    {
        this.questionSix = questionSix;
    }

    public String getQuestionOneTF()
    {
        return questionOneTF;
    }

    public void setQuestionOneTF(String questionOneTF)
    {
        this.questionOneTF = questionOneTF;
    }

    public String getQuestionTwoTF()
    {
        return questionTwoTF;
    }

    public void setQuestionTwoTF(String questionTwoTF)
    {
        this.questionTwoTF = questionTwoTF;
    }

    public String getQuestionThreeTF()
    {
        return questionThreeTF;
    }

    public void setQuestionThreeTF(String questionThreeTF)
    {
        this.questionThreeTF = questionThreeTF;
    }

    public String getQuestionFourTF()
    {
        return questionFourTF;
    }

    public void setQuestionFourTF(String questionFourTF)
    {
        this.questionFourTF = questionFourTF;
    }

    public String getQuestionFiveTF()
    {
        return questionFiveTF;
    }

    public void setQuestionFiveTF(String questionFiveTF)
    {
        this.questionFiveTF = questionFiveTF;
    }

    public String getQuestionSixTF()
    {
        return questionSixTF;
    }

    public void setQuestionSixTF(String questionSixTF)
    {
        this.questionSixTF = questionSixTF;
    }

    public Integer getNumCorrect()
    {
        return numCorrect;
    }

    public void setNumCorrect(Integer numCorrect)
    {
        this.numCorrect = numCorrect;
    }

    public Integer getQuestionsAnswered()
    {
        return questionsAnswered;
    }

    public void setQuestionsAnswered(Integer questionsAnswered)
    {
        this.questionsAnswered = questionsAnswered;
    }

    public String toString()
    {
        return id + ": " + date + " " + time + " " + questionOne + " " + questionTwo + " " + questionThree + " " +
                questionFour + questionFive + " " + questionSix + " " + questionOneTF + " " + questionTwoTF +
                questionThreeTF + " " + questionFourTF + " " + questionFiveTF + " " + questionSixTF + numCorrect + " " +
                questionsAnswered;
    }



}


