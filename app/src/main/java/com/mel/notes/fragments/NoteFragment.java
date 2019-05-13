package com.mel.notes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mel.notes.R;
import com.mel.notes.adapters.MyNoteRecyclerViewAdapter;
import com.mel.notes.interfaces.NotesInteractionListener;
import com.mel.notes.pojos.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
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
    private NotesInteractionListener notesInteractionListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
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
            noteList.add(new Note("Lista de la compra","Comprar pan tostado y arroz integral",true, android.R.color.white));
            noteList.add(new Note("The standard Lorem Ipsum passage, used since the 1500s","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",false, android.R.color.holo_green_light));
            noteList.add(new Note("Paragraphs","Donec nec justo eget felis facilisis fermentum. Aliquam porttitor mauris sit amet orci. Aenean dignissim pellentesque felis.",true, android.R.color.white));
            noteList.add(new Note("Lists","Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n" +
                    "Aliquam tincidunt mauris eu risus.\n" +
                    "Vestibulum auctor dapibus neque.\n" +
                    "Nunc dignissim risus id metus.",false, android.R.color.holo_blue_light));
            noteList.add(new Note("Cumpleaños (fiesta)","No olvidar las velas y la tarta",true, android.R.color.white));

            noteRecyclerViewAdapter=new MyNoteRecyclerViewAdapter(noteList, notesInteractionListener);
            recyclerView.setAdapter(noteRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NotesInteractionListener) {
            notesInteractionListener = (NotesInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        notesInteractionListener = null;
    }
}
