package Adapter;

import android.app.AppComponentFactory;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Course.CourseMainActivity;
import com.example.jeff.schoolappv2.R;
import com.example.jeff.schoolappv2.Term.EditTermActivity;
import com.example.jeff.schoolappv2.Term.TermFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import DatePicker.DatePickerFragment;
import Model.Term;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder>  {

    private Context context;
    private List<Term> termLists = new ArrayList<>();
    private static Term sCurrentTerm;
    private static int sCurrentTermId;


    public TermAdapter() {

    }

    public static Term getCurrentTerm() {
        return sCurrentTerm;
    }

    public static int getCurrentTermId() {
        return sCurrentTermId;
    }

    public Context getContext() {
        return context;
    }

    public List<Term> getTermList() {
        return termLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflates termitemlistview to be displayed on screen
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_termview, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //gets position of currentTerm
        sCurrentTerm = termLists.get(i);


        //formats the date to be displayed as string
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String startDateString = formatter.format(sCurrentTerm.getStartDate());
        String endDateString = formatter.format(sCurrentTerm.getEndDate());

        viewHolder.title.setText(sCurrentTerm.getTitle());
        viewHolder.startDate.setText(startDateString);
        viewHolder.endDate.setText(endDateString);

    }




    //returns termLists size

    @Override
    public int getItemCount() {
        return termLists.size();
    }

    //updates data list
    public void setTerms(List<Term> terms) {
        termLists = terms;
        notifyDataSetChanged();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView startDate;
        private TextView endDate;
        private FloatingActionButton floatingActionButton;
        private ImageView editTerm;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            this.title = itemView.findViewById(R.id.termTitleTV);
            this.startDate = itemView.findViewById(R.id.termStartTV);
            this.endDate = itemView.findViewById(R.id.termEndTV);
            this.editTerm = itemView.findViewById(R.id.editTermIV);






            editTerm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditTermActivity.class);
                    intent.putExtra("title", title.getText().toString());
                    intent.putExtra("startDate", startDate.getText().toString());
                    intent.putExtra("endDate", endDate.getText().toString());

                    v.getContext().startActivity(intent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sets getadapterposition to int i
                    int i = getAdapterPosition();
                    //sets currentterm to currently clicked term
                    sCurrentTerm = termLists.get(i);
                    sCurrentTermId = sCurrentTerm.getTermId();
                    context = v.getContext();
                    //creates new intent and passes termId to intent extra to be used in new adapater recyclerview
                    Intent intent = new Intent(context, CourseMainActivity.class);
                    context.startActivity(intent);

                }
            });

            //long click to delete Term and all associated courses/assments/mentors
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //method to delete data from term by termid
                    //course by termid
                    //mentor by courseid
                    //assessment by courseId
                     Intent intent = new Intent(v.getContext(), EditTermActivity.class);
                                        intent.putExtra("title", title.getText().toString());
                                        intent.putExtra("startDate", startDate.getText().toString());
                                        intent.putExtra("endDate", endDate.getText().toString());


                                        v.getContext().startActivity(intent);


                    return true;
                }
            });

        }


    }

}
