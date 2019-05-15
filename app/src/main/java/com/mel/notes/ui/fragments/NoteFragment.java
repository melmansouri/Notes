package com.mel.notes.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mel.notes.NuevaNotaDialogViewModel;
import com.mel.notes.R;
import com.mel.notes.ui.adapters.MyNoteRecyclerViewAdapter;
import com.mel.notes.db.entities.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<Note> noteList;
    private MyNoteRecyclerViewAdapter noteRecyclerViewAdapter;
    private NuevaNotaDialogViewModel mViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        //Indicamos que el fragmento tiene un menu de opciones propio
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            //Mostraremos en el listado en formato vertical en caso de que la orientacion sea portrait
            //Hay otras variaciones como detectar si es de dia o de noche, detectar el tamaño de pantalla como en el else etc..
            //El sistema operativo es el que gestiona esta carga
            if (view.getId()==R.id.listPortrait){
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                //Averiguar el tamaño de la pantalla en la que estamos ejecutando la aplicacion
                DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
                //pixeles dependientes de la densidad que tenemos en el ancho de la pantalla
                //density= Densidad de la pantalla
                float dpWidth=displayMetrics.widthPixels/displayMetrics.density;
                //Cada columna de la lista va a ocupar 180 pixeles por pulgada
                int numeroColumnas=(int)(dpWidth/180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas,StaggeredGridLayoutManager.VERTICAL));
            }

            noteList=new ArrayList<>();


            noteRecyclerViewAdapter=new MyNoteRecyclerViewAdapter(getActivity(),noteList);
            recyclerView.setAdapter(noteRecyclerViewAdapter);

            //MEtodo que se va a encargar de decirnos si hay nuevos datos. y en ese medodo
            //refrescaremos la lista de datos
            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        mViewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel.class);
        //Para poder saber si hay nuevas notas, usamos un obeservador.
        //Es un metodo que esta continuamente esperando una notificacion de cambio de datos y en el momento
        //en que eso ocurre vamos a recibir la nueva lista de datos
        mViewModel.getAllNotes().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteRecyclerViewAdapter.setNuevasNotas(notes);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_note_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_note:
                mostrarDialogNuevaNota();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogNuevaNota() {
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        NuevaNotaDialogFragment dialogNuevaNota=new NuevaNotaDialogFragment();
        dialogNuevaNota.show(fragmentManager,"NuevaNotaDialogFragment");
    }
}
