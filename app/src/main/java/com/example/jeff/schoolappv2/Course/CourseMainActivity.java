package com.example.jeff.schoolappv2.Course;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.R;

public class CourseMainActivity extends AppCompatActivity implements CourseFragment.OnFragmentInteractionListener, AddCourseFragment.OnFragmentInteractionListener {
    public static final CourseFragment sCourseFragment = new CourseFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursemain);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.courseMainFrameLayout, sCourseFragment);
        fragmentTransaction.commit();
        // replace fragment with selected fragment on screen

        setTitle("Course");


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
