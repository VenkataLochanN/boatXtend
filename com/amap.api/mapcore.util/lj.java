package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CgiManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class lj {
    private static boolean v = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    TelephonyManager f1675d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    CellLocation f1677f;
    private Context l;
    private lh p;
    private Object q;
    private TelephonyManager.CellInfoCallback t;
    private lg w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1672a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<li> f1673b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<la> f1674c = new ArrayList<>();
    private String m = null;
    private ArrayList<li> n = new ArrayList<>();
    private int o = -113;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f1676e = 0;
    private int r = 0;
    private long s = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f1678g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    PhoneStateListener f1679h = null;
    private boolean u = false;
    String i = null;
    boolean j = false;
    StringBuilder k = null;
    private boolean x = false;
    private Object y = new Object();

    /* JADX INFO: compiled from: CgiManager.java */
    class a extends TelephonyManager.CellInfoCallback {
        a() {
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public final void onCellInfo(List<CellInfo> list) {
            lj.d(lj.this);
            CellLocation cellLocationA = lj.this.a(list);
            if (cellLocationA != null) {
                lj ljVar = lj.this;
                ljVar.f1677f = cellLocationA;
                ljVar.f1678g = true;
                ljVar.r();
                lj.this.s = kk.b();
            }
        }
    }

    /* JADX INFO: compiled from: CgiManager.java */
    class b extends PhoneStateListener {
        b() {
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellInfoChanged(List<CellInfo> list) {
            try {
                if (lj.this.w != null) {
                    lj.this.w.a();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellLocationChanged(CellLocation cellLocation) {
            try {
                if (lj.this.a(cellLocation)) {
                    lj.this.f1677f = cellLocation;
                    lj.this.f1678g = true;
                    lj.this.r();
                    lj.this.s = kk.b();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onServiceStateChanged(ServiceState serviceState) {
            try {
                int state = serviceState.getState();
                if (state == 0) {
                    lj.this.b();
                } else {
                    if (state != 1) {
                        return;
                    }
                    lj.this.i();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthChanged(int i) {
            int iA = -113;
            try {
                int i2 = lj.this.f1672a;
                if (i2 == 1 || i2 == 2) {
                    iA = kk.a(i);
                }
                lj.this.b(iA);
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (signalStrength == null) {
                return;
            }
            int iA = -113;
            try {
                int i = lj.this.f1672a;
                if (i == 1) {
                    iA = kk.a(signalStrength.getGsmSignalStrength());
                } else if (i == 2) {
                    iA = signalStrength.getCdmaDbm();
                }
                lj.this.b(iA);
                if (lj.this.w != null) {
                    lj.this.w.a();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public lj(Context context) {
        this.f1675d = null;
        this.p = null;
        this.l = context;
        if (this.f1675d == null) {
            this.f1675d = (TelephonyManager) kk.a(this.l, "phone");
        }
        k();
        this.p = new lh();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        CellLocation cellLocation;
        if (obj == null) {
            return null;
        }
        try {
            Object objA = ki.a(obj, str, objArr);
            cellLocation = objA != null ? (CellLocation) objA : null;
        } catch (Throwable unused) {
        }
        if (b(cellLocation)) {
            return cellLocation;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0056 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.telephony.CellLocation a(java.util.List<android.telephony.CellInfo> r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            if (r11 == 0) goto L5a
            boolean r1 = r11.isEmpty()     // Catch: java.lang.Throwable -> L57
            if (r1 == 0) goto Lb
            goto L5a
        Lb:
            r1 = 0
            r2 = r0
        Ld:
            int r3 = r11.size()     // Catch: java.lang.Throwable -> L57
            if (r1 >= r3) goto L24
            java.lang.Object r3 = r11.get(r1)     // Catch: java.lang.Throwable -> L57
            android.telephony.CellInfo r3 = (android.telephony.CellInfo) r3     // Catch: java.lang.Throwable -> L57
            if (r3 == 0) goto L21
            com.amap.api.mapcore.util.li r2 = r10.a(r3)     // Catch: java.lang.Throwable -> L21
            if (r2 != 0) goto L24
        L21:
            int r1 = r1 + 1
            goto Ld
        L24:
            if (r2 == 0) goto L4e
            int r11 = r2.k     // Catch: java.lang.Throwable -> L4c
            r1 = 2
            if (r11 != r1) goto L3f
            android.telephony.cdma.CdmaCellLocation r11 = new android.telephony.cdma.CdmaCellLocation     // Catch: java.lang.Throwable -> L4c
            r11.<init>()     // Catch: java.lang.Throwable -> L4c
            int r4 = r2.i     // Catch: java.lang.Throwable -> L52
            int r5 = r2.f1668e     // Catch: java.lang.Throwable -> L52
            int r6 = r2.f1669f     // Catch: java.lang.Throwable -> L52
            int r7 = r2.f1670g     // Catch: java.lang.Throwable -> L52
            int r8 = r2.f1671h     // Catch: java.lang.Throwable -> L52
            r3 = r11
            r3.setCellLocationData(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L52
            goto L52
        L3f:
            android.telephony.gsm.GsmCellLocation r11 = new android.telephony.gsm.GsmCellLocation     // Catch: java.lang.Throwable -> L4c
            r11.<init>()     // Catch: java.lang.Throwable -> L4c
            int r1 = r2.f1666c     // Catch: java.lang.Throwable -> L4f
            int r2 = r2.f1667d     // Catch: java.lang.Throwable -> L4f
            r11.setLacAndCid(r1, r2)     // Catch: java.lang.Throwable -> L4f
            goto L4f
        L4c:
            r11 = r0
            goto L52
        L4e:
            r11 = r0
        L4f:
            r9 = r0
            r0 = r11
            r11 = r9
        L52:
            monitor-exit(r10)
            if (r11 != 0) goto L56
            return r0
        L56:
            return r11
        L57:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        L5a:
            monitor-exit(r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.lj.a(java.util.List):android.telephony.CellLocation");
    }

    private static li a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        li liVar = new li(i, z);
        liVar.f1664a = i2;
        liVar.f1665b = i3;
        liVar.f1666c = i4;
        liVar.f1667d = i5;
        liVar.j = i6;
        return liVar;
    }

    private li a(CellInfo cellInfo) {
        boolean zIsRegistered = cellInfo.isRegistered();
        if (cellInfo instanceof CellInfoCdma) {
            return a((CellInfoCdma) cellInfo, zIsRegistered);
        }
        if (cellInfo instanceof CellInfoGsm) {
            return a((CellInfoGsm) cellInfo, zIsRegistered);
        }
        if (cellInfo instanceof CellInfoWcdma) {
            return a((CellInfoWcdma) cellInfo, zIsRegistered);
        }
        if (cellInfo instanceof CellInfoLte) {
            return a((CellInfoLte) cellInfo, zIsRegistered);
        }
        return null;
    }

    private li a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        if (cellInfoCdma != null && cellInfoCdma.getCellIdentity() != null) {
            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
            if (cellIdentity.getSystemId() > 0 && cellIdentity.getNetworkId() >= 0 && cellIdentity.getBasestationId() >= 0) {
                CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                String[] strArrA = kk.a(this.f1675d);
                int i2 = 0;
                try {
                    i = Integer.parseInt(strArrA[0]);
                    try {
                        i2 = Integer.parseInt(strArrA[1]);
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                    i = 0;
                }
                li liVarA = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                liVarA.f1670g = cellIdentity2.getSystemId();
                liVarA.f1671h = cellIdentity2.getNetworkId();
                liVarA.i = cellIdentity2.getBasestationId();
                liVarA.f1668e = cellIdentity2.getLatitude();
                liVarA.f1669f = cellIdentity2.getLongitude();
                return liVarA;
            }
        }
        return null;
    }

    private static li a(CellInfoGsm cellInfoGsm, boolean z) {
        if (cellInfoGsm != null && cellInfoGsm.getCellIdentity() != null) {
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            if (c(cellIdentity.getLac()) && d(cellIdentity.getCid())) {
                return a(1, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
            }
        }
        return null;
    }

    private static li a(CellInfoLte cellInfoLte, boolean z) {
        if (cellInfoLte != null && cellInfoLte.getCellIdentity() != null) {
            CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
            if (c(cellIdentity.getTac()) && d(cellIdentity.getCi())) {
                li liVarA = a(3, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getTac(), cellIdentity.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
                liVarA.o = cellIdentity.getPci();
                return liVarA;
            }
        }
        return null;
    }

    private static li a(CellInfoWcdma cellInfoWcdma, boolean z) {
        if (cellInfoWcdma != null && cellInfoWcdma.getCellIdentity() != null) {
            CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
            if (c(cellIdentity.getLac()) && d(cellIdentity.getCid())) {
                li liVarA = a(4, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
                liVarA.o = cellIdentity.getPsc();
                return liVarA;
            }
        }
        return null;
    }

    private li a(CellLocation cellLocation, String[] strArr) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        li liVar = new li(1, true);
        liVar.f1664a = kk.d(strArr[0]);
        liVar.f1665b = kk.d(strArr[1]);
        liVar.f1666c = gsmCellLocation.getLac();
        liVar.f1667d = gsmCellLocation.getCid();
        liVar.j = this.o;
        return liVar;
    }

    private static li a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            li liVar = new li(1, false);
            liVar.f1664a = Integer.parseInt(strArr[0]);
            liVar.f1665b = Integer.parseInt(strArr[1]);
            liVar.f1666c = ki.b(neighboringCellInfo, "getLac", new Object[0]);
            liVar.f1667d = neighboringCellInfo.getCid();
            liVar.j = kk.a(neighboringCellInfo.getRssi());
            return liVar;
        } catch (Throwable th) {
            kg.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private synchronized void a(CellLocation cellLocation, String[] strArr, boolean z) {
        li liVarA;
        if (cellLocation != null) {
            if (this.f1675d != null) {
                this.f1673b.clear();
                if (b(cellLocation)) {
                    this.f1672a = 1;
                    this.f1673b.add(a(cellLocation, strArr));
                    if (Build.VERSION.SDK_INT <= 28) {
                        List<NeighboringCellInfo> list = (List) ki.a(this.f1675d, "getNeighboringCellInfo", new Object[0]);
                        if (list != null && !list.isEmpty()) {
                            for (NeighboringCellInfo neighboringCellInfo : list) {
                                if (neighboringCellInfo != null && a(neighboringCellInfo.getLac(), neighboringCellInfo.getCid()) && (liVarA = a(neighboringCellInfo, strArr)) != null && !this.f1673b.contains(liVarA)) {
                                    this.f1673b.add(liVarA);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean a(int i) {
        return i > 0 && i <= 15;
    }

    private static boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > 65535 || i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(int i) {
        if (i == -113) {
            this.o = -113;
            return;
        }
        this.o = i;
        int i2 = this.f1672a;
        if ((i2 == 1 || i2 == 2) && this.f1673b != null && !this.f1673b.isEmpty()) {
            try {
                this.f1673b.get(0).j = this.o;
            } catch (Throwable unused) {
            }
        }
    }

    private void b(CellLocation cellLocation, String[] strArr) {
        boolean z;
        GsmCellLocation gsmCellLocation;
        if (cellLocation == null) {
            return;
        }
        this.f1673b.clear();
        try {
            boolean z2 = true;
            if (this.q != null) {
                try {
                    Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    gsmCellLocation = (GsmCellLocation) declaredField.get(cellLocation);
                } catch (Throwable unused) {
                }
                if (gsmCellLocation == null || !b(gsmCellLocation)) {
                    z = false;
                } else {
                    a((CellLocation) gsmCellLocation, strArr, false);
                    z = true;
                }
                if (z) {
                    return;
                }
            }
            if (b(cellLocation)) {
                this.f1672a = 2;
                li liVar = new li(2, true);
                liVar.f1664a = Integer.parseInt(strArr[0]);
                liVar.f1665b = Integer.parseInt(strArr[1]);
                liVar.f1670g = ki.b(cellLocation, "getSystemId", new Object[0]);
                liVar.f1671h = ki.b(cellLocation, "getNetworkId", new Object[0]);
                liVar.i = ki.b(cellLocation, "getBaseStationId", new Object[0]);
                liVar.j = this.o;
                liVar.f1668e = ki.b(cellLocation, "getBaseStationLatitude", new Object[0]);
                liVar.f1669f = ki.b(cellLocation, "getBaseStationLongitude", new Object[0]);
                if (liVar.f1668e != liVar.f1669f || liVar.f1668e <= 0) {
                    z2 = false;
                }
                if (liVar.f1668e < 0 || liVar.f1669f < 0 || liVar.f1668e == Integer.MAX_VALUE || liVar.f1669f == Integer.MAX_VALUE || z2) {
                    liVar.f1668e = 0;
                    liVar.f1669f = 0;
                }
                if (this.f1673b.contains(liVar)) {
                    return;
                }
                this.f1673b.add(liVar);
            }
        } catch (Throwable th) {
            kg.a(th, "CgiManager", "hdlCdmaLocChange");
        }
    }

    private boolean b(CellLocation cellLocation) {
        boolean zA = a(cellLocation);
        if (!zA) {
            this.f1672a = 0;
        }
        return zA;
    }

    private int c(CellLocation cellLocation) {
        if (this.j || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            kg.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    private static boolean c(int i) {
        return (i == -1 || i == 0 || i > 65535) ? false : true;
    }

    private static boolean d(int i) {
        return (i == -1 || i == 0 || i == 65535 || i >= 268435455) ? false : true;
    }

    static /* synthetic */ boolean d(lj ljVar) {
        ljVar.u = true;
        return true;
    }

    private void k() {
        Object objA;
        TelephonyManager telephonyManager = this.f1675d;
        if (telephonyManager == null) {
            return;
        }
        try {
            this.f1672a = c(telephonyManager.getCellLocation());
        } catch (SecurityException e2) {
            this.i = e2.getMessage();
        } catch (Throwable th) {
            this.i = null;
            kg.a(th, "CgiManager", "CgiManager");
            this.f1672a = 0;
        }
        try {
            this.r = w();
            int i = this.r;
            if (i != 1) {
                objA = kk.a(i != 2 ? this.l : this.l, "phone2");
            } else {
                objA = kk.a(this.l, "phone_msim");
            }
            this.q = objA;
        } catch (Throwable unused) {
        }
        hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.lj.1
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (lj.this.y) {
                    if (!lj.this.x) {
                        lj.this.l();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        int iB;
        this.f1679h = new b();
        try {
            iB = ki.b("android.telephony.PhoneStateListener", "LISTEN_SIGNAL_STRENGTHS");
        } catch (Throwable unused) {
            iB = 0;
        }
        if (iB == 0) {
            try {
                this.f1675d.listen(this.f1679h, 16);
            } catch (Throwable unused2) {
            }
        } else {
            try {
                this.f1675d.listen(this.f1679h, iB | 16);
            } catch (Throwable unused3) {
            }
        }
    }

    private CellLocation m() {
        TelephonyManager telephonyManager = this.f1675d;
        if (telephonyManager != null) {
            try {
                CellLocation cellLocation = telephonyManager.getCellLocation();
                this.i = null;
                if (b(cellLocation)) {
                    this.f1677f = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            } catch (Throwable th) {
                this.i = null;
                kg.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    private boolean n() {
        return !this.j && kk.b() - this.f1676e >= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
    }

    private void o() {
        i();
    }

    private synchronized void p() {
        int iF = f();
        if (iF != 1) {
            if (iF == 2 && this.f1673b.isEmpty()) {
                this.f1672a = 0;
            }
        } else if (this.f1673b.isEmpty()) {
            this.f1672a = 0;
        }
    }

    private synchronized void q() {
        if (!this.j && this.f1675d != null) {
            if (Build.VERSION.SDK_INT >= 29 && this.l.getApplicationInfo().targetSdkVersion >= 29) {
                if (this.t == null) {
                    this.t = new a();
                }
                this.f1675d.requestCellInfoUpdate(hn.d(), this.t);
            }
            CellLocation cellLocationS = s();
            if (!b(cellLocationS)) {
                cellLocationS = t();
            }
            if (b(cellLocationS)) {
                this.f1677f = cellLocationS;
                this.s = kk.b();
            } else if (kk.b() - this.s > DateUtil.MINUTE) {
                this.f1677f = null;
                this.f1673b.clear();
                this.n.clear();
            }
        }
        this.f1678g = true;
        if (b(this.f1677f)) {
            r();
        }
        try {
            if (kk.c() >= 18) {
                v();
            }
        } catch (Throwable unused) {
        }
        if (this.f1675d != null) {
            this.m = this.f1675d.getNetworkOperator();
            if (!TextUtils.isEmpty(this.m)) {
                this.f1672a |= 8;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void r() {
        String[] strArrA = kk.a(this.f1675d);
        int iC = c(this.f1677f);
        if (iC == 1) {
            a(this.f1677f, strArrA, false);
        } else {
            if (iC == 2) {
                b(this.f1677f, strArrA);
            }
        }
    }

    private CellLocation s() {
        TelephonyManager telephonyManager = this.f1675d;
        CellLocation cellLocationA = null;
        if (telephonyManager == null) {
            return null;
        }
        if (kk.c() >= 18) {
            try {
                cellLocationA = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            }
        }
        if (cellLocationA != null) {
            return cellLocationA;
        }
        CellLocation cellLocationM = m();
        if (b(cellLocationM)) {
            return cellLocationM;
        }
        CellLocation cellLocationA2 = a(telephonyManager, "getCellLocationExt", 1);
        return cellLocationA2 != null ? cellLocationA2 : a(telephonyManager, "getCellLocationGemini", 1);
    }

    private CellLocation t() {
        if (!v) {
            v = true;
        }
        Object obj = this.q;
        CellLocation cellLocationA = null;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> clsU = u();
            if (clsU.isInstance(obj)) {
                Object objCast = clsU.cast(obj);
                CellLocation cellLocationA2 = a(objCast, "getCellLocation", new Object[0]);
                if (cellLocationA2 != null) {
                    return cellLocationA2;
                }
                CellLocation cellLocationA3 = a(objCast, "getCellLocation", 1);
                if (cellLocationA3 != null) {
                    return cellLocationA3;
                }
                CellLocation cellLocationA4 = a(objCast, "getCellLocationGemini", 1);
                if (cellLocationA4 != null) {
                    return cellLocationA4;
                }
                cellLocationA = a(objCast, "getAllCellInfo", 1);
                if (cellLocationA != null) {
                    return cellLocationA;
                }
            }
        } catch (Throwable th) {
            kg.a(th, "CgiManager", "getSim2Cgi");
        }
        return cellLocationA;
    }

    private Class<?> u() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        int i = this.r;
        try {
            return systemClassLoader.loadClass(i != 0 ? i != 1 ? i != 2 ? null : "android.telephony.TelephonyManager2" : "android.telephony.MSimTelephonyManager" : "android.telephony.TelephonyManager");
        } catch (Throwable th) {
            kg.a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    private void v() {
        List<CellInfo> allCellInfo;
        int size;
        TelephonyManager telephonyManager = this.f1675d;
        if (telephonyManager == null) {
            return;
        }
        ArrayList<li> arrayList = this.n;
        lh lhVar = this.p;
        List<CellInfo> list = null;
        try {
            allCellInfo = telephonyManager.getAllCellInfo();
        } catch (SecurityException e2) {
            e = e2;
        }
        try {
            this.i = null;
        } catch (SecurityException e3) {
            list = allCellInfo;
            e = e3;
            this.i = e.getMessage();
            allCellInfo = list;
        }
        if (allCellInfo != null && (size = allCellInfo.size()) != 0) {
            if (arrayList != null) {
                arrayList.clear();
            }
            for (int i = 0; i < size; i++) {
                CellInfo cellInfo = allCellInfo.get(i);
                if (cellInfo != null) {
                    try {
                        li liVarA = a(cellInfo);
                        if (liVarA != null) {
                            liVarA.l = (short) Math.min(65535L, lhVar.a(liVarA));
                            arrayList.add(liVarA);
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.f1672a |= 4;
        lhVar.a(arrayList);
    }

    private int w() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.r = 1;
        } catch (Throwable unused) {
        }
        if (this.r == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.r = 2;
            } catch (Throwable unused2) {
            }
        }
        return this.r;
    }

    public final List<la> a() {
        Object obj;
        Object obj2;
        ArrayList arrayList = new ArrayList();
        List<CellInfo> allCellInfo = this.f1675d.getAllCellInfo();
        if (Build.VERSION.SDK_INT >= 17) {
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                    lb lbVar = new lb(cellInfo.isRegistered(), true);
                    lbVar.m = cellIdentity.getLatitude();
                    lbVar.n = cellIdentity.getLongitude();
                    lbVar.j = cellIdentity.getSystemId();
                    lbVar.k = cellIdentity.getNetworkId();
                    lbVar.l = cellIdentity.getBasestationId();
                    lbVar.f1646d = cellInfoCdma.getCellSignalStrength().getAsuLevel();
                    lbVar.f1645c = cellInfoCdma.getCellSignalStrength().getCdmaDbm();
                    obj = lbVar;
                } else {
                    if (cellInfo instanceof CellInfoGsm) {
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                        CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                        lc lcVar = new lc(cellInfo.isRegistered(), true);
                        lcVar.f1643a = String.valueOf(cellIdentity2.getMcc());
                        lcVar.f1644b = String.valueOf(cellIdentity2.getMnc());
                        lcVar.j = cellIdentity2.getLac();
                        lcVar.k = cellIdentity2.getCid();
                        lcVar.f1645c = cellInfoGsm.getCellSignalStrength().getDbm();
                        lcVar.f1646d = cellInfoGsm.getCellSignalStrength().getAsuLevel();
                        obj2 = lcVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            lcVar.m = cellIdentity2.getArfcn();
                            lcVar.n = cellIdentity2.getBsic();
                            obj2 = lcVar;
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                        ld ldVar = new ld(cellInfo.isRegistered());
                        ldVar.f1643a = String.valueOf(cellIdentity3.getMcc());
                        ldVar.f1644b = String.valueOf(cellIdentity3.getMnc());
                        ldVar.l = cellIdentity3.getPci();
                        ldVar.f1646d = cellInfoLte.getCellSignalStrength().getAsuLevel();
                        ldVar.k = cellIdentity3.getCi();
                        ldVar.m = cellIdentity3.getEarfcn();
                        ldVar.j = cellIdentity3.getTac();
                        ldVar.n = cellInfoLte.getCellSignalStrength().getTimingAdvance();
                        ldVar.f1645c = cellInfoLte.getCellSignalStrength().getDbm();
                        obj = ldVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            ldVar.m = cellIdentity3.getEarfcn();
                            obj = ldVar;
                        }
                    } else if (Build.VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma)) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                        le leVar = new le(cellInfo.isRegistered(), true);
                        leVar.f1643a = String.valueOf(cellIdentity4.getMcc());
                        leVar.f1644b = String.valueOf(cellIdentity4.getMnc());
                        leVar.j = cellIdentity4.getLac();
                        leVar.k = cellIdentity4.getCid();
                        leVar.l = cellIdentity4.getPsc();
                        leVar.f1646d = cellInfoWcdma.getCellSignalStrength().getAsuLevel();
                        leVar.f1645c = cellInfoWcdma.getCellSignalStrength().getDbm();
                        obj2 = leVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            leVar.m = cellIdentity4.getUarfcn();
                            obj2 = leVar;
                        }
                    }
                    arrayList.add(obj2);
                }
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    final boolean a(CellLocation cellLocation) {
        String str;
        if (cellLocation == null) {
            return false;
        }
        int iC = c(cellLocation);
        if (iC == 1) {
            try {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                return a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
            } catch (Throwable th) {
                th = th;
                str = "cgiUseful Cgi.I_GSM_T";
            }
        } else {
            if (iC != 2) {
                return true;
            }
            try {
                if (ki.b(cellLocation, "getSystemId", new Object[0]) > 0 && ki.b(cellLocation, "getNetworkId", new Object[0]) >= 0) {
                    if (ki.b(cellLocation, "getBaseStationId", new Object[0]) >= 0) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                str = "cgiUseful Cgi.I_CDMA_T";
            }
        }
        kg.a(th, "CgiManager", str);
        return true;
    }

    public final synchronized void b() {
        try {
            try {
                this.j = kk.a(this.l);
                if (n() || this.f1673b.isEmpty()) {
                    q();
                    this.f1676e = kk.b();
                }
                if (this.j) {
                    o();
                } else {
                    p();
                }
            } catch (Throwable th) {
                kg.a(th, "CgiManager", "refresh");
            }
        } catch (SecurityException e2) {
            this.i = e2.getMessage();
        }
    }

    public final synchronized ArrayList<li> c() {
        return this.f1673b;
    }

    public final ArrayList<li> d() {
        return this.n;
    }

    public final int e() {
        return this.f1672a;
    }

    public final int f() {
        return this.f1672a & 3;
    }

    public final TelephonyManager g() {
        return this.f1675d;
    }

    public final void h() {
        PhoneStateListener phoneStateListener;
        this.p.a();
        this.s = 0L;
        synchronized (this.y) {
            this.x = true;
        }
        TelephonyManager telephonyManager = this.f1675d;
        if (telephonyManager != null && (phoneStateListener = this.f1679h) != null) {
            try {
                telephonyManager.listen(phoneStateListener, 0);
            } catch (Throwable th) {
                kg.a(th, "CgiManager", "destroy");
            }
        }
        this.f1679h = null;
        this.o = -113;
        this.f1675d = null;
        this.q = null;
    }

    final synchronized void i() {
        this.i = null;
        this.f1677f = null;
        this.f1672a = 0;
        this.f1673b.clear();
        this.n.clear();
    }

    public final String j() {
        return this.m;
    }
}