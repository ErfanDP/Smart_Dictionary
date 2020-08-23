package com.example.smartdictionary.controller.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.smartdictionary.R;
import com.example.smartdictionary.model.Word;
import com.example.smartdictionary.repository.WordRepository;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class DialogNewWord extends DialogFragment {


    private WordRepository mRepository;
    private EditText mTextPersian;
    private EditText mTextArabic;
    private EditText mTextFrench;
    private EditText mTextEnglish;

    public static DialogNewWord newInstance() {
        return new DialogNewWord();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = WordRepository.getInstance(Objects.requireNonNull(getContext()));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_new_word,null);
        findViews(view);
        return new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext()))
                .setTitle(R.string.title_new_word)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog , int which) {
                        mRepository.insertWord(new Word(mTextArabic.getText().toString()
                                ,mTextPersian.getText().toString()
                                ,mTextEnglish.getText().toString()
                                ,mTextFrench.getText().toString()
                        ));
                    }
                })
                .setView(view)
                .setNegativeButton(android.R.string.cancel,null)
                .create();
    }

    private void findViews(View view) {
        mTextPersian = view.findViewById(R.id.edit_text_persian);
        mTextArabic = view.findViewById(R.id.edit_text_arabic);
        mTextEnglish = view.findViewById(R.id.edit_text_english);
        mTextFrench = view.findViewById(R.id.edit_text_french);
    }
}
