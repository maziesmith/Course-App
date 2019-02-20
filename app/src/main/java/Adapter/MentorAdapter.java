package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Assessment.EditAssessmentActivity;
import com.example.jeff.schoolappv2.Mentor.EditMentorActivity;
import com.example.jeff.schoolappv2.R;

import java.util.ArrayList;
import java.util.List;

import Model.Mentor;
import ViewModel.MentorViewModel;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.ViewHolder> {

    private Context context;
    private List<Mentor> mentorList = new ArrayList<>();
    private static Mentor sCurrentMentor;
    private MentorViewModel mentorViewModel;

    public MentorAdapter() {

    }


    public static Mentor getCurrentMentor() {
        return sCurrentMentor;
    }

    public MentorViewModel getMentorViewModel() {
        return mentorViewModel;
    }

    public List<Mentor> getMentorList() {
        return mentorList;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mentorview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //gets currently clicked mentor
        sCurrentMentor = mentorList.get(i);
        //sets currently clicked mentor information to textview
        viewHolder.mentorPhone.setText(sCurrentMentor.getMentorPhone());
        viewHolder.mentorName.setText(sCurrentMentor.getMentorName());
        viewHolder.mentorEmail.setText(sCurrentMentor.getMentorEmail());


    }

    @Override
    public int getItemCount() {
        return mentorList.size();
    }

    //updates the mentor list
    public void setMentors(List<Mentor> mentors) {
        mentorList = mentors;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mentorName;
        private TextView mentorPhone;
        private TextView mentorEmail;
        private ImageButton editMentor;
        private ImageButton deleteMentor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            mentorName = itemView.findViewById(R.id.mvMentorNameTV);
            mentorPhone = itemView.findViewById(R.id.mvMentorPhoneTV);
            mentorEmail = itemView.findViewById(R.id.mvMentorEmailTV);
            editMentor = itemView.findViewById(R.id.mvEditMentorIB);
            deleteMentor = itemView.findViewById(R.id.mvDeleteMentorIB);


            editMentor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditMentorActivity.class);
                    intent.putExtra("mentorName", mentorName.getText().toString());
                    intent.putExtra("mentorPhone", mentorPhone.getText().toString());
                    intent.putExtra("mentorEmail", mentorEmail.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });

            deleteMentor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Delete Mentor Clicked", Toast.LENGTH_SHORT).show();
                }
            });


            // on click for when mentor is clicked in list
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }
}
