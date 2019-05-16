package com.mel.notes;

import android.app.Application;
import android.os.AsyncTask;

import com.mel.notes.db.NotaRoomDatabase;
import com.mel.notes.db.daos.NoteDao;
import com.mel.notes.db.entities.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Es una capa transparente de acceso a la informacion en funcion de si la informacion se va a obtener offline atraves
 * del dao o atraves de peticiones a una API através de internet de manera que esa capa nos permite a nostros
 * no tengamos que estar pendientes de la fuente de datos
 */
public class NoteRepository {
    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allFavorites;

    //Recibe la instancia de la aplicacion
    public NoteRepository(Application application){
        NotaRoomDatabase db=NotaRoomDatabase.getDataBase(application);
        noteDao=db.noteDao();
        //#1
        allNotes=noteDao.getAll();
        allNotes=noteDao.getAllFavorites();
    }


    public LiveData<List<Note>> getAll(){
        /**
         * Otra forma de hacer esto es invocando solo una vez el objeto dao y en la siguientes instanicas automaticamente ya tendriamos ese resultado
         * Mirar #1 return noteDao.getAll();
         */
        return allNotes;
    }

    public LiveData<List<Note>> getAllFavorites(){
        return allFavorites;
    }

    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    //Para el update seria practicamente igual que el insert
    private static class InsertAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao notaDaoAsyncTask;
        public InsertAsyncTask(NoteDao noteDao){
            notaDaoAsyncTask=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            notaDaoAsyncTask.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao notaDaoAsyncTask;
        public UpdateAsyncTask(NoteDao noteDao){
            notaDaoAsyncTask=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            notaDaoAsyncTask.update(notes[0]);
            return null;
        }
    }

    public void update(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }
}
