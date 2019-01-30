package Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Date;

import Converter.Converters;
import Dao.AssessmentDao;
import Dao.CourseAssessmentDao;
import Dao.CourseDao;
import Dao.CourseMentorDao;
import Dao.MentorDao;
import Dao.TermCourseDao;
import Dao.TermDao;
import Model.Assessment;
import Model.Course;
import Model.CourseAssessment;
import Model.CourseMentor;
import Model.Mentor;
import Model.Term;
import Model.TermCourse;

@Database(entities = {Assessment.class, Course.class, Mentor.class, Term.class, TermCourse.class, CourseAssessment.class, CourseMentor.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AssessmentDao getAssessmentDao();

    public abstract TermDao getTermDao();

    public abstract CourseDao getCourseDao();

    public abstract MentorDao getMentorDao();

    public abstract TermCourseDao getTermCourseDao();

    public abstract CourseAssessmentDao getCourseAssessmentDao();

    public abstract CourseMentorDao getCourseMentorDao();


    private static volatile AppDatabase INSTANCE;


    public static synchronized AppDatabase getDatabase(final Context context) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }


        return INSTANCE;
    }

    //populates the database with test data
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDbAsyncTask(INSTANCE).execute();

        }
    };

    //populate with test data
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final TermDao termDao;
        //private final CourseDao courseDao;
        //private final AssessmentDao assessmentDao;
        //private final MentorDao mentorDao;

        private PopulateDbAsyncTask(AppDatabase appDatabase) {
            this.termDao = appDatabase.getTermDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Term term = new Term("Spring 2018", new Date(2 / 4 / 2019), new Date(4 / 2 / 2019));
            Term term1 = new Term("Summer 2019", new Date(5 / 1 / 2019), new Date(7 / 11 / 2019));
            Term term2 = new Term("Fall 2019", new Date(8 / 2 / 2019), new Date(10 / 12 / 2019));
            Term term3 = new Term("Winter 2019", new Date(11 / 5 / 2019), new Date(1 / 6 / 2020));
            termDao.insertTerm(term);
            termDao.insertTerm(term1);
            termDao.insertTerm(term2);
            termDao.insertTerm(term3);
            return null;

        }
    }

}
