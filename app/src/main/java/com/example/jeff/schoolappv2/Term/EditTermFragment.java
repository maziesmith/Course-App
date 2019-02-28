package com.example.jeff.schoolappv2.Term;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.TermAdapter;
import DatePicker.DatePickerFragment;
import Model.Term;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditTermFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditTermFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTermFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private TextView editTerm;
    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    private Button cancel;
    private Button save;

    private Term term;

    //used for datepicker
    private static final int START_CODE = 1;
    private static final int END_CODE = 2;
    private String selectedStart;
    private String selectedEnd;

    public EditTermFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTermFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTermFragment newInstance(String param1, String param2) {
        EditTermFragment fragment = new EditTermFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_termeditterm, container, false);

        editTerm = view.findViewById(R.id.editTermTV);
        termName = view.findViewById(R.id.editTermNameET);
        termStart = view.findViewById(R.id.editTermStartET);
        termEnd = view.findViewById(R.id.editTermEndET);
        save = view.findViewById(R.id.editTermSaveBtn);
        cancel = view.findViewById(R.id.editTermCancelBtn);
        Toast.makeText(getContext(), "Term " + TermAdapter.getCurrentTermId(), Toast.LENGTH_SHORT).show();

        final FragmentManager fmDate = ((AppCompatActivity) getActivity()).getSupportFragmentManager();

        term = TermAdapter.getCurrentTerm();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String termStartString = formatter.format(term.getStartDate());
        String termEndString = formatter.format(term.getEndDate());
        termName.setText(term.getTitle());
        termStart.setText(termStartString);
        termEnd.setText(termEndString);


        //opens datepicker and sets startdate edittext to chose date
        termStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(EditTermFragment.this, START_CODE);
                newFragment.show(fmDate, "datePicker");

            }
        });


        //opens datepicker and sets enddate edittext to chosen date
        termEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(EditTermFragment.this, END_CODE);
                newFragment.show(fmDate, "datePicker");

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();
                getActivity().onBackPressed();


            }
        });

        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goes to previous screen
                getActivity().onBackPressed();
            }
        });


        return view;
    }


    public void updateData() {
        //validation
        boolean cancel = false;


        if (TextUtils.isEmpty(termName.getText())) {
            termName.setError("Mentor name must be entered");
            cancel = true;
        }
        if (TextUtils.isEmpty(termStart.getText())) {
            termStart.setError("Start date must be selected");
            cancel = true;
        }
        if (TextUtils.isEmpty(termEnd.getText())) {
            termEnd.setError("End date must be selected");
            cancel = true;
        }


        if (cancel) {
           // Toast.makeText(getContext(), "Wrong data", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(getContext(), "Save Data", Toast.LENGTH_SHORT).show();
            String termNameString = termName.getText().toString();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate;
            Date endDate;
            try {
                startDate = formatter.parse(termStart.getText().toString());
                endDate = formatter.parse(termEnd.getText().toString());
                term.setTitle(termNameString);

                term.setStartDate(startDate);
                term.setEndDate(endDate);
                TermFragment.getTermViewModel().update(term);


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    //used to set datepicker data to certain editText
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == START_CODE && resultCode == Activity.RESULT_OK) {
            selectedStart = data.getStringExtra("selectedDate");
            termStart.setText(selectedStart);
        } else if (requestCode == END_CODE && resultCode == Activity.RESULT_OK) {
            selectedEnd = data.getStringExtra("selectedDate");
            termEnd.setText(selectedEnd);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
