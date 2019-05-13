package com.mel.notes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotasActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        navView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_notes:

                return true;
            case R.id.navigation_favorites:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        return false;
    }
}
