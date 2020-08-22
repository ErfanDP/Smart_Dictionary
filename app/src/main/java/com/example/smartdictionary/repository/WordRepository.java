package com.example.smartdictionary.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartdictionary.controller.database.WordCursorWrapper;
import com.example.smartdictionary.model.ExceptionWordNotFound;
import com.example.smartdictionary.model.Languages;
import com.example.smartdictionary.model.Word;

import static com.example.smartdictionary.controller.database.WordDBSchema.WordTable;

public class WordRepository {
    private static WordRepository sWordRepository;
    private SQLiteDatabase mDataBase;

    private WordRepository(){}

    public WordRepository getInstance(){
        if(sWordRepository == null){
            sWordRepository = new WordRepository();
        }
        return sWordRepository;
    }

    public Word getWord(Languages language,String word) throws ExceptionWordNotFound {
        String selection;
        switch (language){
            case ARABIC:
                selection = WordTable.COLS.ARABIC+"=?";
                break;
            case FRENCH:
                selection = WordTable.COLS.FRENCH+"=?";
                break;
            case ENGLISH:
                selection = WordTable.COLS.ENGLISH+"=?";
                break;
            default:
                selection = WordTable.COLS.PERSIAN+"=?";
                break;
        }
        String[] selectionArgs = new String[]{word};
        WordCursorWrapper cursor = queryWord(selection, selectionArgs);
        if(cursor.getCount() != 0){
            return cursor.getWord();
        }else{
            throw new ExceptionWordNotFound();
        }
    }


    private WordCursorWrapper queryWord(String selection, String[] selectionArgs) {
        Cursor cursor = mDataBase.query(WordTable.NAME
                ,null
                ,selection
                ,selectionArgs
                ,null
                ,null
                ,null);
        return new WordCursorWrapper(cursor);
    }


}
