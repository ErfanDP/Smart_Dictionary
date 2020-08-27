package com.example.smartdictionary.controller.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdictionary.R;
import com.example.smartdictionary.controller.adapters.WordListAdapter;
import com.example.smartdictionary.model.Word;
import com.example.smartdictionary.repository.WordRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class WordListFragment extends Fragment {
    public static final String TAG_NEW_WORD_DIALOG = "tag_new word_dialog";
    public static final int REQUEST_CODE_NEW_WORD = 1;
    public static final int REQUEST_CODE_EDIT_WORD = 2;


    private WordRepository mRepository;
    private RecyclerView mRecyclerViewWords;
    private FloatingActionButton mButtonAdd;
    private WordListAdapter mAdapter;

    public static WordListFragment newInstance() {
        WordListFragment fragment = new WordListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = WordRepository.getInstance(Objects.requireNonNull(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        findViews(view);
        listeners();
        recyclerViewInit();
        return view;
    }

    private void recyclerViewInit() {
        int rowNumbers = 1;
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rowNumbers = 2;
        }
        mRecyclerViewWords.setLayoutManager(new GridLayoutManager(getContext(),rowNumbers));
        mAdapter = new WordListAdapter(mRepository.getWordsList(), new WordListAdapter.OnRowClick() {
            @Override
            public void onRow(final Word word) {
                DialogNewWord dialog = DialogNewWord.newInstance(word, new DialogNewWord.OnDialogButtonClick() {
                    @Override
                    public void onDelete() {
                        mRepository.deleteWord(word);
                    }
                });
                dialog.setTargetFragment(WordListFragment.this, REQUEST_CODE_EDIT_WORD);
                dialog.show(Objects.requireNonNull(getFragmentManager()), TAG_NEW_WORD_DIALOG);
            }
        });
        mRecyclerViewWords.setAdapter(mAdapter);
    }

    private void listeners() {
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewWord dialog = DialogNewWord.newInstance();
                dialog.setTargetFragment(WordListFragment.this, REQUEST_CODE_NEW_WORD);
                dialog.show(Objects.requireNonNull(getFragmentManager()), TAG_NEW_WORD_DIALOG);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != Activity.RESULT_OK || data == null){
            return;
        }

        if(requestCode == REQUEST_CODE_EDIT_WORD || requestCode ==REQUEST_CODE_NEW_WORD){
            if(data.getBooleanExtra(DialogNewWord.EXTRA_HAS_CHANGED,true)) {
                mAdapter.setWords(mRepository.getWordsList());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void findViews(View view) {
        mRecyclerViewWords = view.findViewById(R.id.word_list);
        mButtonAdd = view.findViewById(R.id.button_add_word);
    }
}