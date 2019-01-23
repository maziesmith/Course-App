package Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import Model.Course;

@Dao
public interface CourseDao {

    @Insert
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void DeleteCourse(Course course);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM course_table")
    LiveData<Course> getAllCourses();

    @Query("SELECT * FROM course_table WHERE termId IS :termId")
    LiveData<Course> getAllCoursesForTerms(int termId);


}
