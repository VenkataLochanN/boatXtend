package com.baidu.mapsdkplatform.comapi.map;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class s extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ r f3617a;

    s(r rVar) {
        this.f3617a = rVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (r.f3613c != null) {
            this.f3617a.f3615d.a(message);
        }
    }
}