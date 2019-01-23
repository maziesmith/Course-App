package Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import Converter.Converters;

@Entity(tableName = "course_table")
@ForeignKey(entity = Term.class, parentColumns = "id", childColumns = "termId")
//@ForeignKey(entity = Note.class, parentColumns = "id", childColumns ="noteId")
//@ForeignKey(entity = Mentor.class, parentColumns ="id", childColumns ="mentorId" )
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int courseId;
    @ColumnInfo(name = "termId")
    private int termId;
    private String title;
    private String status;
    @ColumnInfo(name = "mentorId")
    private int mentorId;
    @ColumnInfo(name = "noteId")
    private int noteId;
    @TypeConverters(Converters.class)
    private Date startDate;
    @TypeConverters(Converters.class)
    private Date endDate;


    public Course(int termId, String title, String status, int mentorId, int noteId, Date startDate, Date endDate) {
        this.termId = termId;
        this.title = title;
        this.status = status;
        this.mentorId = mentorId;
        this.noteId = noteId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public int getMentorId() {

        return mentorId;
    }

    public void setMentorId(int mentorId) {

        this.mentorId = mentorId;
    }

    public int getNoteId() {

        return noteId;
    }

    public void setNoteId(int noteId) {

        this.noteId = noteId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
