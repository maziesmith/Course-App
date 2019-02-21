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
    private static Assessment sCurrentAssessment;
    private AssessmentViewModel assessmentViewModel;
    private static int sAssessmentId;


    public AssessmentAdapter() {

    }


    public Context getContext() {
        return context;
    }

    public List<Assessment> getAssessmentList() {
        return assessmentList;
    }

    public static Assessment getCurrentAssessment() {
        return sCurrentAssessment;
    }

    public static int getAssessmentId() {
        return sAssessmentId;
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
        //gets currently clicked mentor
        sCurrentAssessment = assessmentList.get(i);
        //sets assessmentId
        sAssessmentId = sCurrentAssessment.getAssessmentId();
        //sets currently clicked mentor information to textview
        viewHolder.assessmentName.setText(sCurrentAssessment.getTitle());
        viewHolder.assessmentType.setText(sCurrentAssessment.getType());
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String date = formatter.format(sCurrentAssessment.getDateDue());
        viewHolder.assessmentDate.setText(date);


    }


    @Override
    public int getItemCount() {
        return assessmentList.size();
    }

    //updates the mentor list
    public void setAssessment(List<Assessment> assessments) {
        assessmentList = assessments;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView assessmentName;
        private TextView assessmentType;
        private TextView assessmentDate;
        private ImageButton editAssessment;
        private ImageButton deleteAssessment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            assessmentName = itemView.findViewById(R.id.nameAssessmentTV);
            assessmentDate = itemView.findViewById(R.id.dateAssessmentTV);
            assessmentType = itemView.findViewById(R.id.typeAssessmentTV);
            editAssessment = itemView.findViewById(R.id.itemEditAssessmentIB);
            deleteAssessment = itemView.findViewById(R.id.itemDeleteAssesmentIB);


            editAssessment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(v.getContext(), EditAssessmentActivity.class);
                    v.getContext().startActivity(intent);

                }
            });

            deleteAssessment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //deletes currently selected assessment
                    ;

                }
            });


            // on click for when mentor is clicked in list
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), sCurrentAssessment.getDateDue().toString(), Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}
