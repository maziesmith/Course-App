package Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Course.CourseMainActivity;
import com.example.jeff.schoolappv2.R;

import java.util.ArrayList;
import java.util.List;

import Model.Term;

import static android.widget.Toast.*;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private Context context;
    private List<Term> termLists = new ArrayList<>();
    public static Term currentTerm;


    public TermAdapter() {

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
                .inflate(R.layout.termitemlistview_fragment, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //gets position of currentTerm
        currentTerm = termLists.get(i);
        viewHolder.title.setText(currentTerm.getTitle());
        viewHolder.startDate.setText(currentTerm.getStartDate().toString());
        viewHolder.endDate.setText(currentTerm.getEndDate().toString());

    }


    @Override
    public int getItemCount() {
        //returns termLists size
        return termLists.size();
    }

    public void setTerms(List<Term> terms) {
        termLists = terms;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView startDate;
        private TextView endDate;
        LinearLayout termLinearLayout;
        private FloatingActionButton floatingActionButton;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            this.title = itemView.findViewById(R.id.termTitleTV);
            this.startDate = itemView.findViewById(R.id.termStartTV);
            this.endDate = itemView.findViewById(R.id.termEndTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sets getadapterposition to int i
                    int i = getAdapterPosition();
                    //sets currentterm to currently clicked term
                    currentTerm = termLists.get(i);
                    Intent intent = new Intent(getContext(), CourseMainActivity.class);
                    Toast.makeText(v.getContext(), "Term "+ currentTerm.getTermId() + " clicked", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
