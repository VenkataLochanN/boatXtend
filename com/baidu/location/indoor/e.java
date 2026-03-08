package com.baidu.location.indoor;

import android.bluetooth.le.ScanResult;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f2527a;

    e(d dVar) {
        this.f2527a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f2527a.a((HashMap<String, ScanResult>) this.f2527a.l);
        } catch (Exception unused) {
        }
        if (this.f2527a.f2521f != null && this.f2527a.f2521f.isEnabled()) {
            this.f2527a.a(false);
        }
        this.f2527a.l.clear();
    }
}