package com.example.jeff.schoolappv2.Mentor;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Course.CourseFragment;
import com.example.jeff.schoolappv2.R;

import java.util.List;

import Adapter.CourseAdapter;
import Adapter.MentorAdapter;
import Model.Mentor;
import ViewModel.MentorViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MentorViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MentorViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MentorViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final MentorAdapter sMentorAdapter = new MentorAdapter();
    private static MentorViewModel sMentorViewModel;


    private RecyclerView mentorRecyclerView;
    private Button backBtn;


    private OnFragmentInteractionListener mListener;

    public MentorViewFragment() {
        // Required empty public constructor
    }

    public static MentorAdapter getMentorAdapter() {
        return sMentorAdapter;
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
     * @return A new instance of fragment MentorViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MentorViewFragment newInstance(String param1, String param2) {
        MentorViewFragment fragment = new MentorViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_mentorview, container, false);

        backBtn = view.findViewById(R.id.mentorViewBackBtn);

        //sets mentor recyclerview

        mentorRecyclerView = view.findViewById(R.id.courseMentorRV);
        mentorRecyclerView.setHasFixedSize(true);
        mentorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mentorRecyclerView.setAdapter(sMentorAdapter);

        sMentorViewModel = ViewModelProviders.of(this).get(MentorViewModel.class);
        sMentorViewModel.getAllMentorsByCourse(CourseAdapter.getCurrentCourseId()).observe(this, new Observer<List<Mentor>>() {

            @Override
            public void onChanged(@Nullable List<Mentor> mentors) {
                sMentorAdapter.setMentors(mentors);
            }

        });


backBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //back button to go back to course
        getActivity().onBackPressed();
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
