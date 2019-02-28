package com.example.jeff.schoolappv2.Assessment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.CourseAdapter;
import Alarm.AlarmClass;
import DatePicker.DatePickerFragment;
import Model.Assessment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddAssessmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddAssessmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAssessmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText assessmentName;
    private RadioGroup radioGroup;
    private RadioButton performance;
    private RadioButton objective;
    private EditText assessmentDueDate;
    private Button cancel;
    private Button save;
    private CheckBox notificationBox;

    private String assessmentType;
    private Assessment assessment;
    public static final int REQUEST_CODE = 1;
    private String dueDate;


    private OnFragmentInteractionListener mListener;


    public AddAssessmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAssessmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAssessmentFragment newInstance(String param1, String param2) {
        AddAssessmentFragment fragment = new AddAssessmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_assessmentaddassessment, container, false);

        assessmentName = view.findViewById(R.id.assessmentNameET);


        assessmentDueDate = view.findViewById(R.id.assessmentDueDateET);
        save = view.findViewById(R.id.assessmentSaveBTN);
        cancel = view.findViewById(R.id.assessmentCancelBTN);
        radioGroup = view.findViewById(R.id.addRadioGroup);
        objective = view.findViewById(R.id.objectiveRB);
        performance = view.findViewById(R.id.performanceRB);
        notificationBox = view.findViewById(R.id.addAssessmentAlertCB);


        final FragmentManager fmDate = ((AppCompatActivity) getActivity()).getSupportFragmentManager();


        assessmentDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment dateFragment = new DatePickerFragment();
                dateFragment.setTargetFragment(AddAssessmentFragment.this, REQUEST_CODE);
                dateFragment.show(fmDate, "datePicker");


            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            dueDate = data.getStringExtra("selectedDate");
            assessmentDueDate.setText(dueDate);

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

    public void clearData() {
        assessmentName.setText("");

        assessmentDueDate.setText("");
    }


    public void saveData() {

        boolean cancel = false;

        if (TextUtils.isEmpty(assessmentName.getText())) {
            assessmentName.setError("Assessment name must be entered");
            cancel = true;
        }
        if (TextUtils.isEmpty(assessmentDueDate.getText())) {
            assessmentDueDate.setError("Assessment due date must be selected");
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
            //converts date from string to date format
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date dateDate;
            if (objective.isChecked()) {
                assessmentType = "Objective";
            } else if (performance.isChecked()) {
                assessmentType = "Performance";
            }


            try {

                dateDate = formatter.parse(dueDate.toString());
                assessment = new Assessment(CourseAdapter.getCurrentCourseId(), assessmentType, assessmentName.getText().toString(), dateDate);
                if (notificationBox.isChecked()) {
                    Intent dueIntent = new Intent(getActivity(), AlarmClass.class);
                    dueIntent.putExtra("name", assessmentName.getText().toString());
                    dueIntent.putExtra("startDate", "Assessment is Due Today " + dueDate);
                    PendingIntent startSender = PendingIntent.getBroadcast(getActivity(), 0, dueIntent, 0);
                    AlarmManager startAlarmManager = (AlarmManager) getActivity().getSystemService(getContext().ALARM_SERVICE);
                    startAlarmManager.set(AlarmManager.RTC_WAKEUP, dateDate.getTime(), startSender);
                }
                AssessmentViewFragment.getAssessmentViewModel().insert(assessment);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            clearData();
            getActivity().onBackPressed();


        }
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
