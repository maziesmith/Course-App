package Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "termCourse_table",
      primaryKeys = {"termId", "courseId"})
//foreignKeys = {@ForeignKey( entity = Term.class, parentColumns = "id", childColumns = "termId"),
  //              @ForeignKey(entity = Course.class, parentColumns = "id", childColumns = "courseId")})
public class TermCourse {
    public int termId;
    public int courseId;

    public TermCourse(int termId, int courseId) {
        this.termId = termId;
        this.courseId = courseId;
    }
}
