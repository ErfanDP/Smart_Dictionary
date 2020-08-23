package com.example.smartdictionary.controller.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartdictionary.R;
import com.example.smartdictionary.model.ExceptionWordNotFound;
import com.example.smartdictionary.model.Languages;
import com.example.smartdictionary.model.Word;
import com.example.smartdictionary.repository.WordRepository;

import java.util.Objects;

public class TranslateFragment extends Fragment {

    public static final String TAG_NEW_WORD_DIALOG = "tag_new word_dialog";
    private EditText mInputText;
    private TextView mOutputText;
    private ImageView mImageViewTranslate;
    private Spinner mSpinnerInput;
    private Spinner mSpinnerOutput;
    private Languages mLanguagesInput;
    private Languages mLanguagesOutput;
    private WordRepository mRepository;



    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mRepository = WordRepository.getInstance(Objects.requireNonNull(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        findViews(view);
        SpinnersAdapterInit();
        spinnersListeners();
        listeners();
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_translate,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_add_word:
                DialogNewWord dialog = DialogNewWord.newInstance();
                dialog.show(Objects.requireNonNull(getFragmentManager()), TAG_NEW_WORD_DIALOG);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void listeners() {
        mImageViewTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Word word =  mRepository.getWord(mLanguagesInput,mInputText.getText().toString());
                    mOutputText.setText(word.getStringByLanguage(mLanguagesOutput));
                } catch (ExceptionWordNotFound exceptionWordNotFound) {
                    mOutputText.setText(exceptionWordNotFound.getMessage());
                }
            }
        });
    }

    private void spinnersListeners() {
        mSpinnerInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mLanguagesInput = Languages.PERSIAN;
                        break;
                    case 1:
                        mLanguagesInput = Languages.ARABIC;
                        break;
                    case 2:
                        mLanguagesInput = Languages.FRENCH;
                        break;
                    case 3:
                        mLanguagesInput = Languages.ENGLISH;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mLanguagesOutput = Languages.PERSIAN;
                        break;
                    case 1:
                        mLanguagesOutput = Languages.ARABIC;
                        break;
                    case 2:
                        mLanguagesOutput = Languages.FRENCH;
                        break;
                    case 3:
                        mLanguagesOutput = Languages.ENGLISH;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void SpinnersAdapterInit() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerInput.setAdapter(adapter);
        mSpinnerOutput.setAdapter(adapter);
    }

    private void findViews(View view) {
        mInputText = view.findViewById(R.id.edit_text_in_put);
        mOutputText = view.findViewById(R.id.text_out_put);
        mImageViewTranslate = view.findViewById(R.id.imageView_translate);
        mSpinnerInput = view.findViewById(R.id.spinner_input);
        mSpinnerOutput = view.findViewById(R.id.spinner_out_put);
    }
}