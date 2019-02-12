package com.example.jeff.schoolappv2.Term;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.R;

public class TermMainActivity extends AppCompatActivity implements TermFragment.OnFragmentInteractionListener, AddTermFragment.OnFragmentInteractionListener {
    public static final TermFragment sTermFragment = new TermFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termmain);


        // replace fragment with selected fragment on screen
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, sTermFragment);
        fragmentTransaction.commit();

        setTitle("Term");


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
