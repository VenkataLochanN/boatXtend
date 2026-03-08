package com.baidu.location.d;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.baidu.location.BDLocation;
import com.baidu.location.a.v;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Context f2329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static volatile h f2330d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final File f2332f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final k f2333g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final com.baidu.location.d.c f2334h;
    private final l i;
    private final f j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Object f2331e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2327a = com.baidu.location.g.a.f2452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final String f2328b = "http://ofloc.map.baidu.com/offline_loc";

    public enum a {
        NEED_TO_LOG,
        NO_NEED_TO_LOG
    }

    public enum b {
        IS_MIX_MODE,
        IS_NOT_MIX_MODE
    }

    private enum c {
        NETWORK_UNKNOWN,
        NETWORK_WIFI,
        NETWORK_2G,
        NETWORK_3G,
        NETWORK_4G
    }

    private h() {
        File file;
        try {
            file = new File(f2329c.getFilesDir(), "ofld");
            try {
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            file = null;
        }
        this.f2332f = file;
        this.f2334h = new com.baidu.location.d.c(this);
        this.f2333g = new k(this.f2334h.a());
        this.j = new f(this, this.f2334h.a());
        this.i = new l(this, this.f2334h.a(), this.j.n());
    }

    private BDLocation a(String[] strArr) {
        new BDLocation();
        FutureTask futureTask = (FutureTask) v.a().c().submit(new i(this, strArr));
        try {
            return (BDLocation) futureTask.get(2000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            futureTask.cancel(true);
            return null;
        }
    }

    public static h a() {
        h hVar;
        synchronized (f2331e) {
            if (f2330d == null) {
                if (f2329c == null) {
                    a(com.baidu.location.f.getServiceContext());
                }
                f2330d = new h();
            }
            f2330d.q();
            hVar = f2330d;
        }
        return hVar;
    }

    public static void a(Context context) {
        if (f2329c == null) {
            f2329c = context;
            com.baidu.location.g.b.a().a(f2329c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Uri c(String str) {
        return Uri.parse(String.format("content://%s/", str));
    }

    private void q() {
        this.j.g();
    }

    private boolean r() {
        String packageName = f2329c.getPackageName();
        ProviderInfo providerInfoResolveContentProvider = null;
        for (String str : this.j.o()) {
            try {
                providerInfoResolveContentProvider = f2329c.getPackageManager().resolveContentProvider(str, 0);
            } catch (Exception unused) {
                providerInfoResolveContentProvider = null;
            }
            if (providerInfoResolveContentProvider != null) {
                break;
            }
        }
        return providerInfoResolveContentProvider == null || packageName.equals(providerInfoResolveContentProvider.packageName);
    }

    public long a(String str) {
        return this.j.a(str);
    }

    public BDLocation a(com.baidu.location.e.a aVar, com.baidu.location.e.h hVar, BDLocation bDLocation, b bVar, a aVar2) {
        String strD;
        int iA;
        if (bVar == b.IS_MIX_MODE) {
            iA = this.j.a();
            strD = com.baidu.location.g.b.a().d() + "&mixMode=1";
        } else {
            strD = com.baidu.location.g.b.a().d();
            iA = 0;
        }
        String[] strArrA = j.a(aVar, hVar, bDLocation, strD, (aVar2 == a.NEED_TO_LOG).booleanValue(), iA);
        BDLocation bDLocationA = null;
        if (strArrA.length > 0 && (bDLocationA = a(strArrA)) != null) {
            bDLocationA.getLocType();
        }
        return bDLocationA;
    }

    public Context b() {
        return f2329c;
    }

    File c() {
        return this.f2332f;
    }

    public boolean d() {
        return this.j.h();
    }

    public boolean e() {
        return this.j.i();
    }

    public boolean f() {
        return this.j.j();
    }

    public boolean g() {
        return this.j.k();
    }

    public boolean h() {
        return this.j.m();
    }

    public void i() {
        if (com.baidu.location.g.k.b()) {
            return;
        }
        this.f2333g.a();
    }

    k j() {
        return this.f2333g;
    }

    l k() {
        return this.i;
    }

    f l() {
        return this.j;
    }

    public void m() {
        if (!r() || com.baidu.location.g.k.b()) {
            return;
        }
        this.f2334h.b();
    }

    public void n() {
    }

    public double o() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) f2329c.getSystemService("connectivity")).getActiveNetworkInfo();
        c cVar = c.NETWORK_UNKNOWN;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 1) {
                cVar = c.NETWORK_WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11) {
                    cVar = c.NETWORK_2G;
                } else if (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) {
                    cVar = c.NETWORK_3G;
                } else if (subtype == 13) {
                    cVar = c.NETWORK_4G;
                }
            }
        }
        if (cVar == c.NETWORK_UNKNOWN) {
            return this.j.b();
        }
        if (cVar == c.NETWORK_WIFI) {
            return this.j.c();
        }
        if (cVar == c.NETWORK_2G) {
            return this.j.d();
        }
        if (cVar == c.NETWORK_3G) {
            return this.j.e();
        }
        if (cVar == c.NETWORK_4G) {
            return this.j.f();
        }
        return 0.0d;
    }
}