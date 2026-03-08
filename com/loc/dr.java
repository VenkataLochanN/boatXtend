package com.loc;

import com.amap.api.location.AMapLocation;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: LocFilter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ds f5037a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f5038b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f5039c = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f5044h = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f5040d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f5041e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    AMapLocation f5042f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    long f5043g = 0;

    private ds b(ds dsVar) {
        int i;
        if (ep.a(dsVar)) {
            if (!this.f5044h || !ei.a(dsVar.getTime())) {
                i = this.f5040d;
            } else if (dsVar.getLocationType() == 5 || dsVar.getLocationType() == 6) {
                i = 4;
            }
            dsVar.setLocationType(i);
        }
        return dsVar;
    }

    public final AMapLocation a(AMapLocation aMapLocation) {
        if (!ep.a(aMapLocation)) {
            return aMapLocation;
        }
        long jB = ep.b() - this.f5043g;
        this.f5043g = ep.b();
        if (jB > BootloaderScanner.TIMEOUT) {
            return aMapLocation;
        }
        AMapLocation aMapLocation2 = this.f5042f;
        if (aMapLocation2 == null) {
            this.f5042f = aMapLocation;
            return aMapLocation;
        }
        if (1 != aMapLocation2.getLocationType() && !"gps".equalsIgnoreCase(this.f5042f.getProvider())) {
            this.f5042f = aMapLocation;
            return aMapLocation;
        }
        if (this.f5042f.getAltitude() == aMapLocation.getAltitude() && this.f5042f.getLongitude() == aMapLocation.getLongitude()) {
            this.f5042f = aMapLocation;
            return aMapLocation;
        }
        long jAbs = Math.abs(aMapLocation.getTime() - this.f5042f.getTime());
        if (30000 < jAbs) {
            this.f5042f = aMapLocation;
            return aMapLocation;
        }
        if (ep.a(aMapLocation, this.f5042f) > (((this.f5042f.getSpeed() + aMapLocation.getSpeed()) * jAbs) / 2000.0f) + ((this.f5042f.getAccuracy() + aMapLocation.getAccuracy()) * 2.0f) + 3000.0f) {
            return this.f5042f;
        }
        this.f5042f = aMapLocation;
        return aMapLocation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x012b, code lost:
    
        if ((r9 - r2) > 30000) goto L48;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.ds r21) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dr.a(com.loc.ds):com.loc.ds");
    }

    public final void a() {
        this.f5037a = null;
        this.f5038b = 0L;
        this.f5039c = 0L;
        this.f5042f = null;
        this.f5043g = 0L;
    }

    public final void a(boolean z) {
        this.f5044h = z;
    }
}