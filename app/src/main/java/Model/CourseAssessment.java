package Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "courseAssessment_table",
        primaryKeys = {"courseId", "assessmentId"})
        //foreignKeys = {@ForeignKey( entity = Course.class, parentColumns = "id", childColumns = "courseId"),
          //      @ForeignKey(entity = Assessment.class, parentColumns = "id", childColumns = "assessmentId")})
public class CourseAssessment {
    public int courseId;
    public int assessmentId;

    public CourseAssessment(int courseId, int assessmentId) {
        this.courseId = courseId;
        this.assessmentId = assessmentId;
    }
}
