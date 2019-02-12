package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jeff.schoolappv2.Course.CourseViewActivity;
import com.example.jeff.schoolappv2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Course;
import ViewModel.CourseViewModel;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context context;
    private List<Course> courseList = new ArrayList<>();
    public static Course sCurrentCourse;
    public static int sCurrentCourseId;
    CourseViewModel courseViewModel;


    public CourseAdapter() {


    }

    public Context getContext() {
        return context;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflates courseItemListview to be displayed on screen
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_courseview, viewGroup, false);
        return new ViewHolder(view);

        //add courses here

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        sCurrentCourse = courseList.get(i);

        ;
        //Set views for viewHolder to view  itms in fragment_termview
        viewHolder.courseTitle.setText(sCurrentCourse.getTitle());
        //
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String startDate = formatter.format(sCurrentCourse.getStartDate());
        String endDate = formatter.format(sCurrentCourse.getEndDate());

        viewHolder.courseStart.setText(startDate);
        viewHolder.courseEnd.setText(endDate);

    }

    //returns courselist size

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    //updates the data list
    public void setCourses(List<Course> courses) {
        courseList = courses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView courseTitle;
        TextView courseStart;
        TextView courseEnd;
        FloatingActionButton floatingActionButton;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseTitle = itemView.findViewById(R.id.courseTitleTV);
            courseStart = itemView.findViewById(R.id.courseStartTV);
            courseEnd = itemView.findViewById(R.id.courseEndTV);


            //allows each item to be individually clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sets i to adapter position
                    int i = getAdapterPosition();
                    //sets current course to currently selected course
                    sCurrentCourse = courseList.get(i);
                    //sets currentcourseid to public static value to be use in fragment
                    sCurrentCourseId = sCurrentCourse.getCourseId();
                    //opens new activity for currently selected course
                    Intent intent = new Intent(v.getContext(), CourseViewActivity.class);
                    v.getContext().startActivity(intent);



                }
            });

            //long click to delete course , mentor , and assessment by courseid
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int i = getAdapterPosition();
                    sCurrentCourse = courseList.get(i);
                    sCurrentCourseId = sCurrentCourse.getCourseId();
                    return true;
                }
            });

        }






    }
}
