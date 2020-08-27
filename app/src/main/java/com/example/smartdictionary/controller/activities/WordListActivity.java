package com.example.smartdictionary.controller.activities;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.smartdictionary.controller.fragments.WordListFragment;

public class WordListActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context context){
        return new Intent(context,WordListActivity.class);
    }

    @Override
    public Fragment fragmentCreator() {
        return WordListFragment.newInstance();
    }


}