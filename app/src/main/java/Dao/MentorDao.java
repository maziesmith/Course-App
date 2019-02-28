package Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Model.Mentor;

@Dao
public interface MentorDao {


    @Insert
    void insertMentor(Mentor mentor);

    @Update
    void updateMentor(Mentor mentor);

    @Delete
    void deleteMentor(Mentor mentor);

    @Query("DELETE FROM mentor_table")
    void deleteAllMentors();


    @Query("DELETE FROM mentor_table Where courseId=:courseId")
    void deleteMentorByCourse(int courseId);

    @Query("SELECT * FROM mentor_table")
    LiveData<List<Mentor>> getAllMentors();

    @Query("SELECT * FROM mentor_table WHERE courseId=:courseId")
    LiveData<List<Mentor>> getAllMentorsForCourse(int courseId);

}


