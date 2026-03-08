package com.amap.api.mapcore.util;

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
import java.util.TreeMap;

/* JADX INFO: compiled from: WifiManagerWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class ka {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static long f1505d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static long f1506e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static long f1507f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static long f1508g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static long f1509h;
    public static HashMap<String, Long> s = new HashMap<>(36);
    public static long t = 0;
    static int u = 0;
    public static long w = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    WifiManager f1510a;
    Context i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<ScanResult> f1511b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<lf> f1512c = new ArrayList<>();
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

    public ka(Context context, WifiManager wifiManager) {
        this.f1510a = wifiManager;
        this.i = context;
    }

    private static boolean a(int i) {
        int iCalculateSignalLevel = 20;
        try {
            iCalculateSignalLevel = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e2) {
            kg.a(e2, "Aps", "wifiSigFine");
        }
        return iCalculateSignalLevel > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !kk.a(wifiInfo.getBSSID())) ? false : true;
    }

    private void d(boolean z) {
        String strValueOf;
        ArrayList<ScanResult> arrayList = this.f1511b;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (kk.b() - f1508g > DateUtil.HOUR) {
            b();
        }
        if (this.p == null) {
            this.p = new TreeMap<>(Collections.reverseOrder());
        }
        this.p.clear();
        if (this.r && z) {
            try {
                this.f1512c.clear();
            } catch (Throwable unused) {
            }
        }
        int size = this.f1511b.size();
        for (int i = 0; i < size; i++) {
            ScanResult scanResult = this.f1511b.get(i);
            if (kk.a(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                if (this.r && z) {
                    try {
                        lf lfVar = new lf(false);
                        lfVar.f1652b = scanResult.SSID;
                        lfVar.f1654d = scanResult.frequency;
                        lfVar.f1655e = scanResult.timestamp;
                        lfVar.f1651a = lf.a(scanResult.BSSID);
                        lfVar.f1653c = (short) scanResult.level;
                        if (Build.VERSION.SDK_INT >= 17) {
                            lfVar.f1657g = (short) ((SystemClock.elapsedRealtime() - (scanResult.timestamp / 1000)) / 1000);
                            if (lfVar.f1657g < 0) {
                                lfVar.f1657g = (short) 0;
                            }
                        }
                        lfVar.f1656f = System.currentTimeMillis();
                        this.f1512c.add(lfVar);
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
        this.f1511b.clear();
        Iterator<ScanResult> it = this.p.values().iterator();
        while (it.hasNext()) {
            this.f1511b.add(it.next());
        }
        this.p.clear();
    }

    private void e(boolean z) {
        this.l = z;
        this.m = true;
        this.n = true;
        this.z = 30000L;
    }

    public static String i() {
        return String.valueOf(kk.b() - f1508g);
    }

    private List<ScanResult> j() {
        long jB;
        WifiManager wifiManager = this.f1510a;
        if (wifiManager != null) {
            try {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                if (Build.VERSION.SDK_INT >= 17) {
                    HashMap<String, Long> map = new HashMap<>(36);
                    for (ScanResult scanResult : scanResults) {
                        map.put(scanResult.BSSID, Long.valueOf(scanResult.timestamp));
                    }
                    if (s.isEmpty() || !s.equals(map)) {
                        s = map;
                        jB = kk.b();
                    }
                    this.o = null;
                    return scanResults;
                }
                jB = kk.b();
                t = jB;
                this.o = null;
                return scanResults;
            } catch (SecurityException e2) {
                this.o = e2.getMessage();
            } catch (Throwable th) {
                this.o = null;
                kg.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    private WifiInfo k() {
        try {
            if (this.f1510a != null) {
                return this.f1510a.getConnectionInfo();
            }
            return null;
        } catch (Throwable th) {
            kg.a(th, "WifiManagerWrapper", "getConnectionInfo");
            return null;
        }
    }

    private int l() {
        WifiManager wifiManager = this.f1510a;
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        return 4;
    }

    private boolean m() {
        long jB = kk.b() - f1505d;
        if (jB < 4900) {
            return false;
        }
        if (n() && jB < 9900) {
            return false;
        }
        if (u > 1) {
            long jB2 = this.z;
            if (jB2 == 30000) {
                jB2 = kf.b() != -1 ? kf.b() : 30000L;
            }
            if (Build.VERSION.SDK_INT >= 28 && jB < jB2) {
                return false;
            }
        }
        if (this.f1510a == null) {
            return false;
        }
        f1505d = kk.b();
        int i = u;
        if (i < 2) {
            u = i + 1;
        }
        return this.f1510a.startScan();
    }

    private boolean n() {
        if (this.v == null) {
            this.v = (ConnectivityManager) kk.a(this.i, "connectivity");
        }
        return a(this.v);
    }

    private boolean o() {
        if (this.f1510a == null) {
            return false;
        }
        return kk.c(this.i);
    }

    private void p() {
        if (s()) {
            long jB = kk.b();
            if (jB - f1506e >= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                this.f1511b.clear();
                f1509h = f1508g;
            }
            q();
            if (jB - f1506e >= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                for (int i = 20; i > 0 && f1508g == f1509h; i--) {
                    try {
                        Thread.sleep(150L);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    private void q() {
        if (s()) {
            try {
                if (m()) {
                    f1507f = kk.b();
                }
            } catch (Throwable th) {
                kg.a(th, "WifiManager", "wifiScan");
            }
        }
    }

    private void r() {
        if (f1509h != f1508g) {
            List<ScanResult> listJ = null;
            try {
                listJ = j();
            } catch (Throwable th) {
                kg.a(th, "WifiManager", "updateScanResult");
            }
            f1509h = f1508g;
            if (listJ == null) {
                this.f1511b.clear();
            } else {
                this.f1511b.clear();
                this.f1511b.addAll(listJ);
            }
        }
    }

    private boolean s() {
        this.q = o();
        if (!this.q || !this.l) {
            return false;
        }
        if (f1507f != 0) {
            if (kk.b() - f1507f < 4900 || kk.b() - f1508g < 1500) {
                return false;
            }
            int i = ((kk.b() - f1508g) > 4900L ? 1 : ((kk.b() - f1508g) == 4900L ? 0 : -1));
        }
        return true;
    }

    public final ArrayList<ScanResult> a() {
        if (this.f1511b == null) {
            return null;
        }
        ArrayList<ScanResult> arrayList = new ArrayList<>();
        if (!this.f1511b.isEmpty()) {
            arrayList.addAll(this.f1511b);
        }
        return arrayList;
    }

    public final void a(boolean z) {
        Context context = this.i;
        if (!kf.a() || !this.n || this.f1510a == null || context == null || !z || kk.c() <= 17) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (((Integer) ki.a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                ki.a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, (Class<?>[]) new Class[]{ContentResolver.class, String.class, Integer.TYPE});
            }
        } catch (Throwable th) {
            kg.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
        }
    }

    public final boolean a(ConnectivityManager connectivityManager) {
        WifiManager wifiManager = this.f1510a;
        if (wifiManager == null) {
            return false;
        }
        try {
            if (kk.a(connectivityManager.getActiveNetworkInfo()) == 1) {
                return a(wifiManager.getConnectionInfo());
            }
            return false;
        } catch (Throwable th) {
            kg.a(th, "WifiManagerWrapper", "wifiAccess");
            return false;
        }
    }

    public final void b() {
        this.y = null;
        this.f1511b.clear();
    }

    public final void b(boolean z) {
        if (z) {
            p();
        } else {
            q();
        }
        boolean z2 = false;
        if (this.x) {
            this.x = false;
            b();
        }
        r();
        if (kk.b() - f1508g > 20000) {
            this.f1511b.clear();
        }
        f1506e = kk.b();
        if (this.f1511b.isEmpty()) {
            f1508g = kk.b();
            List<ScanResult> listJ = j();
            if (listJ != null) {
                this.f1511b.addAll(listJ);
                z2 = true;
            }
        }
        d(z2);
    }

    public final void c() {
        if (this.f1510a != null && kk.b() - f1508g > 4900) {
            f1508g = kk.b();
        }
    }

    public final void c(boolean z) {
        e(z);
    }

    public final void d() {
        int iL;
        if (this.f1510a == null) {
            return;
        }
        try {
            iL = l();
        } catch (Throwable th) {
            kg.a(th, "Aps", "onReceive part");
            iL = 4;
        }
        if (this.f1511b == null) {
            this.f1511b = new ArrayList<>();
        }
        if (iL == 0 || iL == 1 || iL == 4) {
            this.x = true;
        }
    }

    public final boolean e() {
        return this.q;
    }

    public final WifiInfo f() {
        this.y = k();
        return this.y;
    }

    public final boolean g() {
        return this.j;
    }

    public final void h() {
        b();
        this.f1511b.clear();
    }
}