package com.example.smartdictionary.database;

public class WordDBSchema {
    public static final String NAME = "words.db";
    public static final int VERSION = 1;

    public static class WordTable{

        public static final String NAME = "words";

        public static class COLS{
            public static final String ID = "word_id";
            public static final String UUID = "uuid";
            public static final String ARABIC = "arabic";
            public static final String PERSIAN = "persian";
            public static final String ENGLISH = "english";
            public static final String FRENCH ="french";
        }
    }
}
