package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected a f4199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected ISyncDataListener f4200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f4201c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f4202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4203e;

    public int a() {
        return this.f4203e;
    }

    public void a(int i) {
        this.f4203e = i;
    }

    public void a(ISyncDataListener iSyncDataListener) {
        this.f4200b = iSyncDataListener;
    }

    public void a(a aVar) {
        this.f4199a = aVar;
    }

    protected void b() {
        this.f4199a = null;
        this.f4200b = null;
    }

    public abstract void c();

    public abstract void d();
}