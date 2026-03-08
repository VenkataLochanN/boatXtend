package com.baidu.location.a;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class f extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f2090a;

    f(d dVar) {
        this.f2090a = dVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.f2090a.a(new File(com.baidu.location.g.k.k() + "/baidu/tempdata", "intime.dat"), "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
    }
}