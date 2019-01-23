package Repoisitory;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import Dao.AssessmentDao;
import Dao.CourseDao;
import Dao.MentorDao;
import Dao.NoteDao;
import Dao.TermDao;
import Database.AppDatabase;
import Model.Assessment;
import Model.Course;
import Model.Term;

public class AppRepository {
    private TermDao termDao;
    private AssessmentDao assessmentDao;
    private CourseDao courseDao;
    private NoteDao noteDao;
    private MentorDao mentorDao;

    private LiveData<List<Term>> allTerms;
    private LiveData<List<Course>> allCourses;
    private LiveData<List<Assessment>> allAssessments;


    // initializes the member variables
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        termDao = db.getTermDao();
        assessmentDao = db.getAssessmentDao();
        courseDao = db.getCourseDao();
        noteDao = db.getNoteDao();
        mentorDao = db.getMentorDao();

        allTerms = termDao.getAllTerms();
        allAssessments = assessmentDao.getAllAssessments();
        allCourses = courseDao.getAllCourses();

    }

    public LiveData<List<Term>> getAllTerms() {
        return allTerms;
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public LiveData<List<Assessment>> getAllAssessments() {
        return allAssessments;
    }

    public void insertTerm(Term term) {
        new insertAsyncTaskTerm(termDao).execute(term);
    }

    public void insertCourse(Course course) {
        new insertAsyncTaskCourse(courseDao).execute(course);

    }

    public void insertAssessment(Assessment assessment) {
        new insertAsyncTaskAssessment(assessmentDao).execute(assessment);

    }


    //insert term
    private static class insertAsyncTaskTerm extends AsyncTask<Term, Void, Void> {

        private TermDao asyncTaskTermDao;

        insertAsyncTaskTerm(TermDao dao) {
            asyncTaskTermDao = dao;
        }

        @Override
        protected Void doInBackground(Term... terms) {
            asyncTaskTermDao.insertTerm(terms);
            return null;
        }


    }

    //insert course
    private static class insertAsyncTaskCourse extends AsyncTask<Course, Void, Void> {

        private CourseDao asyncTaskCourseDao;

        insertAsyncTaskCourse(CourseDao dao) {
            asyncTaskCourseDao = dao;
        }

        @Override
        protected Void doInBackground(Course... course) {
            asyncTaskCourseDao.insertCourse(course);
            return null;
        }
    }

    //insert Assessment
    private static class insertAsyncTaskAssessment extends AsyncTask<Assessment, Void, Void> {

        private AssessmentDao asyncTaskAssessmentDao;

        insertAsyncTaskAssessment(AssessmentDao dao) {
            asyncTaskAssessmentDao = dao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {
            asyncTaskAssessmentDao.insertAssessment(assessments);
            return null;
        }

    }


}
