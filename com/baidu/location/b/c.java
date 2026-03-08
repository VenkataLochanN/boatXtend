package com.baidu.location.b;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class c extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f2225a;

    c(b bVar) {
        this.f2225a = bVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        try {
            if (i == 1) {
                this.f2225a.f();
            } else if (i != 2) {
            } else {
                this.f2225a.g();
            }
        } catch (Exception unused) {
        }
    }
}