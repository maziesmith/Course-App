package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.R;

import java.util.List;

import Model.TermItem;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private Context context;

    public static List<TermItem> termLists;

    public TermAdapter(Context context, List<TermItem> termItems) {
        this.context = context;
        this.termLists = termItems;
    }

    public Context getContext() {
        return context;
    }

    public List<TermItem> getTermList() {
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

        TermItem item = termLists.get(i);
        //Set views for viewHolder to view  itms in termitem_fragment
        viewHolder.title.setText(item.getTermName());
        viewHolder.startDate.setText(item.getStartDate());
        viewHolder.endDate.setText(item.getEndDate());

    }




    @Override
    public int getItemCount() {
        //returns termLists size
        return termLists.size();

    }


    //updates the dataset in termlists
    public  void updateList(TermItem term){
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
                    Toast.makeText(context, "Term clicked", Toast.LENGTH_LONG).show();

                }
            });

        }

    }
}
