package Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import Model.Note;

@Dao
public interface NoteDao {

    @Insert
    void insertNote (Note note);

    @Update
    void updateNote (Note note);

    @Delete
    void deleteNote (Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table")
    LiveData<Note> getAllNotes();




}
