package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.g.h;
import com.baidu.location.indoor.m;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
class i implements m.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f2587a;

    i(g gVar) {
        this.f2587a = gVar;
    }

    @Override // com.baidu.location.indoor.m.a
    public synchronized void a(double d2, double d3, double d4, long j) {
        if (this.f2587a.n) {
            this.f2587a.J = 0.4d;
            this.f2587a.ae.a(d2, d3, d4, j);
            double[] dArrA = com.baidu.location.indoor.mapversion.b.a.a(this.f2587a.w, d2, d3, d4);
            if (dArrA != null && dArrA[0] != -1.0d && dArrA[0] == 0.0d) {
                this.f2587a.I = dArrA[2];
                this.f2587a.H = dArrA[1];
                if (this.f2587a.M.size() > 50) {
                    this.f2587a.M.clear();
                }
                this.f2587a.M.add(this.f2587a.new h(this.f2587a.j.d(), d2, d4, d3));
                g.g(this.f2587a);
                try {
                    BDLocation bDLocation = new BDLocation();
                    bDLocation.setLocType(161);
                    bDLocation.setLatitude(dArrA[2]);
                    bDLocation.setLongitude(dArrA[1]);
                    bDLocation.setDirection((float) d4);
                    bDLocation.setTime(this.f2587a.f2531b.format(new Date()));
                    bDLocation.setFloor(this.f2587a.w);
                    bDLocation.setBuildingID(this.f2587a.x);
                    bDLocation.setBuildingName(this.f2587a.z);
                    bDLocation.setParkAvailable(this.f2587a.C);
                    bDLocation.setIndoorLocMode(true);
                    if (this.f2587a.T) {
                        bDLocation.setRadius(8.0f);
                    } else {
                        bDLocation.setRadius(15.0f);
                    }
                    bDLocation.setFusionLocInfo("res", dArrA);
                    bDLocation.setRadius((float) dArrA[5]);
                    bDLocation.setDirection((float) dArrA[6]);
                    bDLocation.setSpeed((float) dArrA[8]);
                    bDLocation.setNetworkLocationType("dr");
                    BDLocation bDLocation2 = new BDLocation(bDLocation);
                    bDLocation2.setNetworkLocationType("dr2");
                    if (this.f2587a.U == null || !this.f2587a.U.c()) {
                        this.f2587a.a(bDLocation2, 21);
                    } else {
                        this.f2587a.U.a(bDLocation2);
                    }
                    if (!this.f2587a.ae.a(bDLocation, dArrA[5], "dr")) {
                        this.f2587a.d();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }
}