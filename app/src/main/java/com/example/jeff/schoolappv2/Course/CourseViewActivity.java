package com.example.jeff.schoolappv2.Course;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.Assessment.AddAssessmentFragment;
import com.example.jeff.schoolappv2.Assessment.AssessmentViewFragment;
import com.example.jeff.schoolappv2.Mentor.AddMentorFragment;
import com.example.jeff.schoolappv2.Mentor.MentorViewFragment;
import com.example.jeff.schoolappv2.R;

public class CourseViewActivity extends AppCompatActivity implements CourseViewFragment.OnFragmentInteractionListener,
        MentorViewFragment.OnFragmentInteractionListener, AssessmentViewFragment.OnFragmentInteractionListener,
        AddMentorFragment.OnFragmentInteractionListener, AddAssessmentFragment.OnFragmentInteractionListener,
        EditCourseFragment.OnFragmentInteractionListener {
    private static final CourseViewFragment sCourseViewFragment = new CourseViewFragment();


    public static CourseViewFragment getCourseViewFragment() {
        return sCourseViewFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Course View");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetailedview);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.courseActivityViewFrame, sCourseViewFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
