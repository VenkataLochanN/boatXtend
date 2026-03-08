package com.ido.alexa.bean;

import com.ido.alexa.AlexaConstant;

/* JADX INFO: loaded from: classes2.dex */
public enum AlexaLanguageEnum {
    DE_DE(1, AlexaConstant.LANGUAGE_DE_DE),
    EN_AU(2, AlexaConstant.LANGUAGE_EN_AU),
    EN_CA(3, AlexaConstant.LANGUAGE_EN_CA),
    EN_GB(4, AlexaConstant.LANGUAGE_EN_GB),
    EN_IN(5, AlexaConstant.LANGUAGE_EN_IN),
    EN_US(6, AlexaConstant.LANGUAGE_EN_US),
    ES_ES(7, AlexaConstant.LANGUAGE_ES_ES),
    ES_MX(8, AlexaConstant.LANGUAGE_ES_MX),
    ES_US(9, AlexaConstant.LANGUAGE_ES_US),
    FR_CA(10, AlexaConstant.LANGUAGE_FR_CA),
    FR_FR(11, AlexaConstant.LANGUAGE_FR_FR),
    IT_IT(12, AlexaConstant.LANGUAGE_IT_IT),
    JA_JP(13, AlexaConstant.LANGUAGE_JA_JP),
    PT_BR(14, AlexaConstant.LANGUAGE_PT_BR);

    public int mCode;
    public String mLanguage;

    AlexaLanguageEnum(int i, String str) {
        this.mCode = i;
        this.mLanguage = str;
    }

    public static String getmLanguage(int i) {
        AlexaLanguageEnum alexaLanguageEnum = EN_US;
        String str = alexaLanguageEnum.mLanguage;
        AlexaLanguageEnum alexaLanguageEnum2 = DE_DE;
        if (i == alexaLanguageEnum2.mCode) {
            return alexaLanguageEnum2.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum3 = EN_AU;
        if (i == alexaLanguageEnum3.mCode) {
            return alexaLanguageEnum3.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum4 = EN_CA;
        if (i == alexaLanguageEnum4.mCode) {
            return alexaLanguageEnum4.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum5 = EN_GB;
        if (i == alexaLanguageEnum5.mCode) {
            return alexaLanguageEnum5.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum6 = EN_IN;
        if (i == alexaLanguageEnum6.mCode) {
            return alexaLanguageEnum6.mLanguage;
        }
        if (i == alexaLanguageEnum.mCode) {
            return str;
        }
        AlexaLanguageEnum alexaLanguageEnum7 = ES_ES;
        if (i == alexaLanguageEnum7.mCode) {
            return alexaLanguageEnum7.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum8 = ES_MX;
        if (i == alexaLanguageEnum8.mCode) {
            return alexaLanguageEnum8.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum9 = ES_US;
        if (i == alexaLanguageEnum9.mCode) {
            return alexaLanguageEnum9.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum10 = FR_CA;
        if (i == alexaLanguageEnum10.mCode) {
            return alexaLanguageEnum10.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum11 = FR_FR;
        if (i == alexaLanguageEnum11.mCode) {
            return alexaLanguageEnum11.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum12 = IT_IT;
        if (i == alexaLanguageEnum12.mCode) {
            return alexaLanguageEnum12.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum13 = JA_JP;
        if (i == alexaLanguageEnum13.mCode) {
            return alexaLanguageEnum13.mLanguage;
        }
        AlexaLanguageEnum alexaLanguageEnum14 = PT_BR;
        return i == alexaLanguageEnum14.mCode ? alexaLanguageEnum14.mLanguage : str;
    }
}