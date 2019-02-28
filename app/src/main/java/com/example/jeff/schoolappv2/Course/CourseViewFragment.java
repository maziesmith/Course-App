package com.example.jeff.schoolappv2.Course;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Assessment.AddAssessmentFragment;
import com.example.jeff.schoolappv2.Assessment.AssessmentViewFragment;
import com.example.jeff.schoolappv2.Mentor.AddMentorFragment;
import com.example.jeff.schoolappv2.Mentor.MentorViewFragment;
import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Adapter.AssessmentAdapter;
import Adapter.CourseAdapter;
import Adapter.MentorAdapter;
import Database.AppDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final MentorAdapter sMentorAdapter = new MentorAdapter();
    private static final AssessmentAdapter sAssessmentAdapter = new AssessmentAdapter();
    private static final CourseAdapter sCourseAdapter = new CourseAdapter();
    private static AssessmentViewFragment sAssessmentViewFragment = new AssessmentViewFragment();
    private static MentorViewFragment sMentorViewFragment = new MentorViewFragment();
    private static AddAssessmentFragment sAddAssessmentFragment = new AddAssessmentFragment();
    private static AddMentorFragment sAddMentorFragment = new AddMentorFragment();
    private static final EditCourseFragment sEditCourseFragment = new EditCourseFragment();


    private AppDatabase appDatabase;


    private TextView courseName;
    private TextView courseStart;
    private TextView courseEnd;
    private TextView courseStatus;
    private TextView courseNotes;
    private ImageButton editCourseButton;
    private ImageButton shareNotesButton;
    private Button viewMentorBtn;
    private Button addMentorBtn;
    private Button addAssessmentBtn;
    private Button viewAssessmentBtn;
    private Button cancelButton;
    private Button saveButton;


    private OnFragmentInteractionListener mListener;

    public CourseViewFragment() {
        // Required empty public constructor
    }

    public static MentorAdapter getMentorAdapter() {
        return sMentorAdapter;
    }

    public static AssessmentAdapter getAssessmentAdapter() {
        return sAssessmentAdapter;
    }

    public static CourseAdapter getCourseAdapter() {
        return sCourseAdapter;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseViewFragment newInstance(String param1, String param2) {
        CourseViewFragment fragment = new CourseViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_coursedetailedview, container, false);


        courseName = view.findViewById(R.id.courseTitleTV);
        courseStart = view.findViewById(R.id.courseStartDateTV);
        courseEnd = view.findViewById(R.id.courseEndDateTV);
        courseStatus = view.findViewById(R.id.courseStatusTV);
        courseNotes = view.findViewById(R.id.courseNotesTV);
        //buttons
        editCourseButton = view.findViewById(R.id.courseEditCourseIB);
        shareNotesButton = view.findViewById(R.id.courseShareIB);
        viewAssessmentBtn = view.findViewById(R.id.courseViewAssessmentBtn);
        viewMentorBtn = view.findViewById(R.id.courseViewMentorsBtn);


        cancelButton = view.findViewById(R.id.courseViewCancelBtn);


        //inserts currently clicked data into textview
        courseName.setText(CourseAdapter.getCurrentCourse().getTitle());
        //formats the date to be displayed as string
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String startDateString = formatter.format(CourseAdapter.getCurrentCourse().getStartDate());
        String endDateString = formatter.format(CourseAdapter.getCurrentCourse().getEndDate());
        courseStart.setText(startDateString);
        courseEnd.setText(endDateString);
        courseStatus.setText(CourseAdapter.getCurrentCourse().getStatus());
        courseNotes.setText(CourseAdapter.getCurrentCourse().getNote());

        //edit course button loads new activity with currently clicked course data
        editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(v.getContext(), EditCourseActivity.class);
//                v.getContext().startActivity(intent);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.courseActivityViewFrame, sEditCourseFragment);
                ft.commit();

            }
        });


        //share button to share notes
        shareNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, courseNotes.getText().toString());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });


        //view mentor button
        viewMentorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.courseActivityViewFrame, sMentorViewFragment);
                ft.commit();


            }
        });


        // view assessment button

        viewAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.courseActivityViewFrame, sAssessmentViewFragment);
                ft.commit();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });




        return view;


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
