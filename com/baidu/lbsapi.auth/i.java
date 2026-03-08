package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class i extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ LBSAuthManager f2004a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    i(LBSAuthManager lBSAuthManager, Looper looper) {
        super(looper);
        this.f2004a = lBSAuthManager;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (a.f1989a) {
            a.a("handleMessage !!");
        }
        LBSAuthManagerListener lBSAuthManagerListener = (LBSAuthManagerListener) LBSAuthManager.f1984f.get(message.getData().getString("listenerKey"));
        if (a.f1989a) {
            a.a("handleMessage listener = " + lBSAuthManagerListener);
        }
        if (lBSAuthManagerListener != null) {
            lBSAuthManagerListener.onAuthResult(message.what, message.obj.toString());
        }
    }
}