package com.example.jeff.schoolappv2.Course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import Adapter.TermAdapter;

public class EditCourseActivity extends AppCompatActivity {

    private EditText courseTitle;
    private EditText courseStart;
    private EditText courseEnd;
    private EditText courseStatus;
    private EditText courseNotes;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseeditcourse);
        courseTitle = findViewById(R.id.editCourseNameET);
        courseStart = findViewById(R.id.editStartDateET);
        courseEnd = findViewById(R.id.editEndDateET);
        courseStatus = findViewById(R.id.editStatusET);
        courseNotes = findViewById(R.id.editNotesET);
        save = findViewById(R.id.editAddCourseSaveBTN);
        cancel = findViewById(R.id.editAddCourseCancelBTN);

        String nameString = getIntent().getStringExtra("courseName");
        String startString = getIntent().getStringExtra("courseStart");
        String endString = getIntent().getStringExtra("courseEnd");
        String statusString = getIntent().getStringExtra("courseStatus");
        String notesString = getIntent().getStringExtra("courseNotes");


        courseTitle.setText(nameString);
        courseStart.setText(startString);
        courseEnd.setText(endString);
        courseStatus.setText(startString);
        courseNotes.setText(notesString);




        //save button should update currently selected course
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EditCourseActivity.this, "Term " + TermAdapter.getCurrentTermId(), Toast.LENGTH_SHORT).show();





                //changes back to previous screen
                onBackPressed();

            }
        });

        //cancels editing currently selected course
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //changes back to previous screen
                onBackPressed();




            }
        });



    }
}
