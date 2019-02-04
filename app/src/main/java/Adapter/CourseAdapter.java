package Adapter;

import android.content.Context;
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

import java.util.List;

import Model.Course;
import ViewModel.CourseViewModel;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context context;
    private List<Course> courseList;
    private Course currentCourse;
    CourseMainActivity courseMainActivity = new CourseMainActivity();
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
                .inflate(R.layout.courseitemlistview_fragment, viewGroup, false);
        return new ViewHolder(view);

        //add courses here


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int termId = courseMainActivity.getTermId();
        courseViewModel.getAllCoursesByTerm(termId);
        currentCourse = courseList.get(termId);

        ;
        //Set views for viewHolder to view  itms in termitem_fragment




        viewHolder.courseStart.setText(currentCourse.getStartDate().toString());
        viewHolder.courseTitle.setText(currentCourse.getTitle());
        viewHolder.courseEnd.setText(currentCourse.getEndDate().toString());

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
                    Course currentCourse = courseList.get(i);
                    Toast.makeText(getContext(), "Course " + currentCourse + " clicked", Toast.LENGTH_SHORT).show();

                    // replace fragment with selected fragment on screen


                }
            });

        }
    }
}
