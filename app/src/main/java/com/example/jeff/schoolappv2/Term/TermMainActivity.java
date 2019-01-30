package com.example.jeff.schoolappv2.Term;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.R;

public class TermMainActivity extends AppCompatActivity implements TermFragment.OnFragmentInteractionListener, AddTermFragment.OnFragmentInteractionListener {
    public static final TermFragment termFragment = new TermFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termmain_activity);


        // replace fragment with selected fragment on screen
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, termFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    // replace fragment with selected fragment on screen
    public static void replaceFragment(int i, Fragment fragment) {


    }

    //method to add fragment to displayed screen
    public static void addFragment(int i, Fragment fragment) {


    }

}
