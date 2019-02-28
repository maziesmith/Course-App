package com.example.jeff.schoolappv2.Mentor;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jeff.schoolappv2.Course.CourseViewActivity;
import com.example.jeff.schoolappv2.R;

import Adapter.CourseAdapter;
import Model.Mentor;

public class EditMentorActivity extends AppCompatActivity implements EditMentorFragment.OnFragmentInteractionListener {

    public static EditMentorFragment sEditMentorFragment = new EditMentorFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mentor);


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.activityEditMentorFrame, sEditMentorFragment);
            ft.commit();


        }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
