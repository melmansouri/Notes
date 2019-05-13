package com.mel.notes.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mel.notes.R;
import com.mel.notes.fragments.NoteFragment;
import com.mel.notes.interfaces.NotesInteractionListener;
import com.mel.notes.pojos.Note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotasActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NotesInteractionListener {

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private Fragment f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        navView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new NoteFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_notes:
                f=new NoteFragment();
                return true;
            case R.id.navigation_favorites:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        if (f!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
        }
        return false;
    }

    @Override
    public void editNoteClick(Note note) {

    }

    @Override
    public void removeNoteClick(Note note) {

    }

    @Override
    public void favoriteNoteClick(Note note) {

    }
}
