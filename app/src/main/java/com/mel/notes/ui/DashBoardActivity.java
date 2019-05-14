package com.mel.notes.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mel.notes.R;
import com.mel.notes.ui.fragments.NoteFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

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
}
