package com.example.smartdictionary.model;

import java.io.Serializable;
import java.util.UUID;

public class Word implements Serializable {
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

    public void setArabic(String arabic) {
        mArabic = arabic;
    }

    public void setPersian(String persian) {
        mPersian = persian;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public void setFrench(String french) {
        mFrench = french;
    }
}
