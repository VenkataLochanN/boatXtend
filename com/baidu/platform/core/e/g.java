package com.baidu.platform.core.e;

import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3947a = new int[SearchType.values().length];

    static {
        try {
            f3947a[SearchType.POI_DETAIL_SHARE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3947a[SearchType.LOCATION_SEARCH_SHARE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}