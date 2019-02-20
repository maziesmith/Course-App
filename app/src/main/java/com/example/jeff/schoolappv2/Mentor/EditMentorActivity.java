package com.example.jeff.schoolappv2.Mentor;

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

public class EditMentorActivity extends AppCompatActivity {

    private EditText mentorName;
    private EditText mentorPhone;
    private EditText mentorEmail;
    private Button cancel;
    private Button save;

    private Mentor mentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentoreditmentor);
        mentorName = findViewById(R.id.editMentorNameET);
        mentorEmail = findViewById(R.id.editMentorEmailET);
        mentorPhone = findViewById(R.id.editMentorPhoneET);
        cancel = findViewById(R.id.editCancelMentorBTN);
        save = findViewById(R.id.editSaveMentorBTN);

        //stores intent data into string to insert into text box
        String name = getIntent().getStringExtra("mentorName");
        String phone = getIntent().getStringExtra("mentorPhone");
        String email = getIntent().getStringExtra("mentorEmail");

        mentorName.setText(name);
        mentorEmail.setText(email);
        mentorPhone.setText(phone);


        //cancel editing mentor
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updates data on click, loads previous fragment back, then removes data from textview
                updateMentor();


                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.courseActivityViewFrame, CourseViewActivity.getCourseViewFragment());
                ft.commit();
                clearText();


            }
        });
    }

    //clears text in editext
    public void clearText() {
        mentorPhone.setText("");
        mentorEmail.setText("");
        mentorName.setText("");
    }

    public void updateMentor() {
        mentor = new Mentor(CourseAdapter.getCurrentCourseId(),
                mentorName.getText().toString(), mentorPhone.getText().toString(),
                mentorEmail.getText().toString());

        MentorViewFragment.getMentorViewModel().update(mentor);
    }
}
