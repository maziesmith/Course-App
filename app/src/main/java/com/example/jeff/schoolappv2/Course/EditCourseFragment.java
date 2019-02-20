package com.example.jeff.schoolappv2.Course;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Assessment.AddAssessmentFragment;
import com.example.jeff.schoolappv2.Mentor.AddMentorFragment;
import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.TermAdapter;
import Model.Assessment;
import Model.Course;
import Model.Mentor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CHANNEL_ID = "channel_id";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Course course;
    private Mentor mentor;
    private Assessment assessment;
    private static final AddMentorFragment sAddMentorFragment = new AddMentorFragment();
    private static final AddAssessmentFragment sAddAssessmentFragment = new AddAssessmentFragment();


    //layout button,editext
    private EditText courseTitle;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText courseStatus;
    private EditText courseNotes;
    private Button save;
    private Button cancel;


    private OnFragmentInteractionListener mListener;

    public EditCourseFragment() {
        // Required empty public constructor
    }

    public static AddMentorFragment getAddMentorFragment() {
        return sAddMentorFragment;
    }

    public static AddAssessmentFragment getAddAssessmentFragment() {
        return sAddAssessmentFragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseFragment.
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
        View view = inflater.inflate(R.layout.fragment_courseaddcourse, container, false);

        courseTitle = view.findViewById(R.id.courseNameET);
        courseStart = view.findViewById(R.id.startDateET);
        courseEnd = view.findViewById(R.id.endDateET);
        courseStatus = view.findViewById(R.id.statusET);
        courseNotes = view.findViewById(R.id.notesET);
        save = view.findViewById(R.id.addCourseSaveBTN);
        cancel = view.findViewById(R.id.addCourseCancelBTN);



        //save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.ic_share_black_24dp)
                        .setContentTitle("Test")
                        .setContentText("Notification");


                NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                notificationManager.notify(001, mBuilder.build());



                //saves data
//                saveData();
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.replace(R.id.courseMainFrameLayout, CourseMainActivity.getCourseFragment());
//                fragmentTransaction.commit();

            }
        });

        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                Toast.makeText(getContext(), "Cancel Button clicked", Toast.LENGTH_SHORT).show();

            }
        });


        return view;


    }


    //clear data
    public void clearData() {
        courseTitle.setText("");
        courseStart.setText("");
        courseEnd.setText("");
        courseNotes.setText("");
        courseStatus.setText("");

    }


    //save data
    public void saveData() {
        //course info
        String courseTitleString = courseTitle.getText().toString();
        String courseStatusString = courseStatus.getText().toString();


        String courseNotesString = courseNotes.getText().toString();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate;
        Date endDate;
        try {
            startDate = formatter.parse(courseStart.getText().toString());
            endDate = formatter.parse(courseEnd.getText().toString());
            course = new Course(TermAdapter.getCurrentTermId(), courseTitleString, courseStatusString, courseNotesString, startDate, endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        CourseFragment.getCourseViewModel().insert(course);
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
