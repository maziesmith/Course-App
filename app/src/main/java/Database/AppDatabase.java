package Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import Converter.Converters;
import Dao.AssessmentDao;
import Dao.CourseDao;
import Dao.MentorDao;
import Dao.NoteDao;
import Dao.TermDao;
import Model.Assessment;
import Model.Course;
import Model.Mentor;
import Model.Note;
import Model.Term;

@Database(entities = {Assessment.class, Course.class, Mentor.class, Note.class, Term.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AssessmentDao getAssessmentDao();

    public abstract TermDao getTermDao();

    public abstract CourseDao getCourseDao();

    public abstract MentorDao getMentorDao();

    public abstract NoteDao getNoteDao();


    private static volatile AppDatabase INSTANCE;


   public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
