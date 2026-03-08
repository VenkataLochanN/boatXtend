package com.baidu.lbsapi.auth;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f1998a;

    f(e eVar) {
        this.f1998a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        e eVar = this.f1998a;
        eVar.a((List<HashMap<String, String>>) eVar.f1996b);
    }
}