package com.example.jeff.schoolappv2.Course;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import Model.Course;
import Model.Mentor;
import ViewModel.AssessmentViewModel;
import ViewModel.CourseViewModel;
import ViewModel.MentorViewModel;

public class CourseViewActivity extends AppCompatActivity  implements CourseViewFragment.OnFragmentInteractionListener {
    private static final CourseViewFragment sCourseViewFragment = new CourseViewFragment();


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
