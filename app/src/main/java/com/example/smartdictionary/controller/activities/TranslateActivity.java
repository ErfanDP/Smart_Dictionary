package com.example.smartdictionary.controller.activities;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.smartdictionary.controller.fragments.TranslateFragment;

public class TranslateActivity extends SingleFragmentActivity {

    public static final String EXTRA_CURRENT_LANGUAGE = "extra_current_language";

    public static Intent newIntent(Context context, String language) {
        Intent intent = new Intent(context,TranslateActivity.class);
        intent.putExtra(EXTRA_CURRENT_LANGUAGE,language);
        return intent;
    }

    @Override
    protected Fragment fragmentCreator() {
        return TranslateFragment.newInstance(getIntent().getStringExtra(EXTRA_CURRENT_LANGUAGE));
    }
}