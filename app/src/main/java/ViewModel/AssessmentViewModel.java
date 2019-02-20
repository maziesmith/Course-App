package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import Model.Assessment;
import Repoisitory.AppRepository;

import static android.support.constraint.Constraints.TAG;

//viewmodel class used to interact with UI and DB and to survive configuration changes
public class AssessmentViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<Assessment>> allAssessments;


    public AssessmentViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allAssessments = appRepository.getAllAssessments();
    }

    public LiveData<List<Assessment>> getAllAssessments() {
        return appRepository.getAllAssessments();
    }

    public LiveData<List<Assessment>> getAllAssessmentsByCourse(int courseId) {
        return appRepository.getAllAssessmentsByCourse(courseId);
    }

    public void insert(Assessment assessment) {
        appRepository.insertAssessment(assessment);
    }

    public void update(Assessment assessment) {
        appRepository.updateAssessment(assessment);
    }

    public void delete(Assessment assessment) {
        appRepository.deleteAssessment(assessment);
    }
}
