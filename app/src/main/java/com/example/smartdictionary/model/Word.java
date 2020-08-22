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


    public String getArabic() {
        return mArabic;
    }

    public void setArabic(String arabic) {
        mArabic = arabic;
    }

    public String getPersian() {
        return mPersian;
    }

    public void setPersian(String persian) {
        mPersian = persian;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public String getFrench() {
        return mFrench;
    }

    public void setFrench(String french) {
        mFrench = french;
    }
}
