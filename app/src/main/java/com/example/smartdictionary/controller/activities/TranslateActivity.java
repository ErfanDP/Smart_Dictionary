package com.example.smartdictionary.controller.activities;

import androidx.fragment.app.Fragment;

import com.example.smartdictionary.controller.fragments.TranslateFragment;

public class TranslateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment fragmentCreator() {
        return TranslateFragment.newInstance();
    }
}