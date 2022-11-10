package edu.uga.cs.mobiledevproject4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QPAdapter extends RecyclerView.Adapter<QPAdapter.ViewHolder> {

    ArrayList<QuizPagerItem> quizPageList;
    ArrayList<Integer> scorePerPage = new ArrayList<Integer>();

    public QPAdapter(ArrayList<QuizPagerItem> quizPageList) {
        this.quizPageList = quizPageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizpager_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizPagerItem quizPagerItem = quizPageList.get(position);
        if (quizPagerItem.firstQStatus == 0 && quizPagerItem.secondQStatus == 0) {
            System.out.println(position);
            scorePerPage.add(0);
        }
        int qNumBase = quizPagerItem.pageNum - 1;
        String capText = "Question " + ((2*qNumBase) + 1) + ": What is the capital of " +
                quizPagerItem.stateName + "?";
        String popText = "Question " + ((2*qNumBase) + 2) + ": Is " + quizPagerItem.stateName +
                "'s capital its most populous city?";
        holder.capitalQText.setText(capText);
        holder.popQText.setText(popText);
        setQuestionListeners(quizPagerItem, holder, position);
        if (qNumBase >= 5) {
            holder.tempSubmitB.setVisibility(View.VISIBLE);
            holder.tempSubmitB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int finalScore = 0;
                    for (int i = 0; i < scorePerPage.size(); i++) {
                        finalScore = finalScore + scorePerPage.get(i);
                        
                    }
                    System.out.println("You scored " + finalScore + " out of 12 goes here");//put score here for testing
                    Intent fIntent = new Intent(view.getContext(), ResultsActivity.class);
                    fIntent.putExtra("finish", true);
                    fIntent.putExtra("score", finalScore);
                    view.getContext().startActivity(fIntent);
                }
            });
        }
        //bind values for output variables here
    }

    public void setQuestionListeners(QuizPagerItem q, ViewHolder h, int pos) {
        h.capitalCs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int qpPoints = scorePerPage.get(pos);
                if (i == q.firstQAnswer) {
                    if (q.firstQStatus < 2) {
                        q.firstQStatus = 2;
                        q.points++;
                        qpPoints++;
                    }
                } else {
                    if (q.firstQStatus == 2) {
                        q.points--;
                        qpPoints--;
                    }
                    q.firstQStatus = 1;
                }
                scorePerPage.set(pos, qpPoints);
            }
        });

        h.popCs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int qpPoints = scorePerPage.get(pos);
                if (i == q.secondQAnswer) {
                    if (q.secondQStatus < 2) {
                        q.secondQStatus = 2;
                        q.points++;
                        qpPoints++;
                    }
                } else {
                    if (q.secondQStatus == 2) {
                        q.points--;
                        qpPoints--;
                    }
                    q.secondQStatus = 1;
                }
                scorePerPage.set(pos, qpPoints);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizPageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView capitalQText;
        TextView popQText;
        Button tempSubmitB;
        RadioGroup capitalCs;
        RadioGroup popCs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            capitalQText = itemView.findViewById(R.id.pcapital_qtext);
            popQText = itemView.findViewById(R.id.ppop_qtext);
            tempSubmitB = itemView.findViewById(R.id.tempSubmitButton);
            capitalCs = itemView.findViewById(R.id.capitalChoices);
            popCs = itemView.findViewById(R.id.popChoices);
        }
    }

}
