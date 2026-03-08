package com.loc;

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
import com.realsil.sdk.dfu.model.DfuConfig;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CgiManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dw {
    private static int r = 0;
    private static boolean v = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f5068a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    TelephonyManager f5071d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    CellLocation f5073f;
    String i;
    private Context l;
    private du p;
    private Object q;
    private TelephonyManager.CellInfoCallback t;
    private Cdo w;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<dv> f5069b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<cw> f5070c = new ArrayList<>();
    private String m = null;
    private ArrayList<dv> n = new ArrayList<>();
    private int o = -113;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f5072e = 0;
    private long s = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f5074g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    PhoneStateListener f5075h = null;
    private boolean u = false;
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
            dw.d(dw.this);
            CellLocation cellLocationA = dw.this.a(list);
            if (cellLocationA != null) {
                dw dwVar = dw.this;
                dwVar.f5073f = cellLocationA;
                dwVar.f5074g = true;
                dwVar.a(false);
                dw.this.s = ep.b();
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
                if (dw.this.w != null) {
                    dw.this.w.c();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellLocationChanged(CellLocation cellLocation) {
            try {
                if (dw.this.a(cellLocation)) {
                    dw.this.f5073f = cellLocation;
                    dw.this.f5074g = true;
                    dw.this.a(false);
                    dw.this.s = ep.b();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onServiceStateChanged(ServiceState serviceState) {
            try {
                int state = serviceState.getState();
                if (state == 0) {
                    dw.this.a(false, false);
                } else {
                    if (state != 1) {
                        return;
                    }
                    dw.this.i();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthChanged(int i) {
            int iA = -113;
            try {
                int i2 = dw.this.f5068a;
                if (i2 == 1 || i2 == 2) {
                    iA = ep.a(i);
                }
                dw.this.b(iA);
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
                int i = dw.this.f5068a;
                if (i == 1) {
                    iA = ep.a(signalStrength.getGsmSignalStrength());
                } else if (i == 2) {
                    iA = signalStrength.getCdmaDbm();
                }
                dw.this.b(iA);
                if (dw.this.w != null) {
                    dw.this.w.c();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public dw(Context context) {
        Object objA;
        this.f5068a = 0;
        this.f5071d = null;
        this.p = null;
        this.i = null;
        this.l = context;
        if (this.f5071d == null) {
            this.f5071d = (TelephonyManager) ep.a(this.l, "phone");
        }
        TelephonyManager telephonyManager = this.f5071d;
        if (telephonyManager != null) {
            try {
                this.f5068a = c(telephonyManager.getCellLocation());
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            } catch (Throwable th) {
                this.i = null;
                ej.a(th, "CgiManager", "CgiManager");
                this.f5068a = 0;
            }
            try {
                int i = r;
                if (i != 1) {
                    objA = ep.a(i != 2 ? this.l : this.l, "phone2");
                } else {
                    objA = ep.a(this.l, "phone_msim");
                }
                this.q = objA;
            } catch (Throwable unused) {
            }
            ab.d().submit(new Runnable() { // from class: com.loc.dw.1
                @Override // java.lang.Runnable
                public final void run() {
                    synchronized (dw.this.y) {
                        if (!dw.this.x) {
                            dw.c(dw.this);
                        }
                    }
                }
            });
        }
        this.p = new du();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        CellLocation cellLocation;
        if (obj == null) {
            return null;
        }
        try {
            Object objA = em.a(obj, str, objArr);
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
            com.loc.dv r2 = r10.a(r3)     // Catch: java.lang.Throwable -> L21
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
            int r5 = r2.f5064e     // Catch: java.lang.Throwable -> L52
            int r6 = r2.f5065f     // Catch: java.lang.Throwable -> L52
            int r7 = r2.f5066g     // Catch: java.lang.Throwable -> L52
            int r8 = r2.f5067h     // Catch: java.lang.Throwable -> L52
            r3 = r11
            r3.setCellLocationData(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L52
            goto L52
        L3f:
            android.telephony.gsm.GsmCellLocation r11 = new android.telephony.gsm.GsmCellLocation     // Catch: java.lang.Throwable -> L4c
            r11.<init>()     // Catch: java.lang.Throwable -> L4c
            int r1 = r2.f5062c     // Catch: java.lang.Throwable -> L4f
            int r2 = r2.f5063d     // Catch: java.lang.Throwable -> L4f
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
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dw.a(java.util.List):android.telephony.CellLocation");
    }

    private static dv a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        dv dvVar = new dv(i, z);
        dvVar.f5060a = i2;
        dvVar.f5061b = i3;
        dvVar.f5062c = i4;
        dvVar.f5063d = i5;
        dvVar.j = i6;
        return dvVar;
    }

    private dv a(CellInfo cellInfo) {
        CellInfoLte cellInfoLte;
        dv dvVarA;
        int pci;
        boolean zIsRegistered = cellInfo.isRegistered();
        if (cellInfo instanceof CellInfoCdma) {
            dvVarA = a((CellInfoCdma) cellInfo, zIsRegistered);
        } else if (cellInfo instanceof CellInfoGsm) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            if (cellInfoGsm == null || cellInfoGsm.getCellIdentity() == null) {
                return null;
            }
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            if (!c(cellIdentity.getLac()) || !d(cellIdentity.getCid())) {
                return null;
            }
            dvVarA = a(1, zIsRegistered, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
        } else {
            if (cellInfo instanceof CellInfoWcdma) {
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                if (cellInfoWcdma == null || cellInfoWcdma.getCellIdentity() == null) {
                    return null;
                }
                CellIdentityWcdma cellIdentity2 = cellInfoWcdma.getCellIdentity();
                if (!c(cellIdentity2.getLac()) || !d(cellIdentity2.getCid())) {
                    return null;
                }
                dvVarA = a(4, zIsRegistered, cellIdentity2.getMcc(), cellIdentity2.getMnc(), cellIdentity2.getLac(), cellIdentity2.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
                pci = cellIdentity2.getPsc();
            } else {
                if (!(cellInfo instanceof CellInfoLte) || (cellInfoLte = (CellInfoLte) cellInfo) == null || cellInfoLte.getCellIdentity() == null) {
                    return null;
                }
                CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                if (!c(cellIdentity3.getTac()) || !d(cellIdentity3.getCi())) {
                    return null;
                }
                dvVarA = a(3, zIsRegistered, cellIdentity3.getMcc(), cellIdentity3.getMnc(), cellIdentity3.getTac(), cellIdentity3.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
                pci = cellIdentity3.getPci();
            }
            dvVarA.o = pci;
        }
        return dvVarA;
    }

    private dv a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        if (cellInfoCdma != null && cellInfoCdma.getCellIdentity() != null) {
            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
            if (cellIdentity.getSystemId() > 0 && cellIdentity.getNetworkId() >= 0 && cellIdentity.getBasestationId() >= 0) {
                CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                String[] strArrA = ep.a(this.f5071d);
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
                dv dvVarA = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                dvVarA.f5066g = cellIdentity2.getSystemId();
                dvVarA.f5067h = cellIdentity2.getNetworkId();
                dvVarA.i = cellIdentity2.getBasestationId();
                dvVarA.f5064e = cellIdentity2.getLatitude();
                dvVarA.f5065f = cellIdentity2.getLongitude();
                return dvVarA;
            }
        }
        return null;
    }

    private static dv a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            dv dvVar = new dv(1, false);
            dvVar.f5060a = Integer.parseInt(strArr[0]);
            dvVar.f5061b = Integer.parseInt(strArr[1]);
            dvVar.f5062c = em.b(neighboringCellInfo, "getLac", new Object[0]);
            dvVar.f5063d = neighboringCellInfo.getCid();
            dvVar.j = ep.a(neighboringCellInfo.getRssi());
            return dvVar;
        } catch (Throwable th) {
            ej.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private synchronized void a(CellLocation cellLocation, String[] strArr, boolean z) {
        dv dvVarA;
        if (cellLocation != null) {
            if (this.f5071d != null) {
                this.f5069b.clear();
                if (b(cellLocation)) {
                    this.f5068a = 1;
                    ArrayList<dv> arrayList = this.f5069b;
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    dv dvVar = new dv(1, true);
                    dvVar.f5060a = ep.g(strArr[0]);
                    dvVar.f5061b = ep.g(strArr[1]);
                    dvVar.f5062c = gsmCellLocation.getLac();
                    dvVar.f5063d = gsmCellLocation.getCid();
                    dvVar.j = this.o;
                    arrayList.add(dvVar);
                    if (z) {
                        return;
                    }
                    if (Build.VERSION.SDK_INT <= 28) {
                        List<NeighboringCellInfo> list = (List) em.a(this.f5071d, "getNeighboringCellInfo", new Object[0]);
                        if (list != null && !list.isEmpty()) {
                            for (NeighboringCellInfo neighboringCellInfo : list) {
                                if (neighboringCellInfo != null && a(neighboringCellInfo.getLac(), neighboringCellInfo.getCid()) && (dvVarA = a(neighboringCellInfo, strArr)) != null && !this.f5069b.contains(dvVarA)) {
                                    this.f5069b.add(dvVarA);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z) {
        CellLocation cellLocation;
        boolean z2;
        GsmCellLocation gsmCellLocation;
        String[] strArrA = ep.a(this.f5071d);
        int iC = c(this.f5073f);
        boolean z3 = true;
        if (iC == 1) {
            a(this.f5073f, strArrA, z);
            return;
        }
        if (iC == 2 && (cellLocation = this.f5073f) != null) {
            this.f5069b.clear();
            try {
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
                        z2 = false;
                    } else {
                        a(gsmCellLocation, strArrA, z);
                        z2 = true;
                    }
                    if (z2) {
                        return;
                    }
                }
                if (b(cellLocation)) {
                    this.f5068a = 2;
                    dv dvVar = new dv(2, true);
                    dvVar.f5060a = Integer.parseInt(strArrA[0]);
                    dvVar.f5061b = Integer.parseInt(strArrA[1]);
                    dvVar.f5066g = em.b(cellLocation, "getSystemId", new Object[0]);
                    dvVar.f5067h = em.b(cellLocation, "getNetworkId", new Object[0]);
                    dvVar.i = em.b(cellLocation, "getBaseStationId", new Object[0]);
                    dvVar.j = this.o;
                    dvVar.f5064e = em.b(cellLocation, "getBaseStationLatitude", new Object[0]);
                    dvVar.f5065f = em.b(cellLocation, "getBaseStationLongitude", new Object[0]);
                    if (dvVar.f5064e != dvVar.f5065f || dvVar.f5064e <= 0) {
                        z3 = false;
                    }
                    if (dvVar.f5064e < 0 || dvVar.f5065f < 0 || dvVar.f5064e == Integer.MAX_VALUE || dvVar.f5065f == Integer.MAX_VALUE || z3) {
                        dvVar.f5064e = 0;
                        dvVar.f5065f = 0;
                    }
                    if (!this.f5069b.contains(dvVar)) {
                        this.f5069b.add(dvVar);
                    }
                }
            } catch (Throwable th) {
                ej.a(th, "CgiManager", "hdlCdmaLocChange");
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
        int i2 = this.f5068a;
        if ((i2 == 1 || i2 == 2) && this.f5069b != null && !this.f5069b.isEmpty()) {
            try {
                this.f5069b.get(0).j = this.o;
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00a6 A[Catch: all -> 0x012f, TRY_LEAVE, TryCatch #6 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:9:0x0011, B:11:0x001b, B:13:0x001f, B:14:0x0026, B:18:0x0036, B:25:0x0046, B:27:0x0050, B:28:0x0054, B:30:0x005a, B:31:0x0063, B:33:0x0071, B:34:0x007d, B:36:0x0082, B:41:0x008b, B:45:0x0093, B:44:0x0090, B:49:0x009c, B:51:0x00a6, B:81:0x0113, B:83:0x0117, B:85:0x0127), top: B:106:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d9 A[Catch: all -> 0x0113, TryCatch #1 {all -> 0x0113, blocks: (B:52:0x00a9, B:54:0x00b1, B:56:0x00b5, B:57:0x00b9, B:58:0x00bf, B:65:0x00d1, B:68:0x00d9, B:70:0x00de, B:78:0x0104, B:80:0x010a, B:63:0x00c8), top: B:96:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00de A[Catch: all -> 0x0113, TRY_LEAVE, TryCatch #1 {all -> 0x0113, blocks: (B:52:0x00a9, B:54:0x00b1, B:56:0x00b5, B:57:0x00b9, B:58:0x00bf, B:65:0x00d1, B:68:0x00d9, B:70:0x00de, B:78:0x0104, B:80:0x010a, B:63:0x00c8), top: B:96:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0117 A[Catch: all -> 0x012f, TryCatch #6 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:9:0x0011, B:11:0x001b, B:13:0x001f, B:14:0x0026, B:18:0x0036, B:25:0x0046, B:27:0x0050, B:28:0x0054, B:30:0x005a, B:31:0x0063, B:33:0x0071, B:34:0x007d, B:36:0x0082, B:41:0x008b, B:45:0x0093, B:44:0x0090, B:49:0x009c, B:51:0x00a6, B:81:0x0113, B:83:0x0117, B:85:0x0127), top: B:106:0x0001, inners: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void b(boolean r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dw.b(boolean, boolean):void");
    }

    private boolean b(CellLocation cellLocation) {
        boolean zA = a(cellLocation);
        if (!zA) {
            this.f5068a = 0;
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
            ej.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    static /* synthetic */ void c(dw dwVar) {
        int iB;
        dwVar.f5075h = dwVar.new b();
        try {
            iB = em.b("android.telephony.PhoneStateListener", "LISTEN_SIGNAL_STRENGTHS");
        } catch (Throwable unused) {
            iB = 0;
        }
        if (iB == 0) {
            try {
                dwVar.f5071d.listen(dwVar.f5075h, 16);
            } catch (Throwable unused2) {
            }
        } else {
            try {
                dwVar.f5071d.listen(dwVar.f5075h, iB | 16);
            } catch (Throwable unused3) {
            }
        }
    }

    private static boolean c(int i) {
        return (i == -1 || i == 0 || i > 65535) ? false : true;
    }

    private static boolean d(int i) {
        return (i == -1 || i == 0 || i == 65535 || i >= 268435455) ? false : true;
    }

    static /* synthetic */ boolean d(dw dwVar) {
        dwVar.u = true;
        return true;
    }

    public static int j() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            r = 1;
        } catch (Throwable unused) {
        }
        if (r == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                r = 2;
            } catch (Throwable unused2) {
            }
        }
        return r;
    }

    private CellLocation o() {
        TelephonyManager telephonyManager = this.f5071d;
        if (telephonyManager != null) {
            try {
                CellLocation cellLocation = telephonyManager.getCellLocation();
                this.i = null;
                if (b(cellLocation)) {
                    this.f5073f = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            } catch (Throwable th) {
                this.i = null;
                ej.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    private synchronized void p() {
        int i = this.f5068a & 3;
        if (i != 1) {
            if (i == 2 && this.f5069b.isEmpty()) {
                this.f5068a = 0;
            }
        } else if (this.f5069b.isEmpty()) {
            this.f5068a = 0;
        }
    }

    private CellLocation q() {
        TelephonyManager telephonyManager = this.f5071d;
        CellLocation cellLocationA = null;
        if (telephonyManager == null) {
            return null;
        }
        if (ep.c() >= 18) {
            try {
                cellLocationA = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e2) {
                this.i = e2.getMessage();
            }
        }
        if (cellLocationA != null) {
            return cellLocationA;
        }
        CellLocation cellLocationO = o();
        if (b(cellLocationO)) {
            return cellLocationO;
        }
        CellLocation cellLocationA2 = a(telephonyManager, "getCellLocationExt", 1);
        return cellLocationA2 != null ? cellLocationA2 : a(telephonyManager, "getCellLocationGemini", 1);
    }

    private CellLocation r() {
        if (!v) {
            v = true;
        }
        Object obj = this.q;
        CellLocation cellLocationA = null;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> clsS = s();
            if (clsS.isInstance(obj)) {
                Object objCast = clsS.cast(obj);
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
            ej.a(th, "CgiManager", "getSim2Cgi");
        }
        return cellLocationA;
    }

    private static Class<?> s() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        int i = r;
        try {
            return systemClassLoader.loadClass(i != 0 ? i != 1 ? i != 2 ? null : "android.telephony.TelephonyManager2" : "android.telephony.MSimTelephonyManager" : "android.telephony.TelephonyManager");
        } catch (Throwable th) {
            ej.a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    public final List<cw> a() {
        Object obj;
        Object obj2;
        ArrayList arrayList = new ArrayList();
        List<CellInfo> allCellInfo = this.f5071d.getAllCellInfo();
        if (Build.VERSION.SDK_INT >= 17 && allCellInfo != null) {
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                    cx cxVar = new cx(cellInfo.isRegistered(), true);
                    cxVar.m = cellIdentity.getLatitude();
                    cxVar.n = cellIdentity.getLongitude();
                    cxVar.j = cellIdentity.getSystemId();
                    cxVar.k = cellIdentity.getNetworkId();
                    cxVar.l = cellIdentity.getBasestationId();
                    cxVar.f4942d = cellInfoCdma.getCellSignalStrength().getAsuLevel();
                    cxVar.f4941c = cellInfoCdma.getCellSignalStrength().getCdmaDbm();
                    obj = cxVar;
                } else {
                    if (cellInfo instanceof CellInfoGsm) {
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                        CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                        cy cyVar = new cy(cellInfo.isRegistered(), true);
                        cyVar.f4939a = String.valueOf(cellIdentity2.getMcc());
                        cyVar.f4940b = String.valueOf(cellIdentity2.getMnc());
                        cyVar.j = cellIdentity2.getLac();
                        cyVar.k = cellIdentity2.getCid();
                        cyVar.f4941c = cellInfoGsm.getCellSignalStrength().getDbm();
                        cyVar.f4942d = cellInfoGsm.getCellSignalStrength().getAsuLevel();
                        obj2 = cyVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            cyVar.m = cellIdentity2.getArfcn();
                            cyVar.n = cellIdentity2.getBsic();
                            obj2 = cyVar;
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                        cz czVar = new cz(cellInfo.isRegistered());
                        czVar.f4939a = String.valueOf(cellIdentity3.getMcc());
                        czVar.f4940b = String.valueOf(cellIdentity3.getMnc());
                        czVar.l = cellIdentity3.getPci();
                        czVar.f4942d = cellInfoLte.getCellSignalStrength().getAsuLevel();
                        czVar.k = cellIdentity3.getCi();
                        czVar.j = cellIdentity3.getTac();
                        czVar.n = cellInfoLte.getCellSignalStrength().getTimingAdvance();
                        czVar.f4941c = cellInfoLte.getCellSignalStrength().getDbm();
                        obj = czVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            czVar.m = cellIdentity3.getEarfcn();
                            obj = czVar;
                        }
                    } else if (Build.VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma)) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                        da daVar = new da(cellInfo.isRegistered(), true);
                        daVar.f4939a = String.valueOf(cellIdentity4.getMcc());
                        daVar.f4940b = String.valueOf(cellIdentity4.getMnc());
                        daVar.j = cellIdentity4.getLac();
                        daVar.k = cellIdentity4.getCid();
                        daVar.l = cellIdentity4.getPsc();
                        daVar.f4942d = cellInfoWcdma.getCellSignalStrength().getAsuLevel();
                        daVar.f4941c = cellInfoWcdma.getCellSignalStrength().getDbm();
                        obj2 = daVar;
                        if (Build.VERSION.SDK_INT >= 24) {
                            daVar.m = cellIdentity4.getUarfcn();
                            obj2 = daVar;
                        }
                    }
                    arrayList.add(obj2);
                }
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void a(Cdo cdo) {
        this.w = cdo;
    }

    public final synchronized void a(boolean z, boolean z2) {
        try {
            this.j = ep.a(this.l);
            boolean z3 = false;
            if (!this.j && ep.b() - this.f5072e >= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                z3 = true;
            }
            if (z3 || this.f5069b.isEmpty()) {
                b(z, z2);
                this.f5072e = ep.b();
            }
            if (this.j) {
                i();
            } else {
                p();
            }
        } catch (SecurityException e2) {
            this.i = e2.getMessage();
        } catch (Throwable th) {
            ej.a(th, "CgiManager", "refresh");
        }
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
                if (em.b(cellLocation, "getSystemId", new Object[0]) > 0 && em.b(cellLocation, "getNetworkId", new Object[0]) >= 0) {
                    if (em.b(cellLocation, "getBaseStationId", new Object[0]) >= 0) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                str = "cgiUseful Cgi.I_CDMA_T";
            }
        }
        ej.a(th, "CgiManager", str);
        return true;
    }

    public final synchronized ArrayList<dv> b() {
        return this.f5069b;
    }

    public final ArrayList<dv> c() {
        return this.n;
    }

    public final synchronized dv d() {
        if (this.j) {
            return null;
        }
        ArrayList<dv> arrayList = this.f5069b;
        if (arrayList.size() <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    public final int e() {
        return this.f5068a;
    }

    public final int f() {
        return this.f5068a & 3;
    }

    public final TelephonyManager g() {
        return this.f5071d;
    }

    public final void h() {
        PhoneStateListener phoneStateListener;
        this.p.a();
        this.s = 0L;
        synchronized (this.y) {
            this.x = true;
        }
        TelephonyManager telephonyManager = this.f5071d;
        if (telephonyManager != null && (phoneStateListener = this.f5075h) != null) {
            try {
                telephonyManager.listen(phoneStateListener, 0);
            } catch (Throwable th) {
                ej.a(th, "CgiManager", "destroy");
            }
        }
        this.f5075h = null;
        this.o = -113;
        this.f5071d = null;
        this.q = null;
    }

    final synchronized void i() {
        this.i = null;
        this.f5073f = null;
        this.f5068a = 0;
        this.f5069b.clear();
        this.n.clear();
    }

    public final String k() {
        return this.i;
    }

    public final String l() {
        return this.m;
    }

    public final synchronized String m() {
        if (this.j) {
            i();
        }
        if (this.k == null) {
            this.k = new StringBuilder();
        } else {
            this.k.delete(0, this.k.length());
        }
        if ((this.f5068a & 3) == 1) {
            for (int i = 1; i < this.f5069b.size(); i++) {
                StringBuilder sb = this.k;
                sb.append("#");
                sb.append(this.f5069b.get(i).f5061b);
                StringBuilder sb2 = this.k;
                sb2.append("|");
                sb2.append(this.f5069b.get(i).f5062c);
                StringBuilder sb3 = this.k;
                sb3.append("|");
                sb3.append(this.f5069b.get(i).f5063d);
            }
        }
        if (this.k.length() > 0) {
            this.k.deleteCharAt(0);
        }
        return this.k.toString();
    }

    public final boolean n() {
        try {
            if (this.f5071d != null) {
                if (!TextUtils.isEmpty(this.f5071d.getSimOperator())) {
                    return true;
                }
                if (!TextUtils.isEmpty(this.f5071d.getSimCountryIso())) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        try {
            int iA = ep.a(ep.c(this.l));
            return iA == 0 || iA == 4 || iA == 2 || iA == 5 || iA == 3;
        } catch (Throwable unused2) {
            return false;
        }
    }
}