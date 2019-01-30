package com.example.jeff.schoolappv2.Term;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeff.schoolappv2.R;

import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

import Adapter.TermAdapter;
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

    private TermAdapter termAdapter;
    private TermViewModel termViewModel;
    private Term term;


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
        View view = inflater.inflate(R.layout.termaddterm_fragment, container, false);

        addTerm = view.findViewById(R.id.editTermTV);
        termName = view.findViewById(R.id.termNameET);
        termStart = view.findViewById(R.id.termStartET);
        termEnd = view.findViewById(R.id.termEndET);
        save = view.findViewById(R.id.saveBtn);
        cancel = view.findViewById(R.id.cancelBtn);


        //save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = termName.getText().toString();
                Date start = (Date) termStart.getText();
                Date end = (Date) termEnd.getText();

                term = new Term(name, start, end );
                termViewModel.insert(term);




                //changes screen back to termFragment view
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrameLayout, TermMainActivity.termFragment);
                fragmentTransaction.commit();

            }
        });

        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changes screen back to termFragment view
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrameLayout, TermMainActivity.termFragment);
                fragmentTransaction.commit();


            }
        });

        return view;
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
