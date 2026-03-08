package com.baidu.location.b;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class i extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f2262a;

    i(h hVar) {
        this.f2262a = hVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 1) {
            return;
        }
        this.f2262a.d();
    }
}