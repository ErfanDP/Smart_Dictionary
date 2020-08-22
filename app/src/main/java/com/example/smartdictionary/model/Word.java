package com.example.smartdictionary.model;

import java.util.UUID;

public class Word {
    private UUID mUUID;
    private String mArabic;
    private String mPersian;
    private String mEnglish;
    private String mFrench;

    public Word(String arabic, String persian, String english, String french) {
        mUUID = UUID.randomUUID();
        mArabic = arabic;
        mPersian = persian;
        mEnglish = english;
        mFrench = french;
    }

    public Word(UUID UUID, String arabic, String persian, String english, String french) {
        mUUID = UUID;
        mArabic = arabic;
        mPersian = persian;
        mEnglish = english;
        mFrench = french;
    }

    public UUID getUUID() {
        return mUUID;
    }


    public String getStringByLanguage(Languages languages) {
        switch (languages){
            case PERSIAN:
                return mPersian;
            case ENGLISH:
                return mEnglish;
            case FRENCH:
                return mFrench;
            default:
                return mArabic;
        }
    }
}
