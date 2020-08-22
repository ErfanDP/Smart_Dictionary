package com.example.smartdictionary.controller.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.smartdictionary.model.Word;

import java.util.UUID;

import static com.example.smartdictionary.controller.database.WordDBSchema.*;

public class WordCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord(){
        String uuid = getString(getColumnIndex(WordTable.COLS.UUID));
        String persian = getString(getColumnIndex(WordTable.COLS.PERSIAN));
        String arabic = getString(getColumnIndex(WordTable.COLS.ARABIC));
        String french = getString(getColumnIndex(WordTable.COLS.FRENCH));
        String english = getString(getColumnIndex(WordTable.COLS.ENGLISH));
        return new Word(UUID.fromString(uuid),arabic,persian,english,french);
    }

}
