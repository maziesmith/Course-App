package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Assessment.AssessmentViewFragment;
import com.example.jeff.schoolappv2.Assessment.EditAssessmentActivity;
import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Assessment;
import ViewModel.AssessmentViewModel;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {

    private Context context;
    private List<Assessment> assessmentLists = new ArrayList<>();
    private static Assessment sCurrentAssessment;
    private AssessmentViewModel assessmentViewModel;


    public AssessmentAdapter() {

    }


    public Context getContext() {
        return context;
    }

    public List<Assessment> getAssessmentList() {
        return assessmentLists;
    }

    public static Assessment getCurrentAssessment() {
        return sCurrentAssessment;
    }


    public AssessmentViewModel getAssessmentViewModel() {
        return assessmentViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_assessmentview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //gets currently clicked assessment
        sCurrentAssessment = assessmentLists.get(i);

        //sets currently clicked mentor information to textview
        viewHolder.assessmentName.setText(sCurrentAssessment.getName());
        viewHolder.assessmentType.setText(sCurrentAssessment.getType());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                if (sCurrentAssessment.getDateDue()!= null) {
                    String dateDue = formatter.format(sCurrentAssessment.getDateDue());
                    viewHolder.assessmentDate.setText(dateDue);
                }



    }


    @Override
    public int getItemCount() {
        return assessmentLists.size();
    }

    //updates the assessment list
    public void setAssessment(List<Assessment> assessments) {
        assessmentLists = assessments;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView assessmentName;
        private TextView assessmentType;
        private TextView assessmentDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            assessmentName = itemView.findViewById(R.id.nameAssessmentTV);
            assessmentDate = itemView.findViewById(R.id.dateAssessmentTV);
            assessmentType = itemView.findViewById(R.id.typeAssessmentTV);


            // on click for when mentor is clicked in list opens edit Assessment
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = getAdapterPosition();
                    //sets current assessment to currently selected assessment
                    sCurrentAssessment = assessmentLists.get(i);
                    Intent intent = new Intent(v.getContext(), EditAssessmentActivity.class);
                    v.getContext().startActivity(intent);



                }
            });


            //when long click is held assessment is deleted
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AssessmentViewFragment.getAssessmentViewModel().delete(sCurrentAssessment);
                    return true;
                }
            });


        }
    }
}
