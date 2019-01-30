package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import Dao.TermCourseDao;
import Model.TermCourse;

public class TermCourseViewModel extends AndroidViewModel {
    private TermCourseDao termCourseDao;
    private LiveData<List<TermCourse>> allTermCourse;

    public TermCourseViewModel(@NonNull Application application) {
        super(application);
    }
}
