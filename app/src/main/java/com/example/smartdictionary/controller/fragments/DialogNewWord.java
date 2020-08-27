package com.example.smartdictionary.controller.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.DialogFragment;

import com.example.smartdictionary.R;
import com.example.smartdictionary.model.Languages;
import com.example.smartdictionary.model.Word;
import com.example.smartdictionary.repository.WordRepository;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;
import java.util.Objects;

public class DialogNewWord extends DialogFragment {


    public static final String ARG_WORD = "arg_word";
    public static final String ARG_ON_DELETE = "arg_onDelete";
    public static final String EXTRA_HAS_CHANGED = "extra_has_changed";
    private WordRepository mRepository;
    private EditText mTextPersian;
    private EditText mTextArabic;
    private EditText mTextFrench;
    private EditText mTextEnglish;
    private Button mButtonShare;

    private boolean mNewWord;
    private OnDialogButtonClick mOnDialogButtonClick;
    private Word mWord;

    public static DialogNewWord newInstance() {
        return new DialogNewWord();
    }

    public static DialogNewWord newInstance(Word word,OnDialogButtonClick onDialogButtonClick) {
        DialogNewWord fragment =  DialogNewWord.newInstance();
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORD,word);
        args.putSerializable(ARG_ON_DELETE,onDialogButtonClick);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = WordRepository.getInstance(Objects.requireNonNull(getContext()));
        if(getArguments() != null){
            mNewWord = false;
            mOnDialogButtonClick = (OnDialogButtonClick) getArguments().getSerializable(ARG_ON_DELETE);
            mWord = (Word) getArguments().getSerializable(ARG_WORD);
        }else{
            mNewWord = true;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_new_word,null);
        findViews(view);
        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ShareCompat.IntentBuilder.from(Objects.requireNonNull(getActivity()))
                        .setText(getMassageText())
                        .setSubject(getString(R.string.title_of_share))
                        .setType("text/plain").getIntent();
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    dismiss();
                    startActivity(intent);
                }
            }
        });
        if(!mNewWord){
            viewInit();
        }
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext()))
                .setView(view)
                .setNegativeButton(android.R.string.cancel,null);
        if(!mNewWord){
            dialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mOnDialogButtonClick.onDelete();
                    hasChangedResult();
                }
            }).setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog , int which) {
                    mWord.setArabic(mTextArabic.getText().toString());
                    mWord.setEnglish(mTextEnglish.getText().toString());
                    mWord.setPersian(mTextPersian.getText().toString());
                    mWord.setFrench(mTextFrench.getText().toString());
                    mRepository.updateWord(mWord);
                    hasChangedResult();
                }
            }).setTitle(R.string.edit_word);
        }else {
            dialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog , int which) {
                    mRepository.insertWord(new Word(mTextArabic.getText().toString()
                            ,mTextPersian.getText().toString()
                            ,mTextEnglish.getText().toString()
                            ,mTextFrench.getText().toString()
                    ));
                    hasChangedResult();
                }
            }).setTitle(R.string.title_new_word);

        }
         return dialogBuilder.create();
    }

    private String getMassageText() {
        return getString(R.string.word_share_massage
                ,mWord.getStringByLanguage(Languages.PERSIAN)
                ,mWord.getStringByLanguage(Languages.ARABIC)
                ,mWord.getStringByLanguage(Languages.ENGLISH)
                ,mWord.getStringByLanguage(Languages.FRENCH));
    }

    private void viewInit() {
        mTextFrench.setText(mWord.getStringByLanguage(Languages.FRENCH));
        mTextPersian.setText(mWord.getStringByLanguage(Languages.PERSIAN));
        mTextEnglish.setText(mWord.getStringByLanguage(Languages.ENGLISH));
        mTextArabic.setText(mWord.getStringByLanguage(Languages.ARABIC));
        mButtonShare.setVisibility(View.VISIBLE);
    }

    private void hasChangedResult() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HAS_CHANGED,true);
        Objects.requireNonNull(getTargetFragment()).onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,new Intent());
    }


    public interface OnDialogButtonClick extends Serializable {
        void onDelete();
    }
    private void findViews(View view) {
        mTextPersian = view.findViewById(R.id.edit_text_persian);
        mTextArabic = view.findViewById(R.id.edit_text_arabic);
        mTextEnglish = view.findViewById(R.id.edit_text_english);
        mTextFrench = view.findViewById(R.id.edit_text_french);
        mButtonShare = view.findViewById(R.id.button_share);
    }




}
