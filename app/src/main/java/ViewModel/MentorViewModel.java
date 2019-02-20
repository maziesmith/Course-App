package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import Model.Mentor;
import Repoisitory.AppRepository;

public class MentorViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<Mentor>> allMentors;

    public MentorViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allMentors = appRepository.getAllMentors();
    }



    public LiveData<List<Mentor>> getAllMentors() {
        return appRepository.getAllMentors();

    }

    public void insert(Mentor mentor) {
        appRepository.insertMentor(mentor);
    }

    public LiveData<List<Mentor>> getAllMentorsByCourse(int courseId) {
        return appRepository.getAllMentorsByCourse(courseId);

    }

    public void delete(Mentor mentor) {
        appRepository.deleteMentor(mentor);

    }



    public void update(Mentor mentor) {
        appRepository.updateMentor(mentor);

    }
}
