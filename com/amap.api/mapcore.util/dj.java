package com.amap.api.mapcore.util;

import android.text.TextUtils;

/* JADX INFO: compiled from: EngineStyleKeyItem.java */
/* JADX INFO: loaded from: classes.dex */
public class dj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int[] f613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    String f618g;

    public dj(int i, int[] iArr, String str, String str2, String str3) {
        this.f612a = i;
        this.f613b = iArr;
        this.f616e = str;
        this.f617f = str2;
        this.f618g = str3;
        str = TextUtils.isEmpty(str) ? str2 : str;
        this.f614c = -1000;
        if ("regions".equals(str)) {
            this.f614c = 1001;
        } else if ("water".equals(str)) {
            this.f614c = 1002;
        } else if ("buildings".equals(str)) {
            this.f614c = 1003;
        } else if ("roads".equals(str)) {
            this.f614c = 1004;
        } else if ("labels".equals(str)) {
            this.f614c = 1005;
        } else if ("borders".equals(str)) {
            this.f614c = 1006;
        }
        this.f615d = (i * 1000) + iArr.hashCode();
    }
}