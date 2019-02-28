package com.example.jeff.schoolappv2.Mentor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import Adapter.CourseAdapter;
import Model.Mentor;
import TextValidation.Validator;
import ViewModel.MentorViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMentorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMentorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static MentorViewModel sMentorViewModel;
    private Mentor mentor;
    private Validator validator = new Validator();

    private EditText mentorName;
    private EditText mentorPhone;
    private EditText mentorEmail;
    private Button cancel;
    private Button save;


    private OnFragmentInteractionListener mListener;

    public AddMentorFragment() {
        // Required empty public constructor
    }

    public static MentorViewModel getMentorViewModel() {
        return sMentorViewModel;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMentorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMentorFragment newInstance(String param1, String param2) {
        AddMentorFragment fragment = new AddMentorFragment();
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
        View view = inflater.inflate(R.layout.fragment_mentoraddmentor, container, false);
        mentorName = view.findViewById(R.id.addMentorNameET);
        mentorEmail = view.findViewById(R.id.addMentorEmailET);
        mentorPhone = view.findViewById(R.id.addMentorPhoneET);
        cancel = view.findViewById(R.id.cancelMentorBTN);
        save = view.findViewById(R.id.saveMentorBTN);


        //cancel adding mentor
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                Toast.makeText(getContext(), "Cancel Button", Toast.LENGTH_SHORT).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saves data on click, loads previous fragment back, then removes data from textview
                saveMentor();


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

    //clears text in editext
    public void clearText() {
        mentorPhone.setText("");
        mentorEmail.setText("");
        mentorName.setText("");
    }

    public void saveMentor() {
        //validation
        boolean cancel = false;


        if (TextUtils.isEmpty(mentorName.getText())) {
            mentorName.setError("Mentor name must be entered");
            cancel = true;
        }
        if (TextUtils.isEmpty(mentorEmail.getText())) {
            mentorEmail.setError("mentor email must be entered");
            cancel = true;
        }
        if (TextUtils.isEmpty(mentorPhone.getText())) {
            mentorPhone.setError("Mentor phone must be entered");
            cancel = true;
        }


        if (cancel) {
            //Toast.makeText(getContext(), "Wrong data", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(getContext(), "Save Data", Toast.LENGTH_SHORT).show();
            mentor = new Mentor(CourseAdapter.getCurrentCourseId(),
                    mentorName.getText().toString(), mentorPhone.getText().toString(),
                    mentorEmail.getText().toString());

            MentorViewFragment.getMentorViewModel().insert(mentor);
            getActivity().onBackPressed();

            clearText();
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
