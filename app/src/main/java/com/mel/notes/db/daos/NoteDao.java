package com.mel.notes.db.daos;

import com.mel.notes.db.entities.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Patron dao permite definir una capa en la que vamos a tener todas las operaciones que se van a ejecutar sobre una tabla de nuestra bd
 */
@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Query("Delete From notas")
    void deleteAll();

    @Query("Delete From notas where id = :id")
    void deleteById(int id);


    @Query("Select * from notas order by title ASC")
    //List<Note> getAll(); En vez de decir que se va a devolver una lista estatica,mutable no va a modificarse le indicamos que vamos a trabajar con livedata sobre esa lista
    //Asi los convertimos a datos dinamicos
    LiveData<List<Note>> getAll();

    @Query("Select * from notas where favorite = 1 order by title ASC")
    LiveData<List<Note>> getAllFavorites();
}
