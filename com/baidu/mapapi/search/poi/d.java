package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.search.poi.PoiFilter;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3210a = new int[PoiFilter.IndustryType.values().length];

    static {
        try {
            f3210a[PoiFilter.IndustryType.HOTEL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3210a[PoiFilter.IndustryType.CATER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3210a[PoiFilter.IndustryType.LIFE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}