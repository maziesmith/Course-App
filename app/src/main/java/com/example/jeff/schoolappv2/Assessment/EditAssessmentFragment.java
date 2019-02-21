package com.example.jeff.schoolappv2.Assessment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.AssessmentAdapter;
import Model.Assessment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditAssessmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditAssessmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAssessmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText assessmentName;
    private EditText assessmentDueDate;
    private RadioButton objective;
    private RadioButton performance;
    private RadioGroup radioGroup;
    private Button save;
    private Button cancel;

    private Assessment assessment;
    private String assessmentType;

    public EditAssessmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditAssessmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditAssessmentFragment newInstance(String param1, String param2) {
        EditAssessmentFragment fragment = new EditAssessmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_assessmenteditassessment, container, false);
        assessmentName = view.findViewById(R.id.editAssessmentNameET);
        assessmentDueDate = view.findViewById(R.id.editAssessmentDueDateET);
        save = view.findViewById(R.id.editAssessmentSaveBTN);
        cancel = view.findViewById(R.id.editAssessmentCancelBTN);
        radioGroup = view.findViewById(R.id.editRadioGroup);
        objective = view.findViewById(R.id.editObjectiveRB);
        performance = view.findViewById(R.id.editPerformanceRB);


        assessment = AssessmentAdapter.getCurrentAssessment();
        assessmentName.setText(assessment.getTitle());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateDueString = formatter.format(assessment.getDateDue());

        assessmentDueDate.setText(dateDueString);
        String type = assessment.getType();

        if (type.equals("performance")) {
            performance.setChecked(true);
            objective.setChecked(false);
        } else if (type.equals("objective")) {
            objective.setChecked(true);
            performance.setChecked(false);
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    updateData();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                clearData();
                getActivity().onBackPressed();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                getActivity().onBackPressed();


            }
        });


        return view;

    }

    public void clearData() {
        assessmentName.setText("");
        performance.setChecked(false);
        objective.setChecked(false);
        assessmentDueDate.setText("");
    }


    public void updateData() throws ParseException {

        //converts date from string to date format

        String nameString = assessmentName.getText().toString();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dueDate;
        if (objective.isChecked()) {
            assessmentType = "Objective";
        } else if (performance.isChecked()) {
            assessmentType = "performance";
        }
        dueDate = formatter.parse(assessmentDueDate.getText().toString());


        assessment.setTitle(nameString);
        assessment.setType(assessmentType);
        assessment.setDateDue(dueDate);


        AssessmentViewFragment.getAssessmentViewModel().update(assessment);
        clearData();


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
