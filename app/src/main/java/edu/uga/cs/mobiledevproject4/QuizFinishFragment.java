package edu.uga.cs.mobiledevproject4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFinishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFinishFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int fScore = 25;

    public QuizFinishFragment() {
        // Required empty public constructor
    }

    public QuizFinishFragment(int f) {
        fScore = f;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFinishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFinishFragment newInstance(String param1, String param2) {
        QuizFinishFragment fragment = new QuizFinishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View finishView = inflater.inflate(R.layout.fragment_quiz_finish, container, false);
        TextView finScoreText = finishView.findViewById(R.id.finishScoreText);
        TableLayout prevTable = finishView.findViewById(R.id.prevScoresTable);
        Button retButton = finishView.findViewById(R.id.returnMenuButton);
        finScoreText.setText(fScore + "/12 points!");
        retButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getActivity(), MainActivity.class);
                mIntent.putExtra("unfinished", false);
                getActivity().startActivity(mIntent); //add bundle here that changes unfinishedQuiz
                //Bool value
            }
        });
        return finishView;
    }
}