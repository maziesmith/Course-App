package Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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
    private String noteId;


    public Course() {
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

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
