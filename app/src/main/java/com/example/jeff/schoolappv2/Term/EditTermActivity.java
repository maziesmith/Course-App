package com.example.jeff.schoolappv2.Term;

import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.CourseAdapter;
import Adapter.TermAdapter;
import Model.Term;
import Repoisitory.AppRepository;
import ViewModel.TermViewModel;

public class EditTermActivity extends AppCompatActivity {
    private TextView editTerm;
    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    private Button cancel;
    private Button save;

    private TermViewModel termViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termeditterm);
        editTerm = findViewById(R.id.editTermTV);
        termName = findViewById(R.id.editTermNameET);
        termStart =findViewById(R.id.editTermStartET);
        termEnd =findViewById(R.id.editTermEndET);
        save = findViewById(R.id.editTermSaveBtn);
        cancel = findViewById(R.id.editTermCancelBtn);

        String title = getIntent().getStringExtra("title");
        String startDate = getIntent().getStringExtra("startDate");
        String endDate = getIntent().getStringExtra("endDate");



        termName.setText(title);
        termStart.setText(startDate);
        termEnd.setText(endDate);

        //update term
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Toast.makeText(EditTermActivity.this, "Term " + TermAdapter.getCurrentTermId()  , Toast.LENGTH_SHORT).show();

//                term.setTitle(termName.getText().toString());
//                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//
//                try {
//                    Date end = formatter.parse(termEnd.getText().toString());
//                    Date start = formatter.parse(termStart.getText().toString());
//                    term.setStartDate(start);
//                    term.setEndDate(end);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                TermFragment.getTermViewModel().update(term);
//onBackPressed();
//
//

            }
        });

        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goes to previous screen
                onBackPressed();
            }
        });





    }
}
