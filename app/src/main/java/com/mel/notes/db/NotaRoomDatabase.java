package com.mel.notes.db;

import android.content.Context;

import com.mel.notes.db.daos.NoteDao;
import com.mel.notes.db.entities.Note;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Esta clase solo se debe de definir una vez
 * Y no debemos instanciar la bd mas que en aquellos sitios en que lo necesitemos y a través del metodo
 * getDataBase que centraliza la generacion de una instancia unica para todo el proyecto
 */
@Database(entities = {Note.class},version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    //Asi lo indica la documentacion
    private static volatile NotaRoomDatabase INSTANCE;

    //Final para no modificar su valor
    public static NotaRoomDatabase getDataBase(final Context context){
        if (INSTANCE == null){
            synchronized (NotaRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,NotaRoomDatabase.class,"notes_database").build();
                }
            }
        }

        return INSTANCE;
    }
}
