package com.example.jeff.schoolappv2.Course;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.R;

import static java.security.AccessController.getContext;

public class CourseMainActivity extends AppCompatActivity implements CourseFragment.OnFragmentInteractionListener, AddCourseFragment.OnFragmentInteractionListener {
    private static final CourseFragment sCourseFragment = new CourseFragment();


    public static CourseFragment getCourseFragment() {
        return sCourseFragment;
    }

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
