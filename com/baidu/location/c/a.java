package com.baidu.location.c;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.Jni;
import com.baidu.location.LocationClient;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import java.util.ArrayList;
import java.util.Iterator;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LocationClient f2268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Context f2269f;
    private AlarmManager k;
    private C0018a l;
    private boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList<BDNotifyListener> f2264a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f2265b = Float.MAX_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BDLocation f2266c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f2267d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2270g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f2271h = 0;
    private boolean i = false;
    private PendingIntent j = null;
    private b m = new b();

    /* JADX INFO: renamed from: com.baidu.location.c.a$a, reason: collision with other inner class name */
    public class C0018a extends BroadcastReceiver {
        public C0018a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (a.this.f2264a == null || a.this.f2264a.isEmpty()) {
                return;
            }
            a.this.f2268e.requestNotifyLocation();
        }
    }

    public class b implements BDLocationListener {
        public b() {
        }

        @Override // com.baidu.location.BDLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            if (a.this.f2264a == null || a.this.f2264a.size() <= 0) {
                return;
            }
            a.this.a(bDLocation);
        }
    }

    public a(Context context, LocationClient locationClient) {
        this.f2268e = null;
        this.f2269f = null;
        this.k = null;
        this.l = null;
        this.n = false;
        this.f2269f = context;
        this.f2268e = locationClient;
        this.f2268e.registerNotifyLocationListener(this.m);
        this.k = (AlarmManager) this.f2269f.getSystemService("alarm");
        this.l = new C0018a();
        this.n = false;
    }

    private void a(long j) {
        try {
            if (this.j != null) {
                this.k.cancel(this.j);
            }
            this.j = PendingIntent.getBroadcast(this.f2269f, 0, new Intent("android.com.baidu.location.TIMER.NOTIFY"), AMapEngineUtils.HALF_MAX_P20_WIDTH);
            if (this.j == null) {
                return;
            }
            this.k.set(0, System.currentTimeMillis() + j, this.j);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BDLocation bDLocation) {
        boolean z;
        if (bDLocation.getLocType() != 61 && bDLocation.getLocType() != 161 && bDLocation.getLocType() != 65) {
            a(120000L);
            return;
        }
        if (System.currentTimeMillis() - this.f2267d < BootloaderScanner.TIMEOUT || this.f2264a == null) {
            return;
        }
        this.f2266c = bDLocation;
        this.f2267d = System.currentTimeMillis();
        float[] fArr = new float[1];
        float f2 = Float.MAX_VALUE;
        for (BDNotifyListener bDNotifyListener : this.f2264a) {
            Location.distanceBetween(bDLocation.getLatitude(), bDLocation.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
            float radius = (fArr[0] - bDNotifyListener.mRadius) - bDLocation.getRadius();
            if (radius > 0.0f) {
                if (radius < f2) {
                    f2 = radius;
                }
            } else if (bDNotifyListener.Notified < 3) {
                z = true;
                bDNotifyListener.Notified++;
                bDNotifyListener.onNotify(bDLocation, fArr[0]);
                if (bDNotifyListener.Notified < 3) {
                    this.i = true;
                }
            }
            z = true;
        }
        if (f2 < this.f2265b) {
            this.f2265b = f2;
        }
        this.f2270g = 0;
        c();
    }

    private boolean b() {
        ArrayList<BDNotifyListener> arrayList = this.f2264a;
        boolean z = false;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<BDNotifyListener> it = this.f2264a.iterator();
            while (it.hasNext()) {
                if (it.next().Notified < 3) {
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c() {
        /*
            r6 = this;
            boolean r0 = r6.b()
            if (r0 != 0) goto L7
            return
        L7:
            float r0 = r6.f2265b
            r1 = 1167867904(0x459c4000, float:5000.0)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r2 = 10000(0x2710, float:1.4013E-41)
            if (r1 <= 0) goto L16
            r0 = 600000(0x927c0, float:8.40779E-40)
            goto L2b
        L16:
            r1 = 1148846080(0x447a0000, float:1000.0)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 <= 0) goto L20
            r0 = 120000(0x1d4c0, float:1.68156E-40)
            goto L2b
        L20:
            r1 = 1140457472(0x43fa0000, float:500.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L2a
            r0 = 60000(0xea60, float:8.4078E-41)
            goto L2b
        L2a:
            r0 = r2
        L2b:
            boolean r1 = r6.i
            r3 = 0
            if (r1 == 0) goto L33
            r6.i = r3
            r0 = r2
        L33:
            int r1 = r6.f2270g
            if (r1 == 0) goto L46
            long r4 = r6.f2271h
            long r1 = (long) r1
            long r4 = r4 + r1
            long r1 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r1
            long r1 = (long) r0
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 <= 0) goto L46
            goto L47
        L46:
            r3 = 1
        L47:
            if (r3 == 0) goto L57
            r6.f2270g = r0
            long r0 = java.lang.System.currentTimeMillis()
            r6.f2271h = r0
            int r0 = r6.f2270g
            long r0 = (long) r0
            r6.a(r0)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.a.c():void");
    }

    public int a(BDNotifyListener bDNotifyListener) {
        if (this.f2264a == null) {
            this.f2264a = new ArrayList<>();
        }
        this.f2264a.add(bDNotifyListener);
        bDNotifyListener.isAdded = true;
        bDNotifyListener.mNotifyCache = this;
        if (!this.n) {
            this.f2269f.registerReceiver(this.l, new IntentFilter("android.com.baidu.location.TIMER.NOTIFY"), "android.permission.ACCESS_FINE_LOCATION", null);
            this.n = true;
        }
        if (bDNotifyListener.mCoorType == null) {
            return 1;
        }
        if (!bDNotifyListener.mCoorType.equals(CoordinateType.GCJ02)) {
            double[] dArrCoorEncrypt = Jni.coorEncrypt(bDNotifyListener.mLongitude, bDNotifyListener.mLatitude, bDNotifyListener.mCoorType + "2gcj");
            bDNotifyListener.mLongitudeC = dArrCoorEncrypt[0];
            bDNotifyListener.mLatitudeC = dArrCoorEncrypt[1];
        }
        if (this.f2266c == null || System.currentTimeMillis() - this.f2267d > 30000) {
            this.f2268e.requestNotifyLocation();
        } else {
            float[] fArr = new float[1];
            Location.distanceBetween(this.f2266c.getLatitude(), this.f2266c.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
            float radius = (fArr[0] - bDNotifyListener.mRadius) - this.f2266c.getRadius();
            if (radius > 0.0f) {
                if (radius < this.f2265b) {
                    this.f2265b = radius;
                }
            } else if (bDNotifyListener.Notified < 3) {
                bDNotifyListener.Notified++;
                bDNotifyListener.onNotify(this.f2266c, fArr[0]);
                if (bDNotifyListener.Notified < 3) {
                    this.i = true;
                }
            }
        }
        c();
        return 1;
    }

    public void a() {
        PendingIntent pendingIntent = this.j;
        if (pendingIntent != null) {
            this.k.cancel(pendingIntent);
        }
        this.f2266c = null;
        this.f2267d = 0L;
        if (this.n) {
            this.f2269f.unregisterReceiver(this.l);
        }
        this.n = false;
    }

    public void b(BDNotifyListener bDNotifyListener) {
        if (bDNotifyListener.mCoorType == null) {
            return;
        }
        if (!bDNotifyListener.mCoorType.equals(CoordinateType.GCJ02)) {
            double[] dArrCoorEncrypt = Jni.coorEncrypt(bDNotifyListener.mLongitude, bDNotifyListener.mLatitude, bDNotifyListener.mCoorType + "2gcj");
            bDNotifyListener.mLongitudeC = dArrCoorEncrypt[0];
            bDNotifyListener.mLatitudeC = dArrCoorEncrypt[1];
        }
        if (this.f2266c == null || System.currentTimeMillis() - this.f2267d > 300000) {
            this.f2268e.requestNotifyLocation();
        } else {
            float[] fArr = new float[1];
            Location.distanceBetween(this.f2266c.getLatitude(), this.f2266c.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
            float radius = (fArr[0] - bDNotifyListener.mRadius) - this.f2266c.getRadius();
            if (radius > 0.0f) {
                if (radius < this.f2265b) {
                    this.f2265b = radius;
                }
            } else if (bDNotifyListener.Notified < 3) {
                bDNotifyListener.Notified++;
                bDNotifyListener.onNotify(this.f2266c, fArr[0]);
                if (bDNotifyListener.Notified < 3) {
                    this.i = true;
                }
            }
        }
        c();
    }

    public int c(BDNotifyListener bDNotifyListener) {
        PendingIntent pendingIntent;
        ArrayList<BDNotifyListener> arrayList = this.f2264a;
        if (arrayList == null) {
            return 0;
        }
        if (arrayList.contains(bDNotifyListener)) {
            this.f2264a.remove(bDNotifyListener);
        }
        if (this.f2264a.size() != 0 || (pendingIntent = this.j) == null) {
            return 1;
        }
        this.k.cancel(pendingIntent);
        return 1;
    }
}