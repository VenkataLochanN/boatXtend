package com.baidu.mapapi.map;

import com.baidu.mapapi.map.MyLocationConfiguration;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ int[] f3039b = new int[com.baidu.mapsdkplatform.comapi.map.aa.values().length];

    static {
        try {
            f3039b[com.baidu.mapsdkplatform.comapi.map.aa.TextureView.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3039b[com.baidu.mapsdkplatform.comapi.map.aa.GLSurfaceView.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f3038a = new int[MyLocationConfiguration.LocationMode.values().length];
        try {
            f3038a[MyLocationConfiguration.LocationMode.COMPASS.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f3038a[MyLocationConfiguration.LocationMode.FOLLOWING.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f3038a[MyLocationConfiguration.LocationMode.NORMAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
    }
}