package com.baidu.platform.core.c;

import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3942a = new int[SearchType.values().length];

    static {
        try {
            f3942a[SearchType.POI_NEAR_BY_SEARCH.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3942a[SearchType.POI_IN_CITY_SEARCH.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3942a[SearchType.POI_IN_BOUND_SEARCH.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}