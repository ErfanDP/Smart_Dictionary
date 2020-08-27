package com.example.smartdictionary.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartdictionary.database.WordCursorWrapper;
import com.example.smartdictionary.database.WordDBBaseHelper;
import com.example.smartdictionary.model.ExceptionWordNotFound;
import com.example.smartdictionary.model.Languages;
import com.example.smartdictionary.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.smartdictionary.database.WordDBSchema.WordTable;

public class WordRepository {
    private static WordRepository sWordRepository;
    private static Context mContext;
    private SQLiteDatabase mDataBase;

    private WordRepository() {
        WordDBBaseHelper database = new WordDBBaseHelper(mContext);
        mDataBase = database.getWritableDatabase();
    }

    public static WordRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sWordRepository == null) {
            sWordRepository = new WordRepository();
        }
        return sWordRepository;
    }

    public Word getWordByUUID(UUID wordId) throws ExceptionWordNotFound {
        String selection = WordTable.COLS.UUID + "=?";
        String[] selectionArgs = new String[]{wordId.toString()};
        try (WordCursorWrapper cursor = queryWord(selection, selectionArgs)) {
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                return cursor.getWord();
            } else {
                throw new ExceptionWordNotFound();
            }
        }
    }

    public Word getWordByLanguage(Languages language, String word) throws ExceptionWordNotFound {
        String selection;
        switch (language) {
            case ARABIC:
                selection = WordTable.COLS.ARABIC + "=?";
                break;
            case FRENCH:
                selection = WordTable.COLS.FRENCH + "=?";
                break;
            case ENGLISH:
                selection = WordTable.COLS.ENGLISH + "=?";
                break;
            default:
                selection = WordTable.COLS.PERSIAN + "=?";
                break;
        }
        String[] selectionArgs = new String[]{word};
        try (WordCursorWrapper cursor = queryWord(selection, selectionArgs)) {
            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                return cursor.getWord();
            } else {
                throw new ExceptionWordNotFound();
            }
        }
    }


    private WordCursorWrapper queryWord(String selection, String[] selectionArgs) {
        Cursor cursor = mDataBase.query(WordTable.NAME
                , null
                , selection
                , selectionArgs
                , null
                , null
                , null);
        return new WordCursorWrapper(cursor);
    }

    public void insertWord(Word word) {
        ContentValues values = getWordContentValues(word);
        mDataBase.insert(WordTable.NAME, null, values);
    }

    private ContentValues getWordContentValues(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordTable.COLS.UUID, word.getUUID().toString());
        values.put(WordTable.COLS.PERSIAN, word.getStringByLanguage(Languages.PERSIAN));
        values.put(WordTable.COLS.ARABIC, word.getStringByLanguage(Languages.ARABIC));
        values.put(WordTable.COLS.FRENCH, word.getStringByLanguage(Languages.FRENCH));
        values.put(WordTable.COLS.ENGLISH, word.getStringByLanguage(Languages.ENGLISH));
        return values;
    }

    public void updateWord(Word word) {
        ContentValues values = getWordContentValues(word);
        String selection = WordTable.COLS.UUID + "=?";
        String[] selectionArgs = new String[]{word.getUUID().toString()};
        mDataBase.update(WordTable.NAME,values,selection,selectionArgs);
    }


    public List<Word> getWordsList() {
        List<Word> words = new ArrayList<>();
        try (WordCursorWrapper cursor = queryWord(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                words.add(cursor.getWord());
                cursor.moveToNext();
            }
        }
        return words;
    }

    public void deleteWord(Word word) {
        String selection = WordTable.COLS.UUID + "=?";
        String[] selectionArgs = new String[]{word.getUUID().toString()};
        mDataBase.delete(WordTable.NAME,selection,selectionArgs);
    }

    public int getWordsCount(){
        WordCursorWrapper cursor =queryWord(null,null);
        return cursor.getCount();
    }
}
