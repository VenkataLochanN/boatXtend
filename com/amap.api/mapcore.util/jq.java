package com.amap.api.mapcore.util;

import android.content.Context;

/* JADX INFO: compiled from: WiFiUplateStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jq extends jp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1481a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1482b;

    public jq(Context context, boolean z) {
        this.f1482b = false;
        this.f1481a = context;
        this.f1482b = z;
    }

    @Override // com.amap.api.mapcore.util.jp
    protected boolean c() {
        return gm.r(this.f1481a) == 1 || this.f1482b;
    }
}