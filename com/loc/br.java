package com.loc;

import android.content.Context;

/* JADX INFO: compiled from: WiFiUplateStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class br extends bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f4879a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4880b;

    public br(Context context) {
        this.f4880b = false;
        this.f4879a = context;
        this.f4880b = false;
    }

    @Override // com.loc.bq
    protected final boolean a() {
        return n.q(this.f4879a) == 1 || this.f4880b;
    }
}