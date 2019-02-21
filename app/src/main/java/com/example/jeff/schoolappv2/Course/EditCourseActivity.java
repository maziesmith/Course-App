package com.example.jeff.schoolappv2.Course;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeff.schoolappv2.R;

public class EditCourseActivity extends AppCompatActivity implements EditCourseFragment.OnFragmentInteractionListener {

    private static final EditCourseFragment sEditCourseFragment = new EditCourseFragment();

    public static EditCourseFragment getEditCourseFragment() {
        return sEditCourseFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseedit);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activityEditFrame, sEditCourseFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
