package Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "courseMentor_table",
        primaryKeys = {"courseId", "mentorId"})
        //foreignKeys = {@ForeignKey( entity = Course.class, parentColumns = "id", childColumns = "courseId"),
          //      @ForeignKey(entity = Mentor.class, parentColumns = "id", childColumns = "mentorId")})
public class CourseMentor {
    private int courseId;
    private int mentorId;

    public CourseMentor(int courseId, int mentorId) {
        this.courseId = courseId;
        this.mentorId = mentorId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
}
