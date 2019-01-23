package com.example.jeff.schoolappv2.Course;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeff.schoolappv2.R;

public class CourseMainActivity extends AppCompatActivity implements CourseFragment.OnFragmentInteractionListener {
    public static final CourseFragment courseFragment = new CourseFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursemain_activity);


        // replace fragment with selected fragment on screen
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.courseMainFrameLayout, courseFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
