package com.baidu.mapsdkplatform.comapi;

import com.baidu.mapsdkplatform.comapi.NativeLoader;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3481a = new int[NativeLoader.a.values().length];

    static {
        try {
            f3481a[NativeLoader.a.ARM64.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3481a[NativeLoader.a.ARMV7.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3481a[NativeLoader.a.ARMEABI.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f3481a[NativeLoader.a.X86_64.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f3481a[NativeLoader.a.X86.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}