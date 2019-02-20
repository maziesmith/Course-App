package Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import Converter.Converters;
import Dao.AssessmentDao;
import Dao.CourseDao;
import Dao.MentorDao;
import Dao.TermDao;
import Model.Assessment;
import Model.Course;
import Model.Mentor;
import Model.Term;

@Database(entities = {Assessment.class, Course.class, Mentor.class, Term.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AssessmentDao getAssessmentDao();

    public abstract TermDao getTermDao();

    public abstract CourseDao getCourseDao();

    public abstract MentorDao getMentorDao();


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
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDbAsyncTask(INSTANCE).execute();

        }
    };


}
