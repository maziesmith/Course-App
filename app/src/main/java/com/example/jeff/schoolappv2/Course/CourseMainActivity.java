package com.example.jeff.schoolappv2.Course;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

public class CourseMainActivity extends AppCompatActivity implements CourseFragment.OnFragmentInteractionListener, AddCourseFragment.OnFragmentInteractionListener {
    public static final CourseFragment courseFragment = new CourseFragment();
    private int termId;

    public int getTermId() {
        return termId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursemain_activity);

        Intent intent = getIntent();
        termId = intent.getExtras().getInt("TERM_ID");



        // replace fragment with selected fragment on screen
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.courseMainFrameLayout, courseFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
