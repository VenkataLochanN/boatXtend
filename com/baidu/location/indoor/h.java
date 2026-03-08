package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.p;
import com.realsil.sdk.dfu.model.DfuConfig;

/* JADX INFO: loaded from: classes.dex */
class h implements p.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f2586a;

    h(g gVar) {
        this.f2586a = gVar;
    }

    @Override // com.baidu.location.indoor.p.a
    public void a(BDLocation bDLocation) {
        String strG;
        if (this.f2586a.f()) {
            if (this.f2586a.ae != null && System.currentTimeMillis() - this.f2586a.ae.f2556c > 20000 && System.currentTimeMillis() - this.f2586a.ae.f2558e < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                bDLocation.setLocType(61);
                bDLocation.setFloor(null);
                bDLocation.setBuildingID(null);
                bDLocation.setBuildingName(null);
            }
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (com.baidu.location.e.e.a().j() && (strG = com.baidu.location.e.e.a().g()) != null) {
                BDLocation bDLocation3 = new BDLocation(strG);
                bDLocation2.setLocType(61);
                bDLocation2.setSatelliteNumber(bDLocation3.getSatelliteNumber());
                bDLocation2.setSpeed(bDLocation3.getSpeed());
                bDLocation2.setAltitude(bDLocation3.getAltitude());
                bDLocation2.setDirection(bDLocation3.getDirection());
            }
            this.f2586a.a(bDLocation2, 29);
            this.f2586a.af.a(bDLocation);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.f2586a.ae == null || jCurrentTimeMillis - this.f2586a.ae.f2556c <= 30000 || jCurrentTimeMillis - this.f2586a.ae.f2558e <= 30000) {
            return;
        }
        this.f2586a.d();
    }
}