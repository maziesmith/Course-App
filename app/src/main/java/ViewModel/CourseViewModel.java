package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import Model.Course;
import Repoisitory.AppRepository;

public class CourseViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<Course>> allCourses;


    public CourseViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allCourses = appRepository.getAllCourses();

    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public LiveData<List<Course>>getAllCoursesByTerm(int termId){
        return appRepository.getAllCoursesByTerm(termId);

    }

    public void insert(Course course) {
        appRepository.insertCourse(course);

    }

    public void delete(Course course) {
        appRepository.deleteCourse(course);
    }

    public void update(Course course) {
        appRepository.updateCourse(course);

    }


}
