package com.mel.notes;

import android.app.Application;

import com.mel.notes.db.entities.Note;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Nos permite intercomunicar fragmentos para transferir datos de unos a otros. Eso simplifica mucho lo que veniamos haciendo
 * de la implementacion de interfaces
 * Es un matron de arquitectura valido y muy recomendable
 * Es como un presenter
 * ViewModel Contiene todo la informacion de una activity o fragment para que no se pierdan al hacer por ejemplo el giro de la pantalla
 * y ademas ahi va toda la logica de negocio y desde ahi se accede a los datos que estan encapsulados en livedata para poder tener los datos siempre actualizados
 */
public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<Note>> allNotes;
    private NoteRepository repository;

    public NuevaNotaDialogViewModel(Application application){
        super(application);
        repository=new NoteRepository(application);
        allNotes=repository.getAll();
    }

    //El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<Note>> getAllNotes(){return allNotes;}

    //El fragment que inserte una nueva nota, debera comunicarlo a este viewmodel
    public void insertNote(Note note){
        repository.insert(note);
    }

    public void updateNote(Note note){
        repository.update(note);
    }
}
