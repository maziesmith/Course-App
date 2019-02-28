package Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Model.Assessment;
import Model.Course;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AssessmentDao {

    @Insert
    void insertAssessment (Assessment assessment);

    @Update
    void updateAssessment (Assessment assessment);

    @Delete
    void deleteAssessment (Assessment assessment);

    @Query("DELETE FROM assessment_table")
    void deleteAllAssessments();

    @Query("DELETE FROM assessment_table Where courseId=:courseId")
    void deleteAssessmentByCourse(int courseId);

    @Query("SELECT * FROM assessment_table")
    LiveData<List<Assessment>> getAllAssessments();

    @Query("SELECT * FROM assessment_table WHERE courseId=:courseId")
    LiveData<List<Assessment>> getAllAssessmentsForCourse(int courseId);

}
