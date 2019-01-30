package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import Dao.CourseAssessmentDao;
import Model.Assessment;
import Model.CourseAssessment;
import Repoisitory.AppRepository;

public class CourseAssessmentViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<CourseAssessment>> allCourseAssessments;

    public CourseAssessmentViewModel(@NonNull Application application) {
        super(application);
    }



}
