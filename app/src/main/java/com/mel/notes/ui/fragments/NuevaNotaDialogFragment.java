package com.mel.notes.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.mel.notes.NuevaNotaDialogViewModel;
import com.mel.notes.R;
import com.mel.notes.db.entities.Note;

public class NuevaNotaDialogFragment extends DialogFragment {
    EditText edttitle;
    EditText edtContenido;
    RadioGroup rdgColor;
    Switch swtFavorito;
    private View view;

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    //la eliminamos por que devolvemos la vista mediante el oncreatedialog
    /*@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nueva_nota_dialog_fragment, container, false);
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar", (dialog, id) -> {
                    String title=edttitle.getText().toString();
                    String contenido=edtContenido.getText().toString();
                    String color="azul";
                    switch (rdgColor.getCheckedRadioButtonId()){
                        case R.id.rdbRojo:
                            color="rojo";
                            break;
                        case R.id.rdbVerde:
                            color="verde";
                            break;
                    }
                    boolean isFavorite=swtFavorito.isChecked();
                    //#2 obtener una instancia cada vez que nos haga falta
                    //Si 2 fragmentos estan trabajando en el mismo activity y ambos invocan al viewmodelproviders van a recibir
                    //exactamente el mismo objeto, estaran accediendo a los mismo datos por que el objeto es una instancia unica
                    //de esto se encarga android de gestionar esa estancia unica como si estuviesemos aplicando un patron singleton
                    //Comunicar al viewmodel el nuevo dato
                    NuevaNotaDialogViewModel mViewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel.class);
                    //VAmos a comunicarle a ese viewmodel que tenemos una nueva nota
                    mViewModel.insertNote(new Note(title,contenido,isFavorite,color));
                    dialog.dismiss();

                })
                .setNegativeButton("Cancelar", (dialog, id) -> {
                    dialog.dismiss();
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //Por que no va a ser devuelto a otro layout raiz, si no que vamos a devolverlo insertado en el mismo dialog
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment, null);
        edttitle=view.findViewById(R.id.edttitle);
        edtContenido=view.findViewById(R.id.edtContenido);
        rdgColor=view.findViewById(R.id.rdgColors);
        swtFavorito=view.findViewById(R.id.swtFavorito);
        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Podemos dejarlo aqui o declararlo arriba #2
        //mViewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel.class);
        // TODO: Use the ViewModel
    }

}
