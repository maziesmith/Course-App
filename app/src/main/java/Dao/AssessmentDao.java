package Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import Model.Assessment;
import Model.Course;

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

    @Query("SELECT * FROM assessment_table")
    LiveData<Assessment> getAllAssessments();

    @Query("SELECT * FROM assessment_table WHERE courseId IS :courseId")
    LiveData<Assessment> getAllAssessmentsForCourse(int courseId);

}
