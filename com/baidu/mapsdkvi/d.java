package com.baidu.mapsdkvi;

import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f3881a = new int[NetworkInfo.State.values().length];

    static {
        try {
            f3881a[NetworkInfo.State.CONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f3881a[NetworkInfo.State.CONNECTING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f3881a[NetworkInfo.State.DISCONNECTED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f3881a[NetworkInfo.State.DISCONNECTING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f3881a[NetworkInfo.State.SUSPENDED.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}