package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import Model.Assessment;
import Model.Course;
import Model.Term;
import Repoisitory.AppRepository;

public class AppViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private LiveData<List<Term>> allTerms;
    private LiveData<List<Course>> allCourses;
    private LiveData<List<Assessment>> allAssessments;


    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allTerms = appRepository.getAllTerms();
        allCourses = appRepository.getAllCourses();
        allAssessments = appRepository.getAllAssessments();

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

    public void insert (Term term){
        appRepository.insertTerm(term);
    }

    public void insert (Course course){
        appRepository.insertCourse(course);
    }
    public void insert (Assessment assessment){
        appRepository.insertAssessment(assessment);
    }
}
