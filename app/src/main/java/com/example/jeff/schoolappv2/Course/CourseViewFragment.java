package com.example.jeff.schoolappv2.Course;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jeff.schoolappv2.Assessment.AddAssessmentFragment;
import com.example.jeff.schoolappv2.Mentor.AddMentorFragment;
import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import Adapter.AssessmentAdapter;
import Adapter.CourseAdapter;
import Adapter.MentorAdapter;
import Model.Assessment;
import Model.Mentor;
import ViewModel.AssessmentViewModel;
import ViewModel.CourseViewModel;
import ViewModel.MentorViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseViewFragment extends Fragment  {
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
    private static final AddMentorFragment sAddMentorFragment= new AddMentorFragment();
    private static final AddAssessmentFragment sAddAssessmentFragment = new AddAssessmentFragment();
    private static CourseViewModel sCourseViewModel;
    private static MentorViewModel sMentorViewModel;
    private static AssessmentViewModel sAssessmentViewModel;


    private TextView courseName;
    private TextView courseStart;
    private TextView courseEnd;
    private TextView courseStatus;
    private TextView courseNotes;
    private ImageButton editCourseButton;
    private ImageButton deleteCourseButton;
    private ImageButton shareNotesButton;
    private RecyclerView mentorRecyclerView;
    private Button adddMentorButton;
    private RecyclerView assessmentRecyclerView;
    private Button addAssessmentButton;
    private Button cancelButton;
    private Button saveButton;


    public static MentorViewModel getMentorViewModel() {
        return sMentorViewModel;
    }

    private OnFragmentInteractionListener mListener;

    public CourseViewFragment() {
        // Required empty public constructor
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
        View view =  inflater.inflate(R.layout.fragment_coursedetailedview, container, false);



        courseName = view.findViewById(R.id.courseTitleTV);
        courseStart = view.findViewById(R.id.courseStartDateTV);
        courseEnd = view.findViewById(R.id.courseEndDateTV);
        courseStatus = view.findViewById(R.id.courseStatusTV);
        courseNotes = view.findViewById(R.id.courseNotesTV);
        //buttons
        editCourseButton = view.findViewById(R.id.courseEditCourseIB);
        shareNotesButton = view.findViewById(R.id.courseShareIB);
        deleteCourseButton = view.findViewById(R.id.courseDeleteCourseIB);
        addAssessmentButton = view.findViewById(R.id.courseViewAddAssessmentBtn);
        adddMentorButton = view.findViewById(R.id.courseViewAddMentorBtn);
        cancelButton = view.findViewById(R.id.courseViewCancelBtn);
        saveButton = view.findViewById(R.id.courseViewSaveBtn);

        //sets mentor recyclerview
        mentorRecyclerView = view.findViewById(R.id.courseViewMentorRV);
        mentorRecyclerView.setHasFixedSize(true);
        mentorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mentorRecyclerView.setAdapter(sMentorAdapter);

        //sets assessment recyclerview
        assessmentRecyclerView = view.findViewById(R.id.courseAssessmentInfoRV);
        assessmentRecyclerView.setHasFixedSize(true);
        assessmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        assessmentRecyclerView.setAdapter(sAssessmentAdapter);

        sMentorViewModel = ViewModelProviders.of(this).get(MentorViewModel.class);
        sMentorViewModel.getAllMentorsByCourse(CourseAdapter.sCurrentCourseId).observe(this, new Observer<List<Mentor>>() {

            @Override
            public void onChanged(@Nullable List<Mentor> mentors) {
                sMentorAdapter.setMentors(mentors);
            }

        });



        sAssessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);
        sAssessmentViewModel.getAllAssessmentsByCourse(CourseAdapter.sCurrentCourseId).observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(@Nullable List<Assessment> assessments) {
                sAssessmentAdapter.setAssessments(assessments);
            }
        });

        //inserts currently clicked data into textview
        courseName.setText(CourseAdapter.sCurrentCourse.getTitle());
        //formats the date to be displayed as string
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String startDateString = formatter.format(CourseAdapter.sCurrentCourse.getStartDate());
        String endDateString = formatter.format(CourseAdapter.sCurrentCourse.getEndDate());
        courseStart.setText(startDateString);
        courseEnd.setText(endDateString);
        courseStatus.setText(CourseAdapter.sCurrentCourse.getStatus());
        courseNotes.setText(CourseAdapter.sCurrentCourse.getNote());


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
