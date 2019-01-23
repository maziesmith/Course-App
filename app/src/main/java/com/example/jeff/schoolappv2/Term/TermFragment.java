package com.example.jeff.schoolappv2.Term;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.TermAdapter;
import Model.Term;
import Model.TermItem;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TermFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TermFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TermFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final AddTermFragment addTermFragment = new AddTermFragment();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerView.Adapter adapter;
    private List<Term> termItems;


    public TermFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TermFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TermFragment newInstance(String param1, String param2) {
        TermFragment fragment = new TermFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets menu to true
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // loads main menu item selection into view
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.termmain_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        termItems = new ArrayList<>();

        //insert term sample data here


        Term term1 = new Term("Winter 2019", new Date(11-23-2019),  new Date(12-23-2019));
        Term term2 = new Term("Winter 2019", new Date(11-23-2019),  new Date(12-23-2019));
        Term term3 = new Term("Winter 2019", new Date(11-23-2019),  new Date(12-23-2019));
        Term term4 = new Term("Winter 2019", new Date(11-23-2019),  new Date(12-23-2019));
        Term term5 = new Term("Winter 2019", new Date(11-23-2019),  new Date(12-23-2019));



        termItems.add(term1);
        termItems.add(term2);
        termItems.add(term3);
        termItems.add(term4);
        termItems.add(term5);




        // inflate fragment view
        View view = inflater.inflate(R.layout.termitem_fragment, container, false);


        fab = view.findViewById(R.id.termFab);
        //floating action button on click
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Fab Clicked", Toast.LENGTH_SHORT).show();

                // replaces termFragment view with AddTermFragment
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrameLayout, addTermFragment);
                fragmentTransaction.commit();

            }


        });

        recyclerView = view.findViewById(R.id.termRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new TermAdapter(getContext().getApplicationContext(), termItems));


        return view;

    }


    //menu option items selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.deleteDataItem:
               Toast.makeText(getContext(), "Delete Data", Toast.LENGTH_SHORT).show();
               return true;
           case R.id.insertDataItem:
               Toast.makeText(getContext(), "Insert Data", Toast.LENGTH_SHORT).show();
               return true;
               default:
                   return super.onOptionsItemSelected(item);
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
