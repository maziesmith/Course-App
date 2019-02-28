package Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import Converter.Converters;

@Entity(tableName = "assessment_table")
@ForeignKey(entity = Course.class, parentColumns = "id", childColumns = "courseId")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int assessmentId;
    private int courseId;
    private String type;
    private String name;
    @TypeConverters(Converters.class)
    private Date dateDue;


    public Assessment(int courseId, String type, String name, Date dateDue) {
        this.courseId = courseId;
        this.type = type;
        this.name = name;
        this.dateDue = dateDue;

    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(@NonNull Date dueDate) {
        this.dateDue = dueDate;
    }

}
