package ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import Dao.TermDao;
import Model.Term;
import Repoisitory.AppRepository;

//view model is used to get data from repositority to interact with UI
public class TermViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<Term>> allTerms;


    public TermViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allTerms = appRepository.getAllTerms();
    }


    public void insert(Term term){
        appRepository.insertTerm(term);
    }

    public void update(Term term){
        appRepository.updateTerm(term);
    }

    public void delete(Term term){
        appRepository.deleteTerm(term);

    }

    public void deleteAll(){
        appRepository.deleteAllTerms();
    }

    public LiveData<List<Term>> getAllTerms() {
        return allTerms;
    }


}
