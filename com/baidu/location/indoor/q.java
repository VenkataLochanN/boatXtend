package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.p;

/* JADX INFO: loaded from: classes.dex */
class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ p f2704a;

    q(p pVar) {
        this.f2704a = pVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        p pVar = this.f2704a;
        p.b bVarA = pVar.a(pVar.f2697e);
        if (bVarA != null && this.f2704a.f2693a != null) {
            p pVar2 = this.f2704a;
            pVar2.f2697e = pVar2.f2697e.b(bVarA);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!bVarA.b(2.0E-6d) && jCurrentTimeMillis - this.f2704a.k > this.f2704a.f2694b) {
                BDLocation bDLocation = new BDLocation(this.f2704a.f2695c);
                bDLocation.setLatitude(this.f2704a.f2697e.f2701a);
                bDLocation.setLongitude(this.f2704a.f2697e.f2702b);
                this.f2704a.f2693a.a(bDLocation);
                this.f2704a.k = jCurrentTimeMillis;
            }
        }
        this.f2704a.m.postDelayed(this.f2704a.o, 450L);
    }
}