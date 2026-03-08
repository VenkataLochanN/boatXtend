package com.baidu.mapapi.synchronization;

import com.baidu.mapapi.synchronization.SyncCoordinateConverter;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3330a = new int[SyncCoordinateConverter.CoordType.values().length];

    static {
        try {
            f3330a[SyncCoordinateConverter.CoordType.COMMON.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3330a[SyncCoordinateConverter.CoordType.BD09LL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}