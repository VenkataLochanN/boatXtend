package com.ido.life.constants;

import com.boat.Xtend.two.R;
import com.ido.common.constant.LanguageRegion;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageCodeHelper {
    public static final int LANGUAGE_CN = 1;
    public static final String LANGUAGE_CR_STR = "Hrvatski";
    public static final int LANGUAGE_CS = 9;
    public static final String LANGUAGE_CS_STR = "Czech";
    public static final String LANGUAGE_DA_STR = "Dansk";
    public static final int LANGUAGE_DE = 4;
    public static final String LANGUAGE_DE_STR = "Deutsch";
    public static final int LANGUAGE_EN = 2;
    public static final String LANGUAGE_EN_STR = "English";
    public static final int LANGUAGE_ES = 7;
    public static final String LANGUAGE_ESN_STR = "Estonian";
    public static final String LANGUAGE_ES_STR = "Español";
    public static final int LANGUAGE_ET = 20;
    public static final int LANGUAGE_FR = 6;
    public static final String LANGUAGE_FR_STR = "Français";
    public static final int LANGUAGE_HI = 17;
    public static final String LANGUAGE_HI_STR = "हिन्दी";
    public static final int LANGUAGE_HU = 10;
    public static final String LANGUAGE_HU_STR = "Magyar";
    public static final int LANGUAGE_IN = 16;
    public static final String LANGUAGE_IN_STR = "Indonesia";
    public static final int LANGUAGE_IT = 5;
    public static final String LANGUAGE_IT_STR = "lingua italiana";
    public static final int LANGUAGE_JA = 11;
    public static final String LANGUAGE_JA_STR = "日本語";
    public static final int LANGUAGE_KO = 12;
    public static final String LANGUAGE_KO_STR = "한국어";
    public static final int LANGUAGE_LT = 18;
    public static final String LANGUAGE_LT_STR = "Lietuva";
    public static final int LANGUAGE_LV = 19;
    public static final String LANGUAGE_LV_STR = "Latvian";
    public static final int LANGUAGE_NL = 15;
    public static final String LANGUAGE_NL_STR = "Nederlands";
    public static final int LANGUAGE_PL = 13;
    public static final String LANGUAGE_PL_STR = "Polski";
    public static final int LANGUAGE_PT = 3;
    public static final String LANGUAGE_PT_STR = "Português";
    public static final String LANGUAGE_RM_STR = "România";
    public static final int LANGUAGE_RU = 8;
    public static final String LANGUAGE_RU_STR = "русский";
    public static final String LANGUAGE_SF_STR = "Slovenského jazyk";
    public static final String LANGUAGE_SV_STR = "Slovenija";
    private static final int LANGUAGE_TW = 0;
    public static final int LANGUAGE_UK = 14;
    public static final String LANGUAGE_UK_STR = "Українська";
    public static final String LANGUAGE_ZH_STR = "简体中文";

    public static String getLanguageCodeByDeviceCode(int i) {
        if (i == 1 || i == 29) {
            return LanguageRegion.ZH;
        }
        if (i == 11) {
            return "lt";
        }
        if (i == 12) {
            return "nl";
        }
        switch (i) {
            case 3:
                return "fr";
            case 4:
                return "de";
            case 5:
                return "it";
            case 6:
                return "es";
            case 7:
                return LanguageRegion.JA;
            case 8:
                return "pl";
            case 9:
                return "cs";
            default:
                switch (i) {
                    case 14:
                        return "hu";
                    case 15:
                        return LanguageRegion.RU;
                    case 16:
                        return "uk";
                    case 17:
                        return "sl";
                    case 18:
                        return "da";
                    case 19:
                        return "hr";
                    case 20:
                        return Constants.INDIA_SERVICE;
                    case 21:
                        return "ko";
                    case 22:
                        return "hi";
                    case 23:
                        return LanguageRegion.PT;
                    default:
                        return "en";
                }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getLanguageCode() {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.constants.LanguageCodeHelper.getLanguageCode():int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getDeviceLanguageCode(java.lang.String r7) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.constants.LanguageCodeHelper.getDeviceLanguageCode(java.lang.String):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getDeviceLanguageCode() {
        /*
            Method dump skipped, instruction units count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.constants.LanguageCodeHelper.getDeviceLanguageCode():int");
    }

    public static String formatLanguageCode2Name(int i) {
        if (i == 11) {
            return "lingua italiana";
        }
        if (i == 12) {
            return "Nederlands";
        }
        if (i != 30) {
            switch (i) {
                case 1:
                    return LanguageUtil.getLanguageText(R.string.language_cn);
                case 2:
                    return LanguageUtil.getLanguageText(R.string.language_en);
                case 3:
                    return LanguageUtil.getLanguageText(R.string.language_fr);
                case 4:
                    return LanguageUtil.getLanguageText(R.string.language_de);
                case 5:
                    return LanguageUtil.getLanguageText(R.string.language_it);
                case 6:
                    return LanguageUtil.getLanguageText(R.string.language_es);
                case 7:
                    return LanguageUtil.getLanguageText(R.string.language_ja);
                case 8:
                    return LanguageUtil.getLanguageText(R.string.language_pl);
                case 9:
                    return LanguageUtil.getLanguageText(R.string.language_cz);
                default:
                    switch (i) {
                        case 14:
                            return "Magyar";
                        case 15:
                            return LanguageUtil.getLanguageText(R.string.language_ru);
                        case 16:
                            return LanguageUtil.getLanguageText(R.string.language_uk);
                        case 17:
                            return "Slovenského jazyk";
                        case 18:
                            return "Dansk";
                        case 19:
                            return "Hrvatski";
                        case 20:
                            return "Indonesia";
                        case 21:
                            return LanguageUtil.getLanguageText(R.string.language_ko);
                        case 22:
                            return LanguageUtil.getLanguageText(R.string.language_hi);
                        case 23:
                            return LanguageUtil.getLanguageText(R.string.language_pt);
                        default:
                            return "";
                    }
            }
        }
        return LanguageUtil.getLanguageText(R.string.language_gr);
    }
}