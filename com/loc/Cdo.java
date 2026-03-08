package com.loc;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.view.MotionEventCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import com.loc.by;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.crypto.KeyGenerator;

/* JADX INFO: renamed from: com.loc.do, reason: invalid class name */
/* JADX INFO: compiled from: CollectionManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class Cdo implements dk {
    private static long k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f5019a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    cu f5022d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f5025g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private LocationManager f5026h;
    private a i;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<ca> f5024f = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    dx f5020b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    dw f5021c = null;
    private volatile boolean j = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ax f5023e = new ax();

    /* JADX INFO: renamed from: com.loc.do$a */
    /* JADX INFO: compiled from: CollectionManager.java */
    static class a implements LocationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Cdo f5028a;

        a(Cdo cdo) {
            this.f5028a = cdo;
        }

        final void a() {
            this.f5028a = null;
        }

        final void a(Cdo cdo) {
            this.f5028a = cdo;
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            try {
                if (this.f5028a != null) {
                    this.f5028a.a(location);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* JADX INFO: renamed from: com.loc.do$b */
    /* JADX INFO: compiled from: CollectionManager.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f5030b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Location f5031c;

        b(int i) {
            this.f5030b = 0;
            this.f5030b = i;
        }

        b(Cdo cdo, Location location) {
            this(1);
            this.f5031c = location;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.f5030b;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        Cdo.this.f();
                        return;
                    }
                    return;
                }
                ao aoVarA = null;
                try {
                    long unused = Cdo.k = System.currentTimeMillis();
                    if (Cdo.this.f5023e.f4819f.c()) {
                        aoVarA = ao.a(new File(Cdo.this.f5023e.f4814a), Cdo.this.f5023e.f4815b);
                        ArrayList arrayList = new ArrayList();
                        byte[] bArrE = Cdo.e();
                        if (bArrE == null) {
                            try {
                                aoVarA.close();
                                return;
                            } catch (Throwable unused2) {
                                return;
                            }
                        }
                        List listB = Cdo.b(aoVarA, Cdo.this.f5023e, arrayList, bArrE);
                        if (listB != null && listB.size() != 0) {
                            Cdo.this.f5023e.f4819f.a(true);
                            if (cu.a(u.b(cu.a(dy.a(bArrE), o.b(bArrE, cu.a(), u.c()), listB)))) {
                                Cdo.a(aoVarA, arrayList);
                            }
                        }
                        try {
                            aoVarA.close();
                            return;
                        } catch (Throwable unused3) {
                            return;
                        }
                    }
                    if (aoVarA != null) {
                        try {
                            aoVarA.close();
                            return;
                        } catch (Throwable unused4) {
                            return;
                        }
                    }
                    return;
                } catch (Throwable th) {
                    try {
                        ab.b(th, "leg", "uts");
                        if (aoVarA != null) {
                            try {
                                aoVarA.close();
                                return;
                            } catch (Throwable unused5) {
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th2) {
                        if (aoVarA != null) {
                            try {
                                aoVarA.close();
                            } catch (Throwable unused6) {
                            }
                        }
                        throw th2;
                    }
                }
            }
            try {
                if (this.f5031c != null && Cdo.this.j) {
                    Bundle extras = this.f5031c.getExtras();
                    int i2 = extras != null ? extras.getInt("satellites") : 0;
                    if (ep.a(this.f5031c, i2)) {
                        return;
                    }
                    if (Cdo.this.f5020b != null && !Cdo.this.f5020b.r) {
                        Cdo.this.f5020b.f();
                    }
                    ArrayList<dd> arrayListA = Cdo.this.f5020b.a();
                    List<cw> listA = Cdo.this.f5021c.a();
                    by.a aVar = new by.a();
                    dc dcVar = new dc();
                    dcVar.i = this.f5031c.getAccuracy();
                    dcVar.f4965f = this.f5031c.getAltitude();
                    dcVar.f4963d = this.f5031c.getLatitude();
                    dcVar.f4967h = this.f5031c.getBearing();
                    dcVar.f4964e = this.f5031c.getLongitude();
                    dcVar.j = this.f5031c.isFromMockProvider();
                    dcVar.f4960a = this.f5031c.getProvider();
                    dcVar.f4966g = this.f5031c.getSpeed();
                    dcVar.l = (byte) i2;
                    dcVar.f4961b = System.currentTimeMillis();
                    dcVar.f4962c = this.f5031c.getTime();
                    dcVar.k = this.f5031c.getTime();
                    aVar.f4906a = dcVar;
                    aVar.f4907b = arrayListA;
                    WifiInfo wifiInfoC = Cdo.this.f5020b.c();
                    if (wifiInfoC != null) {
                        aVar.f4908c = dd.a(wifiInfoC.getBSSID());
                    }
                    aVar.f4909d = dx.w;
                    aVar.f4911f = this.f5031c.getTime();
                    aVar.f4912g = (byte) n.p(Cdo.this.f5019a);
                    aVar.f4913h = n.u(Cdo.this.f5019a);
                    aVar.f4910e = Cdo.this.f5020b.q;
                    aVar.j = ep.a(Cdo.this.f5019a);
                    aVar.i = listA;
                    ca caVarA = cu.a(aVar);
                    if (caVarA == null) {
                        return;
                    }
                    synchronized (Cdo.this.f5024f) {
                        Cdo.this.f5024f.add(caVarA);
                        if (Cdo.this.f5024f.size() >= 5) {
                            try {
                                ab.d().submit(Cdo.this.new b(3));
                            } catch (Throwable unused7) {
                            }
                        }
                    }
                    Cdo.this.d();
                }
            } catch (Throwable th3) {
                ej.a(th3, "cl", "coll");
            }
        }
    }

    Cdo(Context context) {
        this.f5019a = null;
        this.f5019a = context;
        bd.a(this.f5019a, this.f5023e, z.k, 100, 1024000, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.f5023e.f4819f = new bp(context, ei.f5156g, "kKey", new bn(context, ei.f5154e, ei.f5155f, ei.f5155f * 10, "carrierLocKey"));
        this.f5023e.f4818e = new am();
    }

    static /* synthetic */ void a(ao aoVar, List list) {
        if (aoVar != null) {
            try {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    aoVar.c((String) it.next());
                }
                aoVar.close();
            } catch (Throwable th) {
                ab.b(th, "aps", "dlo");
            }
        }
    }

    private static byte[] a(int i) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            if (keyGenerator == null) {
                return null;
            }
            keyGenerator.init(i);
            return keyGenerator.generateKey().getEncoded();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f9, code lost:
    
        if (r7 == null) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00fb, code lost:
    
        r7.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x00f2 A[EXC_TOP_SPLITTER, PHI: r5 r9 r16
  0x00f2: PHI (r5v3 int) = (r5v4 int), (r5v5 int) binds: [B:72:0x011a, B:58:0x00f0] A[DONT_GENERATE, DONT_INLINE]
  0x00f2: PHI (r9v1 com.loc.ao$b) = (r9v2 com.loc.ao$b), (r9v3 com.loc.ao$b) binds: [B:72:0x011a, B:58:0x00f0] A[DONT_GENERATE, DONT_INLINE]
  0x00f2: PHI (r16v3 java.lang.String[]) = (r16v4 java.lang.String[]), (r16v7 java.lang.String[]) binds: [B:72:0x011a, B:58:0x00f0] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0127 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.loc.ca> b(com.loc.ao r17, com.loc.ax r18, java.util.List<java.lang.String> r19, byte[] r20) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.Cdo.b(com.loc.ao, com.loc.ax, java.util.List, byte[]):java.util.List");
    }

    private static byte[] b(int i) {
        return new byte[]{(byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8), (byte) (i & 255)};
    }

    static /* synthetic */ byte[] e() {
        return a(128);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.f5024f != null && this.f5024f.size() != 0) {
                ArrayList<ca> arrayList = new ArrayList();
                synchronized (this.f5024f) {
                    arrayList.addAll(this.f5024f);
                    this.f5024f.clear();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArrA = a(256);
                if (bArrA == null) {
                    return;
                }
                byteArrayOutputStream.write(b(bArrA.length));
                byteArrayOutputStream.write(bArrA);
                for (ca caVar : arrayList) {
                    byte[] bArrB = caVar.b();
                    if (bArrB.length >= 10 && bArrB.length <= 65535) {
                        byte[] bArrB2 = o.b(bArrA, bArrB, u.c());
                        byteArrayOutputStream.write(b(bArrB2.length));
                        byteArrayOutputStream.write(bArrB2);
                        int iA = caVar.a();
                        byteArrayOutputStream.write(new byte[]{(byte) ((iA >> 24) & 255), (byte) ((iA >> 16) & 255), (byte) ((iA >> 8) & 255), (byte) (iA & 255)});
                    }
                }
                ay.a(Long.toString(System.currentTimeMillis()), byteArrayOutputStream.toByteArray(), this.f5023e);
            }
        } catch (Throwable th) {
            ej.a(th, "clm", "wtD");
        }
    }

    @Override // com.loc.dk
    public final dj a(di diVar) {
        try {
            ed edVar = new ed();
            edVar.a(diVar.f4986b);
            edVar.a(diVar.f4985a);
            edVar.a(diVar.f4988d);
            aq.a();
            aw awVarC = aq.c(edVar);
            dj djVar = new dj();
            djVar.f4992c = awVarC.f4809a;
            djVar.f4991b = awVarC.f4810b;
            djVar.f4990a = 200;
            return djVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    final void a() {
        try {
            if (this.i != null && this.f5026h != null) {
                this.f5026h.removeUpdates(this.i);
            }
            if (this.i != null) {
                this.i.a();
            }
            if (this.j) {
                f();
                this.f5020b.a((Cdo) null);
                this.f5021c.a((Cdo) null);
                this.f5021c = null;
                this.f5020b = null;
                this.f5025g = null;
                this.j = false;
            }
        } catch (Throwable th) {
            ej.a(th, "clm", "stc");
        }
    }

    public final void a(Location location) {
        try {
            if (this.f5025g != null) {
                this.f5025g.post(new b(this, location));
            }
        } catch (Throwable th) {
            ab.b(th, "cl", "olcc");
        }
    }

    public final void a(dw dwVar, dx dxVar, Handler handler) {
        if (this.j || dwVar == null || dxVar == null || handler == null) {
            return;
        }
        this.j = true;
        this.f5021c = dwVar;
        this.f5020b = dxVar;
        this.f5020b.a(this);
        this.f5021c.a(this);
        this.f5025g = handler;
        try {
            if (this.f5026h == null && this.f5025g != null) {
                this.f5026h = (LocationManager) this.f5019a.getSystemService(FirebaseAnalytics.Param.LOCATION);
            }
            if (this.i == null) {
                this.i = new a(this);
            }
            this.i.a(this);
            if (this.i != null && this.f5026h != null) {
                this.f5026h.requestLocationUpdates("passive", 1000L, -1.0f, this.i);
            }
            if (this.f5022d == null) {
                this.f5022d = new cu("5.2.0", k.f(this.f5019a), "S128DF1572465B890OE3F7A13167KLEI", k.c(this.f5019a), this);
                this.f5022d.a(n.x(this.f5019a)).b(n.h(this.f5019a)).c(n.a(this.f5019a)).d(n.g(this.f5019a)).e(n.A(this.f5019a)).f(n.i(this.f5019a)).g(Build.MODEL).h(Build.MANUFACTURER).i(Build.BRAND).a(Build.VERSION.SDK_INT).j(Build.VERSION.RELEASE).a(dd.a(n.m(this.f5019a))).k(n.m(this.f5019a));
            }
        } catch (Throwable th) {
            ej.a(th, "col", com.ido.ble.event.stat.one.d.m);
        }
    }

    public final void b() {
        try {
            if (this.f5025g != null) {
                this.f5025g.post(new Runnable() { // from class: com.loc.do.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (Cdo.this.f5022d == null || Cdo.this.f5020b == null) {
                                return;
                            }
                            cu.b(Cdo.this.f5020b.a());
                        } catch (Throwable th) {
                            ej.a(th, "cl", "upwr");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            ej.a(th, "cl", "upw");
        }
    }

    public final void c() {
        try {
            if (this.f5022d == null || this.f5021c == null) {
                return;
            }
            cu.a(this.f5021c.a());
        } catch (Throwable th) {
            ej.a(th, "cl", "upc");
        }
    }

    public final void d() {
        try {
            if (System.currentTimeMillis() - k < DateUtil.MINUTE) {
                return;
            }
            ab.d().submit(new b(2));
        } catch (Throwable unused) {
        }
    }
}