package com.baidu.mapapi.utils;

import com.baidu.mapapi.utils.CoordinateConverter;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3359a = new int[CoordinateConverter.CoordType.values().length];

    static {
        try {
            f3359a[CoordinateConverter.CoordType.COMMON.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3359a[CoordinateConverter.CoordType.GPS.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3359a[CoordinateConverter.CoordType.BD09LL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f3359a[CoordinateConverter.CoordType.BD09MC.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}