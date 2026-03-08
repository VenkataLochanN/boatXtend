package com.amap.api.offlineservice;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.amap.api.maps.offlinemap.OfflineMapActivity;

/* JADX INFO: compiled from: ServiceModule.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected OfflineMapActivity f1951a = null;

    public abstract void a();

    public abstract void a(View view);

    public boolean b() {
        return true;
    }

    public abstract RelativeLayout c();

    public abstract void d();

    public void e() {
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
    }

    public void a(OfflineMapActivity offlineMapActivity) {
        this.f1951a = offlineMapActivity;
    }

    public void a(Bundle bundle) {
        this.f1951a.showScr();
    }

    public int a(float f2) {
        return this.f1951a != null ? (int) ((f2 * (r0.getResources().getDisplayMetrics().densityDpi / 160.0f)) + 0.5f) : (int) f2;
    }
}