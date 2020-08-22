package com.example.smartdictionary.controller.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartdictionary.R;

import java.util.Objects;

public class TranslateFragment extends Fragment {

    private EditText mInputText;
    private TextView mOutputText;
    private ImageView mImageViewTranslate;
    private Spinner mSpinnerInput;
    private Spinner mSpinnerOutput;


    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        findViews(view);
        SpinnersAdapterInit();
        mSpinnerInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
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