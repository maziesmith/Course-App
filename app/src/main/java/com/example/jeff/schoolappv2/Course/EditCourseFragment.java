package com.example.jeff.schoolappv2.Course;

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

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.CourseAdapter;
import DatePicker.DatePickerFragment;
import Model.Course;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditCourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText courseTitle;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText courseStatus;
    private EditText courseNotes;
    private Button save;
    private Button cancel;
    private Course course;

    //used for datepicker
    private static final int START_CODE = 1;
    private static final int END_CODE = 2;
    private String selectedStart;
    private String selectedEnd;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCourseFragment newInstance(String param1, String param2) {
        EditCourseFragment fragment = new EditCourseFragment();
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
        View view = inflater.inflate(R.layout.fragment_courseeditcourse, container, false);
        courseTitle = view.findViewById(R.id.editCourseNameET);
        courseStart = view.findViewById(R.id.editStartDateET);
        courseEnd = view.findViewById(R.id.editEndDateET);
        courseStatus = view.findViewById(R.id.editStatusET);
        courseNotes = view.findViewById(R.id.editNotesET);
        save = view.findViewById(R.id.editAddCourseSaveBTN);
        cancel = view.findViewById(R.id.editAddCourseCancelBTN);
        final FragmentManager fmDate = ((AppCompatActivity) getActivity()).getSupportFragmentManager();


        course = CourseAdapter.getCurrentCourse();
        courseTitle.setText(course.getTitle());
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String courseStartString = formatter.format(course.getStartDate());
        String courseEndString = formatter.format(course.getEndDate());
        courseStart.setText(courseStartString);
        courseEnd.setText(courseEndString);
        courseStatus.setText(course.getStatus());
        courseNotes.setText(course.getNote());

        //datepicker for courseStart Edittext
        courseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(EditCourseFragment.this, START_CODE);
                newFragment.show(fmDate, "datePicker");

            }
        });

        //datepicker for courseEnd Edittext
        courseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(EditCourseFragment.this, END_CODE);
                newFragment.show(fmDate, "datePicker");

            }
        });


        //save button should update currently selected course
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();


                //changes back to previous screen


            }
        });

        //cancels editing currently selected course
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });


        return view;
    }

    //used to set datepicker data to certain editText
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == START_CODE && resultCode == Activity.RESULT_OK) {
            selectedStart = data.getStringExtra("selectedDate");
            courseStart.setText(selectedStart);
        } else if (requestCode == END_CODE && resultCode == Activity.RESULT_OK) {
            selectedEnd = data.getStringExtra("selectedDate");
            courseEnd.setText(selectedEnd);
        }
    }

    //save data
    public void saveData() {
        //validation
        boolean cancel = false;


        if (TextUtils.isEmpty(courseTitle.getText())) {
            courseTitle.setError("Course Title must be entered");
            cancel = true;
        }
        if (TextUtils.isEmpty(courseStatus.getText())) {
            courseStatus.setError("Course status must be selected");
            cancel = true;
        }
        if (TextUtils.isEmpty(courseStart.getText())) {
            courseStart.setError("Start date must be selected");
            cancel = true;
        }
        if (TextUtils.isEmpty(courseEnd.getText())) {
            courseEnd.setError("End date must be selected");
            cancel = true;
        }
        if (TextUtils.isEmpty(courseNotes.getText())) {
            courseNotes.setError("A minimum of one note must be entered");
            cancel = true;
        }


        if (cancel) {
            // Toast.makeText(getContext(), "Wrong data", Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();
            String courseTitleString = courseTitle.getText().toString();
            String courseStatusString = courseStatus.getText().toString();


            String courseNotesString = courseNotes.getText().toString();

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate;
            Date endDate;
            try {
                startDate = formatter.parse(courseStart.getText().toString());
                endDate = formatter.parse(courseEnd.getText().toString());
                course.setTitle(courseTitleString);
                course.setNote(courseNotesString);
                course.setStatus(courseStatusString);
                course.setStartDate(startDate);
                course.setEndDate(endDate);
                CourseFragment.getCourseViewModel().update(course);
                getActivity().finish();


            } catch (ParseException e) {
                e.printStackTrace();
            }




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
