package com.example.smartdictionary.controller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.smartdictionary.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment fragmentCreator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_contianer);
        if(fragment == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_contianer,fragmentCreator())
                    .commit();
        }
    }
}