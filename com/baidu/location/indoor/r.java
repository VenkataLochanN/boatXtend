package com.baidu.location.indoor;

import com.baidu.location.BDLocation;

/* JADX INFO: loaded from: classes.dex */
class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ p f2705a;

    r(p pVar) {
        this.f2705a = pVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f2705a.j != null && this.f2705a.f2693a != null) {
            BDLocation bDLocation = new BDLocation(this.f2705a.f2695c);
            bDLocation.setLatitude(this.f2705a.j.getLatitude());
            bDLocation.setLongitude(this.f2705a.j.getLongitude());
            this.f2705a.f2693a.a(bDLocation);
        }
        this.f2705a.m.postDelayed(this.f2705a.o, this.f2705a.f2694b);
    }
}