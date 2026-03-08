package com.baidu.mapsdkplatform.comapi;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class b extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f3464a;

    b(a aVar) {
        this.f3464a = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        this.f3464a.a(message);
    }
}