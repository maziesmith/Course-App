package com.example.jeff.schoolappv2.Term;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeff.schoolappv2.Notification.NotificationUtils;
import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Adapter.TermAdapter;
import DatePicker.DatePickerFragment;
import Model.Term;
import ViewModel.TermViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddTermFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddTermFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTermFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView addTerm;
    private EditText termName;
    private EditText termStart;
    private EditText termEnd;
    private Button cancel;
    private Button save;
    private LiveData<List<Term>> terms;
    private TermAdapter termAdapter;
    private TermViewModel termViewModel;
    private Term term;

    //used for datepicker
    private static final int START_CODE = 1;
    private static final int END_CODE = 2;
    private String selectedStart;
    private String selectedEnd;


    private NotificationUtils notificationUtils;


    private OnFragmentInteractionListener mListener;

    public AddTermFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTermFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTermFragment newInstance(String param1, String param2) {
        AddTermFragment fragment = new AddTermFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        notificationUtils = new NotificationUtils(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_termaddterm, container, false);

        addTerm = view.findViewById(R.id.editTermTV);
        termName = view.findViewById(R.id.termNameET);
        termStart = view.findViewById(R.id.termStartET);
        termEnd = view.findViewById(R.id.termEndET);
        save = view.findViewById(R.id.saveBtn);
        cancel = view.findViewById(R.id.cancelBtn);



        final FragmentManager fmDate = ((AppCompatActivity) getActivity()).getSupportFragmentManager();

        //startdate datepicker
        termStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(AddTermFragment.this, START_CODE);
                newFragment.show(fmDate, "datePicker");

            }
        });

        //enddate datepicker
        termEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(AddTermFragment.this, END_CODE);
                newFragment.show(fmDate, "datePicker");


            }

        });

        //save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

                String name = termName.getText().toString();
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = formatter.parse(termStart.getText().toString());
                    endDate = formatter.parse(termEnd.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                term = new Term(name, startDate, endDate);

                //inserting new term into termviewmodel
                TermFragment.getTermViewModel().insert(term);


                //creates new notification that displays once term is added

//                Notification.Builder nb = notificationUtils
//                        .getAndroidChannelNotification(termName.getText().toString(), "Term Starts: " + startDate + " Term Ends: " + endDate);
//                notificationUtils.getManager().notify(102, nb.build());
//

                //clears current text in view
                clear();
                //changes screen back to termFragment view
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrameLayout, TermMainActivity.getTermFragment());
                fragmentTransaction.commit();

            }
        });

        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears the current text in boxes
                clear();
                //changes screen back to termFragment view
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrameLayout, TermMainActivity.getTermFragment());
                fragmentTransaction.commit();


            }
        });

        return view;
    }


    //method to post datepicker data to specifed edittext

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

    public void clear() {
        termStart.setText("");
        termEnd.setText("");
        termName.setText("");

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
