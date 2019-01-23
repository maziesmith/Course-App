package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeff.schoolappv2.Course.CourseMainActivity;
import com.example.jeff.schoolappv2.R;
import com.example.jeff.schoolappv2.Term.TermMainActivity;

import java.util.Date;
import java.util.List;

import Model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context context;
    private List<Course> courseList;
    private int courseId;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.courseitemlistview_fragment, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Course course1 = new Course(1, "Course 1", "in progress", 2, 1, new Date(11 - 23 - 2019), new Date(12 - 23 - 2019));
        Course course2 = new Course(1, "Course 1", "in progress", 2, 1, new Date(11 - 23 - 2019), new Date(12 - 23 - 2019));
        Course course3 = new Course(1, "Course 1", "in progress", 2, 1, new Date(11 - 23 - 2019), new Date(12 - 23 - 2019));

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Course course = courseList.get(i);
        //Set views for viewHolder to view  itms in termitem_fragment
        courseId = course.getCourseId();
        viewHolder.courseStart.setText(course.getStartDate().toString());
        viewHolder.courseTitle.setText(course.getTitle());
        viewHolder.courseEnd.setText(course.getEndDate().toString());

    }


    @Override
    public int getItemCount() {
        return 0;
    }


    //updates the dataset in courselist
    public void updateCourseList(Course course) {
        this.courseList.add(course);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView courseTitle;
        TextView courseStart;
        TextView courseEnd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitleTV);
            courseStart = itemView.findViewById(R.id.courseStartTV);
            courseEnd = itemView.findViewById(R.id.courseEndTV);


            //allows each item to be individually clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Couse item clicked", Toast.LENGTH_SHORT).show();

                    // replace fragment with selected fragment on screen



                }
            });

        }
    }
}
