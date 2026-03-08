package com.ido.life.module.user.country;

import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public class PinyinComparator implements Comparator<CountryInfo> {
    @Override // java.util.Comparator
    public int compare(CountryInfo countryInfo, CountryInfo countryInfo2) {
        if (countryInfo2.session.equals("#")) {
            return -1;
        }
        if (countryInfo.session.equals("#")) {
            return 1;
        }
        return countryInfo.session.compareTo(countryInfo2.session);
    }
}