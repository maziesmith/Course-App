package com.example.jeff.schoolappv2.Assessment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.R;

public class EditAssessmentActivity extends AppCompatActivity {

    private static final EditAssessmentFragment sEditAssessmentFragment = new EditAssessmentFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activityEditAssessmentFrame, sEditAssessmentFragment);
        ft.commit();


    }


}

