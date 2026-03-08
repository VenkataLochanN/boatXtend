package com.ido.life.util;

import com.ido.common.constant.LanguageRegion;

/* JADX INFO: loaded from: classes3.dex */
public enum LanguageAbbEnum {
    ZH(LanguageRegion.ZH, 1),
    EN("en", 2),
    CS("cs", 9),
    DE("de", 4),
    ES("es", 6),
    FR("fr", 3),
    IT("it", 5),
    JA(LanguageRegion.JA, 7),
    KO("ko", 21),
    NL("nl", 8),
    PT(LanguageRegion.PT, 23),
    RU(LanguageRegion.RU, 15),
    TR("tr", 24);

    private String abb;
    private int language;

    LanguageAbbEnum(String str, int i) {
        this.abb = str;
        this.language = i;
    }

    public String getAbb() {
        return this.abb;
    }

    public int getLanguage() {
        return this.language;
    }
}