package com.example.jeff.schoolappv2.Term;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.jeff.schoolappv2.R;

public class EditTermActivity extends AppCompatActivity implements EditTermFragment.OnFragmentInteractionListener {

    public static final EditTermFragment sEditTermFragment = new EditTermFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termedit);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.EditTermActivityFrame, sEditTermFragment);
        ft.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
