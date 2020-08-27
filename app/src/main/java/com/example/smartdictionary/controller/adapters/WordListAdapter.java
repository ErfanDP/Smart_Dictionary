package com.example.smartdictionary.controller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdictionary.R;
import com.example.smartdictionary.model.Languages;
import com.example.smartdictionary.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    private List<Word> mWords ;
    private OnRowClick mOnRowClick;
    private static final int TYPE_EVEN = 1;
    private static final int TYPE_ODD = 2;

    public WordListAdapter(List<Word> words, OnRowClick onRowClick) {
        mWords = words;
        mOnRowClick = onRowClick;
    }

    public void setWords(List<Word> words) {
        mWords = words;
    }

    @Override
    public int getItemViewType(int position) {
        if(position %2 ==0){
            return TYPE_EVEN;
        }else
            return TYPE_ODD;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_EVEN) {
            return new WordViewHolder(inflater.inflate(R.layout.list_row_even_word, parent, false));
        } else{
            return new WordViewHolder(inflater.inflate(R.layout.list_row_odd_word, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.bindViews(mWords.get(position));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        private Word mWord;
        private EditText mTextPersian;
        private EditText mTextArabic;
        private EditText mTextEnglish;
        private EditText mTextFrench;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextArabic = itemView.findViewById(R.id.list_edit_text_arabic);
            mTextEnglish = itemView.findViewById(R.id.list_edit_text_english);
            mTextFrench = itemView.findViewById(R.id.list_edit_text_french);
            mTextPersian = itemView.findViewById(R.id.list_edit_text_persian);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRowClick.onRow(mWord);
                }
            });
        }

        public void bindViews(Word word) {
            mWord = word;
            mTextArabic.setText(word.getStringByLanguage(Languages.ARABIC));
            mTextPersian.setText(word.getStringByLanguage(Languages.PERSIAN));
            mTextFrench.setText(word.getStringByLanguage(Languages.FRENCH));
            mTextEnglish.setText(word.getStringByLanguage(Languages.ENGLISH));
        }

    }

    public interface OnRowClick {
        void onRow(Word word);
    }
}
