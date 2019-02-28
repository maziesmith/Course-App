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

import Adapter.MentorAdapter;
import Model.Mentor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditMentorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditMentorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMentorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText mentorName;
    private EditText mentorPhone;
    private EditText mentorEmail;
    private Button cancel;
    private Button save;

    private Mentor mentor;

    public EditMentorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditMentorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditMentorFragment newInstance(String param1, String param2) {
        EditMentorFragment fragment = new EditMentorFragment();
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
        View view = inflater.inflate(R.layout.fragment_mentoreditmentor, container, false);
        mentorName = view.findViewById(R.id.editMentorNameET);
        mentorEmail = view.findViewById(R.id.editMentorEmailET);
        mentorPhone = view.findViewById(R.id.editMentorPhoneET);
        cancel = view.findViewById(R.id.editCancelMentorBTN);
        save = view.findViewById(R.id.editSaveMentorBTN);

        mentor = MentorAdapter.getCurrentMentor();

        mentorName.setText(mentor.getMentorName());
        mentorEmail.setText(mentor.getMentorEmail());
        mentorPhone.setText(mentor.getMentorPhone());

        //cancel editing mentor
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                getActivity().onBackPressed();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updates data on click, loads previous fragment back, then removes data from textview
                updateMentor();
                Toast.makeText(v.getContext(), "Mentor Id " + MentorAdapter.getCurrentMentor().getMentorId(), Toast.LENGTH_SHORT).show();




            }
        });


        return view;

    }

    //clears text in editext
    public void clearText() {
        mentorPhone.setText("");
        mentorEmail.setText("");
        mentorName.setText("");
    }

    public void updateMentor() {


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
           // Toast.makeText(getContext(), "Wrong data", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(getContext(), "Save Data", Toast.LENGTH_SHORT).show();
            mentor.setMentorName(mentorName.getText().toString());
            mentor.setMentorEmail(mentorEmail.getText().toString());
            mentor.setMentorPhone(mentorPhone.getText().toString());


            MentorViewFragment.getMentorViewModel().update(mentor);
            getActivity().onBackPressed();

            clearText();
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
