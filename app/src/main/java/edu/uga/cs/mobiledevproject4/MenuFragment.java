package edu.uga.cs.mobiledevproject4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean resQTestBool = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        MainActivity mA = (MainActivity) getActivity();
        View menuView = inflater.inflate(R.layout.fragment_menu, container, false);
        TextView quizDescript = menuView.findViewById(R.id.quiz_descript);
        InputStream inputStream = getResources().openRawResource(R.raw.quizdescription);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Button newQzButtn = menuView.findViewById(R.id.newQzButton);
        Button resumeQzButtn = menuView.findViewById(R.id.resumeQzButton);
        Button pRButton = menuView.findViewById(R.id.prevResultsButton);
        newQzButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeQuiz();
                mA.unfinishedQuiz = true;
            }
        });
        resumeQzButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeQuiz(); //add params when you get db access, should distinguish between
                // new quiz and resumed quiz
            }
        });
        pRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pIntent = new Intent(getActivity(), ResultsActivity.class);
                pIntent.putExtra("finish", false);
                pIntent.putExtra("keepMAVal", mA.unfinishedQuiz);
                getActivity().startActivity(pIntent);
            }
        });
        if (!mA.unfinishedQuiz) {
            resumeQzButtn.setVisibility(View.INVISIBLE);
        }
        try {
            String eachLine = bufferedReader.readLine();
            while (eachLine != null) {
               quizDescript.append(eachLine);
               eachLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.err.println("There was a problem loading the Menu Description Text!");
        }
        return menuView;
    }

    public void executeQuiz() { //edit when you get db access, difference between new and resumed
        Intent qIntent = new Intent(getActivity(), QuizActivity.class);
        getActivity().startActivity(qIntent);
        /*Fragment qFragment = new QuizFragment(); // quizzes. Rn it just brings you to prototype
        FragmentManager fragmentManager = getParentFragmentManager();//quiz fragment
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, qFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();*/
    }
}