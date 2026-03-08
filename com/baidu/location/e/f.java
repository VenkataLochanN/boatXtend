package com.baidu.location.e;

import android.location.OnNmeaMessageListener;

/* JADX INFO: loaded from: classes.dex */
class f implements OnNmeaMessageListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f2418a;

    f(e eVar) {
        this.f2418a = eVar;
    }

    @Override // android.location.OnNmeaMessageListener
    public void onNmeaMessage(String str, long j) {
        if (this.f2418a.b(str)) {
            this.f2418a.a(str);
        }
    }
}