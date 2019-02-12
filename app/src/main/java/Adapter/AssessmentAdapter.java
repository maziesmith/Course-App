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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Assessment;
import ViewModel.AssessmentViewModel;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {


    private Context context;
    private List<Assessment> assessmentList = new ArrayList<>();
    public static Assessment currentAssessment;
    AssessmentViewModel assessmentViewModel;


    @NonNull
    @Override
    public AssessmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_assessmentview, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.ViewHolder viewHolder, int i) {
        //sets currently clicked assessment to currentAssessment
        currentAssessment = assessmentList.get(i);

        viewHolder.assessmentName.setText(currentAssessment.getTitle());
        viewHolder.assessmentType.setText(currentAssessment.getType());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dueDateString = formatter.format(currentAssessment.getDueDate());
        viewHolder.assessmentDueDate.setText(dueDateString);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setAssessments(List<Assessment> assessments) {
        assessmentList = assessmentList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView assessmentName;
        private TextView assessmentType;
        private TextView assessmentDueDate;
        private Button editAssessment;
        private Button deleteAssessment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            assessmentName = itemView.findViewById(R.id.assessmentNameTV);
            assessmentType = itemView.findViewById(R.id.assessmentTypeTV);
            assessmentDueDate = itemView.findViewById(R.id.assessmentDueDateTV);
            editAssessment = itemView.findViewById(R.id.editAssessmentIB);
            deleteAssessment = itemView.findViewById(R.id.deleteAssesmentIB);


            editAssessment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Edit Assessment", Toast.LENGTH_SHORT).show();
                }
            });

            deleteAssessment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Delete Assessment", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
