package com.example.jeff.schoolappv2.Mentor;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeff.schoolappv2.Assessment.AddAssessmentFragment;
import com.example.jeff.schoolappv2.R;

public class AddMentorActivity extends AppCompatActivity implements AddMentorFragment.OnFragmentInteractionListener {
    private static final AddMentorFragment sAddMentorFragment = new AddMentorFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mentor);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.addMentorFrameLayout, sAddMentorFragment);
        ft.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
