package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.util.ArrayList;
import java.util.List;

import Model.Mentor;
import ViewModel.MentorViewModel;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.ViewHolder> {

    private Context context;
    private List<Mentor> mentorList = new ArrayList<>();
    public static Mentor currentMentor;
    MentorViewModel mentorViewModel;

    @NonNull
    @Override
    public MentorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mentorview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorAdapter.ViewHolder viewHolder, int i) {
        //gets currently clicked mentor
        currentMentor = mentorList.get(i);
        //sets currently clicked mentor information to textview
        viewHolder.mentorPhone.setText(currentMentor.getMentorPhone());
        viewHolder.mentorName.setText(currentMentor.getMentorName());
        viewHolder.mentorEmail.setText(currentMentor.getMentorEmail());




    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setMentors (List<Mentor> mentors){
        mentorList = mentors;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mentorName;
        private TextView mentorPhone;
        private TextView mentorEmail;
        private Button editMentor;
        private Button deleteMentor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mentorName = itemView.findViewById(R.id.mentorNameTV);
            mentorPhone = itemView.findViewById(R.id.mentorPhoneTV);
            mentorEmail = itemView.findViewById(R.id.mentorEmailTV);
            editMentor = itemView.findViewById(R.id.editMentorIB);
            deleteMentor = itemView.findViewById(R.id.cancelMentorBTN);


            editMentor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Edit Mentor Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            deleteMentor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Delete Mentor Clicked", Toast.LENGTH_SHORT).show();
                }
            });





        }
    }
}
