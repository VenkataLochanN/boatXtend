package com.baidu.mapsdkplatform.comapi.synchronization.b;

import com.baidu.mapsdkplatform.comapi.synchronization.c.c;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3661a = new int[c.a.values().length];

    static {
        try {
            f3661a[c.a.SUCCESS.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3661a[c.a.INNER_ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3661a[c.a.SERVER_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f3661a[c.a.NETWORK_ERROR.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f3661a[c.a.REQUEST_ERROR.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}