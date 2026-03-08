package com.baidu.mapsdkvi;

import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3880c;

    public c(NetworkInfo networkInfo) {
        this.f3878a = networkInfo.getTypeName();
        this.f3879b = networkInfo.getType();
        int i = d.f3881a[networkInfo.getState().ordinal()];
        if (i == 1) {
            this.f3880c = 2;
        } else if (i != 2) {
            this.f3880c = 0;
        } else {
            this.f3880c = 1;
        }
    }
}