package com.baidu.platform.core.d;

import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3946a = new int[SearchType.values().length];

    static {
        try {
            f3946a[SearchType.TRANSIT_ROUTE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3946a[SearchType.DRIVE_ROUTE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3946a[SearchType.WALK_ROUTE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}