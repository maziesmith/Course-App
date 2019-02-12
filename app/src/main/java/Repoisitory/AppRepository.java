package Repoisitory;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import Dao.AssessmentDao;
import Dao.CourseDao;
import Dao.MentorDao;
import Dao.TermDao;
import Database.AppDatabase;
import Model.Assessment;
import Model.Course;
import Model.Mentor;
import Model.Term;

public class AppRepository {

    private TermDao termDao;
    private AssessmentDao assessmentDao;
    private CourseDao courseDao;
    private MentorDao mentorDao;


    private LiveData<List<Term>> allTerms;
    private LiveData<List<Course>> allCourses;
    private LiveData<List<Assessment>> allAssessments;
    private LiveData<List<Mentor>> allMentors;


    // initializes the member variables
    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        termDao = db.getTermDao();
        assessmentDao = db.getAssessmentDao();
        courseDao = db.getCourseDao();
        mentorDao = db.getMentorDao();


        allTerms = termDao.getAllTerms();
        allAssessments = assessmentDao.getAllAssessments();
        allCourses = courseDao.getAllCourses();
        allMentors = mentorDao.getAllMentors();

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

    public LiveData<List<Mentor>> getAllMentors() {
        return allMentors;
    }

    public LiveData<List<Course>> getAllCoursesByTerm(int termId) {
        return courseDao.getAllCoursesForTerms(termId);
    }

    public LiveData<List<Mentor>> getAllMentorsByCourse(int courseId) {
        return mentorDao.getAllMentorsForCourse(courseId);
    }

    public LiveData<List<Assessment>> getAllAssessmentsByCourse(int courseId) {
        return assessmentDao.getAllAssessmentsForCourse(courseId);
    }

    public void insertTerm(Term term) {
        new insertAsyncTaskTerm(termDao).execute(term);
    }

    public void deleteTerm(Term term) {
        new deleteAsyncTaskTerm(termDao).execute(term);
    }

    public void deleteAllTerms() {
        new deleteAllAsyncTaskTerm(termDao).execute();


    }


    public void deleteAllCourses() {
        new deleteAllAsyncTaskCourse(courseDao).execute();
    }

    public void updateTerm(Term term) {
        new updateAsyncTaskTerm(termDao).execute(term);

    }


    public void insertMentor(Mentor mentor) {
        new updateAsyncTaskMentor(mentorDao).execute(mentor);

    }

    public void deleteMentor(Mentor mentor) {
        new deleteAsyncTaskMentor(mentorDao).execute(mentor);
    }


    public void updateMentor(Mentor mentor) {
        new updateAsyncTaskMentor(mentorDao).execute(mentor);
    }


    public void insertCourse(Course course) {
        new insertAsyncTaskCourse(courseDao).execute(course);

    }

    public void deleteCourse(Course course) {
        new deleteAsyncTaskCourse(courseDao).execute(course);
    }

    public void updateCourse(Course course) {
        new updateAsyncTaskCourse(courseDao).execute(course);

    }

    public void insertAssessment(Assessment assessment) {
        new insertAsyncTaskAssessment(assessmentDao).execute(assessment);

    }

    public void deleteAssessment(Assessment assessment) {
        new deleteAsyncTaskAssessment(assessmentDao).execute(assessment);
    }

    public void updateAssessment(Assessment assessment) {
        new updateAsyncTaskAssessment(assessmentDao).equals(assessment);

    }


    //insert term
    private static class insertAsyncTaskTerm extends AsyncTask<Term, Void, Void> {

        private TermDao asyncTaskTermDao;

        insertAsyncTaskTerm(TermDao dao) {
            asyncTaskTermDao = dao;
        }

        @Override
        protected Void doInBackground(final Term... terms) {
            asyncTaskTermDao.insertTerm(terms[0]);
            return null;
        }


    }


    //update term
    private static class updateAsyncTaskTerm extends AsyncTask<Term, Void, Void> {

        private TermDao asyncTaskTermDao;

        updateAsyncTaskTerm(TermDao dao) {
            asyncTaskTermDao = dao;
        }

        @Override
        protected Void doInBackground(Term... terms) {
            asyncTaskTermDao.updateTerm(terms[0]);
            return null;
        }


    }

    //delete term
    private static class deleteAsyncTaskTerm extends AsyncTask<Term, Void, Void> {

        private TermDao asyncTaskTermDao;

        public deleteAsyncTaskTerm(TermDao asyncTaskTermDao) {
            this.asyncTaskTermDao = asyncTaskTermDao;
        }

        @Override
        protected Void doInBackground(Term... terms) {
            asyncTaskTermDao.deleteTerm(terms[0]);

            return null;
        }
    }

    //delete all terms
    private static class deleteAllAsyncTaskTerm extends AsyncTask<Void, Void, Void> {

        private TermDao asyncTaskTermDao;

        public deleteAllAsyncTaskTerm(TermDao termDao) {
            asyncTaskTermDao = termDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskTermDao.deleteAllTerms();
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
            asyncTaskCourseDao.insertCourse(course[0]);
            return null;
        }


    }

    //update course
    private static class updateAsyncTaskCourse extends AsyncTask<Course, Void, Void> {

        private CourseDao asyncTaskCourseDao;

        updateAsyncTaskCourse(CourseDao dao) {
            asyncTaskCourseDao = dao;
        }

        @Override
        protected Void doInBackground(Course... course) {
            asyncTaskCourseDao.updateCourse(course[0]);
            return null;
        }


    }

    // delete course
    private static class deleteAsyncTaskCourse extends AsyncTask<Course, Void, Void> {

        private CourseDao asyncTaskCourseDao;

        public deleteAsyncTaskCourse(CourseDao asyncTaskCourseDao) {
            this.asyncTaskCourseDao = asyncTaskCourseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            asyncTaskCourseDao.DeleteCourse(courses[0]);

            return null;
        }
    }

//    //retreieve coursebytermId
//
//    private static class getCourseByTermId extends AsyncTask<Course, Void,  LiveData<List<Course>>>{
//
//        private CourseDao courseDao;
//
//        public getCourseByTermId(CourseDao courseDao) {
//            this.courseDao = courseDao;
//        }
//
//        @Override
//        protected LiveData<List<Course>> doInBackground(Course... courses) {
//            return courseDao.getAllCoursesForTerms(courses[0].getTermId());
//
//
//        }
//   }


    //delete all terms
    private static class deleteAllAsyncTaskCourse extends AsyncTask<Void, Void, Void> {

        private CourseDao asyncCourseDao;

        public deleteAllAsyncTaskCourse(CourseDao asyncCourseDao) {
            this.asyncCourseDao = asyncCourseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncCourseDao.deleteAllCourses();
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
            asyncTaskAssessmentDao.insertAssessment(assessments[0]);
            return null;
        }

    }

    //update Assessment
    private static class updateAsyncTaskAssessment extends AsyncTask<Assessment, Void, Void> {

        private AssessmentDao asyncTaskAssessmentDao;

        updateAsyncTaskAssessment(AssessmentDao dao) {
            asyncTaskAssessmentDao = dao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {
            asyncTaskAssessmentDao.updateAssessment(assessments[0]);
            return null;
        }

    }

    // delete course
    private static class deleteAsyncTaskAssessment extends AsyncTask<Assessment, Void, Void> {

        private AssessmentDao asyncTaskAssessmentDao;

        public deleteAsyncTaskAssessment(AssessmentDao asyncTaskAssessmentDao) {
            this.asyncTaskAssessmentDao = asyncTaskAssessmentDao;
        }

        @Override
        protected Void doInBackground(Assessment... assessments) {
            asyncTaskAssessmentDao.deleteAssessment(assessments[0]);

            return null;
        }
    }

    //insert mentor
    private static class insertAsyncTaskMentor extends AsyncTask<Mentor, Void, Void> {

        private MentorDao asyncMentorDao;


        public insertAsyncTaskMentor(MentorDao mentorDao) {
            this.asyncMentorDao = mentorDao;
        }

        @Override
        protected Void doInBackground(Mentor... mentors) {
            asyncMentorDao.insertMentor(mentors[0]);

            return null;
        }
    }

    //delete mentor
    private static class deleteAsyncTaskMentor extends AsyncTask<Mentor, Void, Void> {

        private MentorDao asyncMentorDao;


        public deleteAsyncTaskMentor(MentorDao mentorDao) {
            this.asyncMentorDao = mentorDao;
        }

        @Override
        protected Void doInBackground(Mentor... mentors) {
            asyncMentorDao.deleteMentor(mentors[0]);

            return null;
        }

    }

    //update mentor
    private static class updateAsyncTaskMentor extends AsyncTask<Mentor, Void, Void> {

        private MentorDao asyncMentorDao;


        public updateAsyncTaskMentor(MentorDao mentorDao) {
            this.asyncMentorDao = mentorDao;
        }

        @Override
        protected Void doInBackground(Mentor... mentors) {
            asyncMentorDao.updateMentor(mentors[0]);

            return null;
        }
    }


}

