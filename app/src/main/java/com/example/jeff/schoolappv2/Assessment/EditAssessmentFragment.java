package com.example.jeff.schoolappv2.Assessment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.AssessmentAdapter;
import DatePicker.DatePickerFragment;
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
    private EditText assessmentDate;
    private RadioButton performance;
    private RadioButton objective;
    private Button cancel;
    private Button save;

    private Assessment assessment;


    private static final int DATE_CODE = 1;
    private String date;
    private String assessmentTypeString;
    private Date assessmentDueDate;


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
        assessmentDate = view.findViewById(R.id.editAssessmentDueDateET);
        performance = view.findViewById(R.id.editPerformanceRB);
        objective = view.findViewById(R.id.editObjectiveRB);
        cancel = view.findViewById(R.id.editAssessmentCancelBTN);
        save = view.findViewById(R.id.editAssessmentSaveBTN);
        final FragmentManager fmDate = ((AppCompatActivity) getActivity()).getSupportFragmentManager();


        assessment = AssessmentAdapter.getCurrentAssessment();
        assessmentName.setText(assessment.getName());
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        if (assessment.getDateDue() != null) {
            String currentAssessmentDateString = formatter.format(assessment.getDateDue());
            assessmentDate.setText(currentAssessmentDateString);
        }

        if (assessment.getType().equals("Performance")) {
            performance.setChecked(true);
            objective.setChecked(false);
        } else if (assessment.getType().equals("Objective")) {
            objective.setChecked(true);
            performance.setChecked(false);

        }


        assessmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(EditAssessmentFragment.this, DATE_CODE);
                newFragment.show(fmDate, "datePicker");

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return view;

    }

    public void updateData() {
        boolean cancel = false;

        if (TextUtils.isEmpty(assessmentName.getText())) {
            assessmentName.setError("Assessment name must be entered");
            cancel = true;
        }
        if (TextUtils.isEmpty(assessmentDate.getText())) {
            assessmentDate.setError("Assessment due date must be selected");
            cancel = true;
        }
        if (!performance.isChecked() && !objective.isChecked()) {
            performance.setError("Assessment type must be selected");
            cancel = true;
        }


        if (cancel) {
            // Toast.makeText(getContext(), "Wrong data", Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            if (performance.isChecked()) {
                assessmentTypeString = "Performance";
            } else if (objective.isChecked()) {
                assessmentTypeString = "Objective";

            }


            try {
                assessmentDueDate = formatter.parse(assessmentDate.getText().toString());

                Toast.makeText(getContext(), "Current Assessment " + AssessmentAdapter.getCurrentAssessment().getAssessmentId(), Toast.LENGTH_SHORT).show();

                assessment.setName(assessmentName.getText().toString());
                assessment.setDateDue(assessmentDueDate);
                assessment.setType(assessmentTypeString);


                AssessmentViewFragment.getAssessmentViewModel().update(assessment);
                getActivity().onBackPressed();


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DATE_CODE && resultCode == Activity.RESULT_OK) {
            date = data.getStringExtra("selectedDate");
            assessmentDate.setText(date);
        }
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
