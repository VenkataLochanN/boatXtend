package com.baidu.location.indoor;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
class o extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ m f2692a;

    o(m mVar) {
        this.f2692a = mVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        try {
            this.f2692a.l();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}