package com.loc;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/* JADX INFO: compiled from: WifiManagerWrapper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dx {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static long f5079d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static long f5080e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static long f5081f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static long f5082g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static long f5083h;
    public static HashMap<String, Long> s = new HashMap<>(36);
    public static long t = 0;
    static int u = 0;
    public static long w = 0;
    private Cdo A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    WifiManager f5084a;
    Context i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<ScanResult> f5085b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<dd> f5086c = new ArrayList<>();
    boolean j = false;
    StringBuilder k = null;
    boolean l = true;
    boolean m = true;
    boolean n = true;
    private volatile WifiInfo y = null;
    String o = null;
    TreeMap<Integer, ScanResult> p = null;
    public boolean q = true;
    public boolean r = false;
    ConnectivityManager v = null;
    private long z = 30000;
    volatile boolean x = false;

    public dx(Context context, WifiManager wifiManager) {
        this.f5084a = wifiManager;
        this.i = context;
    }

    private static boolean a(int i) {
        int iCalculateSignalLevel = 20;
        try {
            iCalculateSignalLevel = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e2) {
            ej.a(e2, "Aps", "wifiSigFine");
        }
        return iCalculateSignalLevel > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !ep.a(wifiInfo.getBSSID())) ? false : true;
    }

    public static long b() {
        return ((ep.b() - t) / 1000) + 1;
    }

    private void c(boolean z) {
        String strValueOf;
        ArrayList<ScanResult> arrayList = this.f5085b;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (ep.b() - f5082g > DateUtil.HOUR) {
            g();
        }
        if (this.p == null) {
            this.p = new TreeMap<>(Collections.reverseOrder());
        }
        this.p.clear();
        if (this.r && z) {
            try {
                this.f5086c.clear();
            } catch (Throwable unused) {
            }
        }
        int size = this.f5085b.size();
        for (int i = 0; i < size; i++) {
            ScanResult scanResult = this.f5085b.get(i);
            if (ep.a(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                if (this.r && z) {
                    try {
                        dd ddVar = new dd(false);
                        ddVar.f4969b = scanResult.SSID;
                        ddVar.f4971d = scanResult.frequency;
                        ddVar.f4972e = scanResult.timestamp;
                        ddVar.f4968a = dd.a(scanResult.BSSID);
                        ddVar.f4970c = (short) scanResult.level;
                        if (Build.VERSION.SDK_INT >= 17) {
                            ddVar.f4974g = (short) ((SystemClock.elapsedRealtime() - (scanResult.timestamp / 1000)) / 1000);
                            if (ddVar.f4974g < 0) {
                                ddVar.f4974g = (short) 0;
                            }
                        }
                        ddVar.f4973f = System.currentTimeMillis();
                        this.f5086c.add(ddVar);
                    } catch (Throwable unused2) {
                    }
                }
                if (!TextUtils.isEmpty(scanResult.SSID)) {
                    strValueOf = "<unknown ssid>".equals(scanResult.SSID) ? "unkwn" : String.valueOf(i);
                    this.p.put(Integer.valueOf((scanResult.level * 25) + i), scanResult);
                }
                scanResult.SSID = strValueOf;
                this.p.put(Integer.valueOf((scanResult.level * 25) + i), scanResult);
            }
        }
        this.f5085b.clear();
        Iterator<ScanResult> it = this.p.values().iterator();
        while (it.hasNext()) {
            this.f5085b.add(it.next());
        }
        this.p.clear();
    }

    public static String o() {
        return String.valueOf(ep.b() - f5082g);
    }

    private List<ScanResult> p() {
        long jB;
        WifiManager wifiManager = this.f5084a;
        if (wifiManager != null) {
            try {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                if (Build.VERSION.SDK_INT >= 17) {
                    HashMap<String, Long> map = new HashMap<>(36);
                    if (scanResults != null) {
                        for (ScanResult scanResult : scanResults) {
                            map.put(scanResult.BSSID, Long.valueOf(scanResult.timestamp));
                        }
                    }
                    if (s.isEmpty() || !s.equals(map)) {
                        s = map;
                        jB = ep.b();
                    }
                    this.o = null;
                    return scanResults;
                }
                jB = ep.b();
                t = jB;
                this.o = null;
                return scanResults;
            } catch (SecurityException e2) {
                this.o = e2.getMessage();
            } catch (Throwable th) {
                this.o = null;
                ej.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0059 A[Catch: all -> 0x007e, TryCatch #0 {all -> 0x007e, blocks: (B:4:0x0006, B:6:0x0013, B:8:0x0017, B:9:0x0023, B:13:0x0031, B:15:0x0036, B:17:0x003e, B:21:0x004f, B:18:0x0041, B:20:0x004b, B:25:0x0059, B:27:0x005d, B:29:0x0068, B:30:0x006d, B:33:0x0077), top: B:38:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void q() {
        /*
            r10 = this;
            boolean r0 = r10.r()
            if (r0 == 0) goto L86
            long r0 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L7e
            long r2 = com.loc.dx.f5079d     // Catch: java.lang.Throwable -> L7e
            long r0 = r0 - r2
            r2 = 4900(0x1324, double:2.421E-320)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L74
            android.net.ConnectivityManager r2 = r10.v     // Catch: java.lang.Throwable -> L7e
            if (r2 != 0) goto L23
            android.content.Context r2 = r10.i     // Catch: java.lang.Throwable -> L7e
            java.lang.String r3 = "connectivity"
            java.lang.Object r2 = com.loc.ep.a(r2, r3)     // Catch: java.lang.Throwable -> L7e
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch: java.lang.Throwable -> L7e
            r10.v = r2     // Catch: java.lang.Throwable -> L7e
        L23:
            android.net.ConnectivityManager r2 = r10.v     // Catch: java.lang.Throwable -> L7e
            boolean r2 = r10.a(r2)     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L31
            r2 = 9900(0x26ac, double:4.8912E-320)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L74
        L31:
            int r2 = com.loc.dx.u     // Catch: java.lang.Throwable -> L7e
            r3 = 1
            if (r2 <= r3) goto L59
            long r4 = r10.z     // Catch: java.lang.Throwable -> L7e
            r6 = 30000(0x7530, double:1.4822E-319)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L41
            long r6 = r10.z     // Catch: java.lang.Throwable -> L7e
            goto L4f
        L41:
            long r4 = com.loc.ei.n()     // Catch: java.lang.Throwable -> L7e
            r8 = -1
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 == 0) goto L4f
            long r6 = com.loc.ei.n()     // Catch: java.lang.Throwable -> L7e
        L4f:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L7e
            r4 = 28
            if (r2 < r4) goto L59
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 < 0) goto L74
        L59:
            android.net.wifi.WifiManager r0 = r10.f5084a     // Catch: java.lang.Throwable -> L7e
            if (r0 == 0) goto L74
            long r0 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L7e
            com.loc.dx.f5079d = r0     // Catch: java.lang.Throwable -> L7e
            int r0 = com.loc.dx.u     // Catch: java.lang.Throwable -> L7e
            r1 = 2
            if (r0 >= r1) goto L6d
            int r0 = com.loc.dx.u     // Catch: java.lang.Throwable -> L7e
            int r0 = r0 + r3
            com.loc.dx.u = r0     // Catch: java.lang.Throwable -> L7e
        L6d:
            android.net.wifi.WifiManager r0 = r10.f5084a     // Catch: java.lang.Throwable -> L7e
            boolean r0 = r0.startScan()     // Catch: java.lang.Throwable -> L7e
            goto L75
        L74:
            r0 = 0
        L75:
            if (r0 == 0) goto L7d
            long r0 = com.loc.ep.b()     // Catch: java.lang.Throwable -> L7e
            com.loc.dx.f5081f = r0     // Catch: java.lang.Throwable -> L7e
        L7d:
            return
        L7e:
            r0 = move-exception
            java.lang.String r1 = "WifiManager"
            java.lang.String r2 = "wifiScan"
            com.loc.ej.a(r0, r1, r2)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dx.q():void");
    }

    private boolean r() {
        this.q = this.f5084a == null ? false : ep.h(this.i);
        if (!this.q || !this.l) {
            return false;
        }
        if (f5081f != 0) {
            if (ep.b() - f5081f < 4900 || ep.b() - f5082g < 1500) {
                return false;
            }
            int i = ((ep.b() - f5082g) > 4900L ? 1 : ((ep.b() - f5082g) == 4900L ? 0 : -1));
        }
        return true;
    }

    public final ArrayList<dd> a() {
        if (!this.r) {
            return this.f5086c;
        }
        b(true);
        return this.f5086c;
    }

    public final void a(Cdo cdo) {
        this.A = cdo;
    }

    public final void a(boolean z) {
        Context context = this.i;
        if (!ei.m() || !this.n || this.f5084a == null || context == null || !z || ep.c() <= 17) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (((Integer) em.a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                em.a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, (Class<?>[]) new Class[]{ContentResolver.class, String.class, Integer.TYPE});
            }
        } catch (Throwable th) {
            ej.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
        }
    }

    public final void a(boolean z, boolean z2, boolean z3, long j) {
        this.l = z;
        this.m = z2;
        this.n = z3;
        if (j < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
            this.z = DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
        } else {
            this.z = j;
        }
    }

    public final boolean a(ConnectivityManager connectivityManager) {
        WifiManager wifiManager = this.f5084a;
        if (wifiManager == null) {
            return false;
        }
        try {
            if (ep.a(connectivityManager.getActiveNetworkInfo()) == 1) {
                return a(wifiManager.getConnectionInfo());
            }
            return false;
        } catch (Throwable th) {
            ej.a(th, "WifiManagerWrapper", "wifiAccess");
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(boolean r8) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dx.b(boolean):void");
    }

    public final WifiInfo c() {
        try {
            if (this.f5084a != null) {
                return this.f5084a.getConnectionInfo();
            }
            return null;
        } catch (Throwable th) {
            ej.a(th, "WifiManagerWrapper", "getConnectionInfo");
            return null;
        }
    }

    public final String d() {
        return this.o;
    }

    public final ArrayList<ScanResult> e() {
        if (this.f5085b == null) {
            return null;
        }
        ArrayList<ScanResult> arrayList = new ArrayList<>();
        if (!this.f5085b.isEmpty()) {
            arrayList.addAll(this.f5085b);
        }
        return arrayList;
    }

    public final void f() {
        try {
            this.r = true;
            List<ScanResult> listP = p();
            if (listP != null) {
                this.f5085b.clear();
                this.f5085b.addAll(listP);
            }
            c(true);
        } catch (Throwable unused) {
        }
    }

    public final void g() {
        this.y = null;
        this.f5085b.clear();
    }

    public final void h() {
        w = System.currentTimeMillis();
        Cdo cdo = this.A;
        if (cdo != null) {
            cdo.b();
        }
    }

    public final void i() {
        if (this.f5084a != null && ep.b() - f5082g > 4900) {
            f5082g = ep.b();
        }
    }

    public final void j() {
        if (this.f5084a == null) {
            return;
        }
        this.x = true;
    }

    public final WifiInfo k() {
        this.y = c();
        return this.y;
    }

    public final boolean l() {
        return this.j;
    }

    public final String m() {
        boolean z;
        String str;
        StringBuilder sb = this.k;
        if (sb == null) {
            this.k = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        this.j = false;
        this.y = k();
        String bssid = a(this.y) ? this.y.getBSSID() : "";
        int size = this.f5085b.size();
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < size) {
            String str2 = this.f5085b.get(i).BSSID;
            if (!this.m && !"<unknown ssid>".equals(this.f5085b.get(i).SSID)) {
                z2 = true;
            }
            if (bssid.equals(str2)) {
                str = "access";
                z = true;
            } else {
                z = z3;
                str = "nb";
            }
            this.k.append(String.format(Locale.US, "#%s,%s", str2, str));
            i++;
            z3 = z;
        }
        if (this.f5085b.size() == 0) {
            z2 = true;
        }
        if (!this.m && !z2) {
            this.j = true;
        }
        if (!z3 && !TextUtils.isEmpty(bssid)) {
            StringBuilder sb2 = this.k;
            sb2.append("#");
            sb2.append(bssid);
            this.k.append(",access");
        }
        return this.k.toString();
    }

    public final void n() {
        g();
        this.f5085b.clear();
    }
}