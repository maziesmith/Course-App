package com.example.jeff.schoolappv2.Assessment;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeff.schoolappv2.R;

public class AddAssessmentActivity extends AppCompatActivity implements AddAssessmentFragment.OnFragmentInteractionListener {

    private static final AddAssessmentFragment sAddAssessmentFragment = new AddAssessmentFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.addAssessmentFrameLayout, sAddAssessmentFragment);
        ft.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}
