package com.example.jeff.schoolappv2.Assessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.AssessmentAdapter;
import Adapter.CourseAdapter;
import Model.Assessment;
import ViewModel.AssessmentViewModel;

public class EditAssessmentActivity extends AppCompatActivity {

    private EditText assessmentName;
    private EditText assessmentDueDate;
    private RadioButton objective;
    private RadioButton performance;
    private RadioGroup radioGroup;
    private Button save;
    private Button cancel;

    private Assessment assessment;
    private String assessmentType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessmenteditassessment);

        //passes intent extra from assessmentadapter to field for editing
        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String type = getIntent().getStringExtra("type");

        Toast.makeText(this, "Current Assessment Id " + AssessmentAdapter.getAssessmentId(), Toast.LENGTH_SHORT).show();


        assessmentName = findViewById(R.id.editAssessmentNameET);
        assessmentDueDate = findViewById(R.id.editAssessmentDueDateET);
        save = findViewById(R.id.editAssessmentSaveBTN);
        cancel = findViewById(R.id.editAssessmentCancelBTN);
        radioGroup = findViewById(R.id.editRadioGroup);
        objective = findViewById(R.id.editObjectiveRB);
        performance = findViewById(R.id.editPerformanceRB);

        assessmentName.setText(name);
        assessmentDueDate.setText(date);
        if (type.equals("performance")) {
            performance.setChecked(true);
            objective.setChecked(false);
        } else if (type.equals("objective")) {
            objective.setChecked(true);
            performance.setChecked(false);
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    updateData();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                clearData();
                onBackPressed();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                onBackPressed();


            }
        });


    }

    public void clearData() {
        assessmentName.setText("");
        performance.setChecked(false);
        objective.setChecked(false);
        assessmentDueDate.setText("");
    }


    public void updateData() throws ParseException {

        //converts date from string to date format
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dueDate;
        if (objective.isChecked()) {
            assessmentType = "Objective";
        } else if (performance.isChecked()) {
            assessmentType = "performance";
        }
        dueDate = formatter.parse(assessmentDueDate.getText().toString());


        AssessmentAdapter.getCurrentAssessment().setTitle(assessmentName.getText().toString());
        AssessmentAdapter.getCurrentAssessment().setDateDue(dueDate);
        AssessmentAdapter.getCurrentAssessment().setType(assessmentType);


        AssessmentViewFragment.getAssessmentViewModel().update(AssessmentAdapter.getCurrentAssessment());
        clearData();


    }


}

