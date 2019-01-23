package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Course.CourseMainActivity;
import com.example.jeff.schoolappv2.R;

import java.util.List;

import Model.Term;

import static android.support.v4.content.ContextCompat.startActivity;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private Context context;
    private int itemId;
    public static List<Term> termLists;

    public TermAdapter(Context context, List<Term> termItems) {
        this.context = context;
        this.termLists = termItems;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.termitemlistview_fragment, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Term item = termLists.get(i);
        //Set views for viewHolder to view  itms in termitem_fragment
        itemId= item.getTermId();
        viewHolder.title.setText(item.getTitle());
        viewHolder.startDate.setText(item.getStartDate().toString());
        viewHolder.endDate.setText(item.getEndDate().toString());

    }




    @Override
    public int getItemCount() {
        //returns termLists size
        return termLists.size();

    }


    //updates the dataset in termlists
    public  void updateTermList(Term term){
        this.termLists.add(term);
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView startDate;
        TextView endDate;
        FloatingActionButton floatingActionButton;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            this.title = itemView.findViewById(R.id.termTitleTV);
            this.startDate = itemView.findViewById(R.id.termStartTV);
            this.endDate = itemView.findViewById(R.id.termEndTV);




                // allows each item to be individually clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "Term " + itemId + " clicked", Toast.LENGTH_LONG).show();

                    //changes screen to courseactivity associated with clicked term
                    Intent intent = new Intent(getContext(), CourseMainActivity.class);
                    context.startActivity(intent);



                }
            });

        }

    }
}
