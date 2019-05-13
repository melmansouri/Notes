package com.mel.notes.activities;

import android.content.Intent;
import android.os.Bundle;

import com.mel.notes.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @OnClick({R.id.btnlogin})
    public void click(){
        startActivity(new Intent(this, NotasActivity.class));
    }
}
