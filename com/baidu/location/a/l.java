package com.baidu.location.a;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.a.i;
import com.baidu.location.d.h;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l extends i {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f2149g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static l f2150h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i.b f2151e;
    private double y;
    private double z;
    private boolean i = true;
    private String j = null;
    private BDLocation k = null;
    private BDLocation l = null;
    private com.baidu.location.e.h m = null;
    private com.baidu.location.e.a n = null;
    private com.baidu.location.e.h o = null;
    private com.baidu.location.e.a p = null;
    private boolean q = true;
    private volatile boolean r = false;
    private boolean s = false;
    private long t = 0;
    private long u = 0;
    private Address v = null;
    private String w = null;
    private List<Poi> x = null;
    private boolean A = false;
    private long B = 0;
    private long C = 0;
    private a D = null;
    private boolean E = false;
    private boolean F = false;
    private boolean G = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f2152f = new i.a();
    private boolean H = false;
    private boolean I = false;
    private b J = null;
    private boolean K = false;
    private int L = 0;
    private long M = 0;
    private boolean N = false;
    private boolean O = true;

    private class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(l lVar, m mVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (l.this.E) {
                l.this.E = false;
                if (l.this.F || com.baidu.location.e.e.a().j()) {
                    return;
                }
                l.this.a(false, false);
            }
        }
    }

    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(l lVar, m mVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (l.this.K) {
                l.this.K = false;
            }
            if (l.this.s) {
                l.this.s = false;
                l.this.h(null);
            }
        }
    }

    private l() {
        this.f2151e = null;
        this.f2151e = new i.b();
    }

    private boolean a(com.baidu.location.e.a aVar) {
        this.f2114b = com.baidu.location.e.b.a().f();
        if (this.f2114b == aVar) {
            return false;
        }
        if (this.f2114b == null || aVar == null) {
            return true;
        }
        return !aVar.a(this.f2114b);
    }

    private boolean a(com.baidu.location.e.h hVar) {
        this.f2113a = com.baidu.location.e.i.a().p();
        if (hVar == this.f2113a) {
            return false;
        }
        if (this.f2113a == null || hVar == null) {
            return true;
        }
        return !hVar.c(this.f2113a);
    }

    private boolean b(com.baidu.location.e.a aVar) {
        if (aVar == null) {
            return false;
        }
        if (this.p == null) {
            return true;
        }
        return !aVar.a(r0);
    }

    public static synchronized l c() {
        if (f2150h == null) {
            f2150h = new l();
        }
        return f2150h;
    }

    private void c(Message message) {
        if (com.baidu.location.g.k.b()) {
            Log.d(com.baidu.location.g.a.f2452a, "isInforbiddenTime on request location ...");
        }
        if (message.getData().getBoolean("isWaitingLocTag", false)) {
            f2149g = true;
        }
        if (com.baidu.location.indoor.g.a().f()) {
            return;
        }
        int iD = com.baidu.location.a.a.a().d(message);
        if (iD == 1) {
            d(message);
            return;
        }
        if (iD == 2) {
            g(message);
        } else {
            if (iD != 3) {
                throw new IllegalArgumentException(String.format("this type %d is illegal", Integer.valueOf(iD)));
            }
            if (com.baidu.location.e.e.a().j()) {
                e(message);
            }
        }
    }

    private void d(Message message) {
        if (com.baidu.location.e.e.a().j()) {
            e(message);
            n.a().c();
        } else {
            g(message);
            n.a().b();
        }
    }

    private void e(Message message) {
        BDLocation bDLocation = new BDLocation(com.baidu.location.e.e.a().g());
        if (com.baidu.location.g.k.f2506g.equals("all") || com.baidu.location.g.k.f2507h || com.baidu.location.g.k.j) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.z, this.y, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                Address address = this.v;
                if (address != null) {
                    bDLocation.setAddr(address);
                }
                String str = this.w;
                if (str != null) {
                    bDLocation.setLocationDescribe(str);
                }
                List<Poi> list = this.x;
                if (list != null) {
                    bDLocation.setPoiList(list);
                }
            } else {
                this.A = true;
                g(null);
            }
        }
        this.k = bDLocation;
        this.l = null;
        com.baidu.location.a.a.a().a(bDLocation);
    }

    private void f(Message message) {
        b bVar;
        if (!com.baidu.location.e.i.a().g()) {
            h(message);
            return;
        }
        this.s = true;
        if (this.J == null) {
            this.J = new b(this, null);
        }
        if (this.K && (bVar = this.J) != null) {
            this.f2152f.removeCallbacks(bVar);
        }
        this.f2152f.postDelayed(this.J, 3500L);
        this.K = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(Message message) {
        this.L = 0;
        if (!this.q) {
            f(message);
            this.C = SystemClock.uptimeMillis();
            return;
        }
        this.L = 1;
        this.C = SystemClock.uptimeMillis();
        if (com.baidu.location.e.i.a().k()) {
            f(message);
        } else {
            h(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(android.os.Message r13) {
        /*
            Method dump skipped, instruction units count: 547
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.l.h(android.os.Message):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean k() {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.l.k():boolean");
    }

    private String[] l() {
        boolean z;
        com.baidu.location.a.b bVarA;
        int i;
        String[] strArr = {"", "Location failed beacuse we can not get any loc information!"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&apl=");
        int iB = com.baidu.location.g.k.b(com.baidu.location.f.getServiceContext());
        String str = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        if (iB == 1) {
            strArr[1] = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        }
        stringBuffer.append(iB);
        String strD = com.baidu.location.g.k.d(com.baidu.location.f.getServiceContext());
        if (strD.contains("0|0|")) {
            strArr[1] = "Location failed beacuse we can not get any loc information without any location permission!";
        }
        stringBuffer.append(strD);
        if (Build.VERSION.SDK_INT >= 23) {
            stringBuffer.append("&loc=");
            int iC = com.baidu.location.g.k.c(com.baidu.location.f.getServiceContext());
            if (iC == 0) {
                strArr[1] = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
                z = true;
            } else {
                z = false;
            }
            stringBuffer.append(iC);
        } else {
            z = false;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            stringBuffer.append("&lmd=");
            int iC2 = com.baidu.location.g.k.c(com.baidu.location.f.getServiceContext());
            if (iC2 >= 0) {
                stringBuffer.append(iC2);
            }
        }
        String strG = com.baidu.location.e.b.a().g();
        String strH = com.baidu.location.e.i.a().h();
        stringBuffer.append(strH);
        stringBuffer.append(strG);
        stringBuffer.append(com.baidu.location.g.k.e(com.baidu.location.f.getServiceContext()));
        if (iB != 1) {
            if (strD.contains("0|0|")) {
                com.baidu.location.a.b.a().a(62, 4, "Location failed beacuse we can not get any loc information without any location permission!");
            } else if (z) {
                com.baidu.location.a.b.a().a(62, 5, "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!");
            } else if (strG == null || strH == null || !strG.equals("&sim=1") || strH.equals("&wifio=1")) {
                com.baidu.location.a.b.a().a(62, 9, "Location failed beacuse we can not get any loc information!");
            } else {
                bVarA = com.baidu.location.a.b.a();
                i = 6;
                str = "Location failed beacuse we can not get any loc information , you can insert a sim card or open wifi and try again!";
            }
            strArr[0] = stringBuffer.toString();
            return strArr;
        }
        bVarA = com.baidu.location.a.b.a();
        i = 7;
        bVarA.a(62, i, str);
        strArr[0] = stringBuffer.toString();
        return strArr;
    }

    private void m() {
        this.r = false;
        this.F = false;
        this.G = false;
        this.A = false;
        n();
        if (this.O) {
            this.O = false;
        }
    }

    private void n() {
        if (this.k == null || !com.baidu.location.e.i.j()) {
            return;
        }
        x.a().d();
    }

    public Address a(BDLocation bDLocation) {
        if (com.baidu.location.g.k.f2506g.equals("all") || com.baidu.location.g.k.f2507h || com.baidu.location.g.k.j) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.z, this.y, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                Address address = this.v;
                if (address != null) {
                    return address;
                }
            } else {
                this.w = null;
                this.x = null;
                this.A = true;
                this.f2152f.post(new m(this));
            }
        }
        return null;
    }

    @Override // com.baidu.location.a.i
    public void a() {
        BDLocation bDLocation;
        a aVar = this.D;
        if (aVar != null && this.E) {
            this.E = false;
            this.f2152f.removeCallbacks(aVar);
        }
        if (com.baidu.location.e.e.a().j()) {
            BDLocation bDLocation2 = new BDLocation(com.baidu.location.e.e.a().g());
            if (com.baidu.location.g.k.f2506g.equals("all") || com.baidu.location.g.k.f2507h || com.baidu.location.g.k.j) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.z, this.y, bDLocation2.getLatitude(), bDLocation2.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    Address address = this.v;
                    if (address != null) {
                        bDLocation2.setAddr(address);
                    }
                    String str = this.w;
                    if (str != null) {
                        bDLocation2.setLocationDescribe(str);
                    }
                    List<Poi> list = this.x;
                    if (list != null) {
                        bDLocation2.setPoiList(list);
                    }
                }
            }
            com.baidu.location.a.a.a().a(bDLocation2);
        } else {
            if (this.F) {
                m();
                return;
            }
            if (com.baidu.location.d.h.a().d() && com.baidu.location.d.h.a().e()) {
                bDLocation = com.baidu.location.d.h.a().a(com.baidu.location.e.b.a().f(), com.baidu.location.e.i.a().o(), null, h.b.IS_NOT_MIX_MODE, h.a.NEED_TO_LOG);
                if (bDLocation != null && bDLocation.getLocType() == 66) {
                    com.baidu.location.a.a.a().a(bDLocation);
                }
            } else {
                bDLocation = null;
            }
            if (bDLocation == null || bDLocation.getLocType() == 67) {
                if (this.i || this.k == null) {
                    if (com.baidu.location.d.a.a().f2275a) {
                        bDLocation = com.baidu.location.d.a.a().a(false);
                    } else if (bDLocation == null) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(67);
                    }
                    if (bDLocation != null) {
                        com.baidu.location.a.a.a().a(bDLocation);
                        if (bDLocation.getLocType() == 67 && !this.I) {
                            com.baidu.location.a.b.a().a(67, 3, "Offline location failed, please check the net (wifi/cell)!");
                        }
                        boolean z = true;
                        if (com.baidu.location.g.k.f2506g.equals("all") && bDLocation.getAddrStr() == null) {
                            z = false;
                        }
                        if (com.baidu.location.g.k.f2507h && bDLocation.getLocationDescribe() == null) {
                            z = false;
                        }
                        if (!((com.baidu.location.g.k.j && bDLocation.getPoiList() == null) ? false : z)) {
                            bDLocation.setLocType(67);
                        }
                    }
                } else {
                    com.baidu.location.a.a.a().a(this.k);
                }
            }
            this.l = null;
        }
        m();
    }

    @Override // com.baidu.location.a.i
    public void a(Message message) {
        a aVar = this.D;
        if (aVar != null && this.E) {
            this.E = false;
            this.f2152f.removeCallbacks(aVar);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        if (bDLocation != null && bDLocation.getLocType() == 167 && this.I) {
            bDLocation.setLocType(62);
        }
        b(bDLocation);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r9, boolean r10) {
        /*
            r8 = this;
            com.baidu.location.d.h r0 = com.baidu.location.d.h.a()
            boolean r0 = r0.d()
            r1 = 0
            if (r0 == 0) goto L47
            com.baidu.location.d.h r0 = com.baidu.location.d.h.a()
            boolean r0 = r0.g()
            if (r0 == 0) goto L47
            com.baidu.location.d.h r2 = com.baidu.location.d.h.a()
            com.baidu.location.e.b r0 = com.baidu.location.e.b.a()
            com.baidu.location.e.a r3 = r0.f()
            com.baidu.location.e.i r0 = com.baidu.location.e.i.a()
            com.baidu.location.e.h r4 = r0.o()
            r5 = 0
            com.baidu.location.d.h$b r6 = com.baidu.location.d.h.b.IS_NOT_MIX_MODE
            com.baidu.location.d.h$a r7 = com.baidu.location.d.h.a.NEED_TO_LOG
            com.baidu.location.BDLocation r0 = r2.a(r3, r4, r5, r6, r7)
            if (r0 == 0) goto L3c
            int r2 = r0.getLocType()
            r3 = 67
            if (r2 != r3) goto L5b
        L3c:
            if (r9 == 0) goto L5b
            com.baidu.location.d.a r9 = com.baidu.location.d.a.a()
            boolean r9 = r9.f2275a
            if (r9 == 0) goto L5b
            goto L51
        L47:
            if (r9 == 0) goto L5a
            com.baidu.location.d.a r9 = com.baidu.location.d.a.a()
            boolean r9 = r9.f2275a
            if (r9 == 0) goto L5a
        L51:
            com.baidu.location.d.a r9 = com.baidu.location.d.a.a()
            com.baidu.location.BDLocation r0 = r9.a(r1)
            goto L5b
        L5a:
            r0 = 0
        L5b:
            if (r0 == 0) goto L98
            int r9 = r0.getLocType()
            r2 = 66
            if (r9 != r2) goto L98
            r9 = 1
            java.lang.String r2 = com.baidu.location.g.k.f2506g
            java.lang.String r3 = "all"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L77
            java.lang.String r2 = r0.getAddrStr()
            if (r2 != 0) goto L77
            r9 = r1
        L77:
            boolean r2 = com.baidu.location.g.k.f2507h
            if (r2 == 0) goto L82
            java.lang.String r2 = r0.getLocationDescribe()
            if (r2 != 0) goto L82
            r9 = r1
        L82:
            boolean r2 = com.baidu.location.g.k.j
            if (r2 == 0) goto L8d
            java.util.List r2 = r0.getPoiList()
            if (r2 != 0) goto L8d
            r9 = r1
        L8d:
            if (r9 != 0) goto L91
            if (r10 == 0) goto L98
        L91:
            com.baidu.location.a.a r9 = com.baidu.location.a.a.a()
            r9.a(r0)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.l.a(boolean, boolean):void");
    }

    public void b(Message message) {
        if (this.H) {
            c(message);
        }
    }

    public void b(BDLocation bDLocation) {
        String strH;
        int iC;
        com.baidu.location.e.h hVar;
        BDLocation bDLocation2;
        BDLocation bDLocation3 = new BDLocation(bDLocation);
        if (bDLocation.hasAddr()) {
            this.v = bDLocation.getAddress();
            this.y = bDLocation.getLongitude();
            this.z = bDLocation.getLatitude();
        }
        if (bDLocation.getLocationDescribe() != null) {
            this.w = bDLocation.getLocationDescribe();
            this.y = bDLocation.getLongitude();
            this.z = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiList() != null) {
            this.x = bDLocation.getPoiList();
            this.y = bDLocation.getLongitude();
            this.z = bDLocation.getLatitude();
        }
        boolean z = false;
        if (com.baidu.location.e.e.a().j()) {
            BDLocation bDLocation4 = new BDLocation(com.baidu.location.e.e.a().g());
            if (com.baidu.location.g.k.f2506g.equals("all") || com.baidu.location.g.k.f2507h || com.baidu.location.g.k.j) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.z, this.y, bDLocation4.getLatitude(), bDLocation4.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    Address address = this.v;
                    if (address != null) {
                        bDLocation4.setAddr(address);
                    }
                    String str = this.w;
                    if (str != null) {
                        bDLocation4.setLocationDescribe(str);
                    }
                    List<Poi> list = this.x;
                    if (list != null) {
                        bDLocation4.setPoiList(list);
                    }
                }
            }
            com.baidu.location.a.a.a().a(bDLocation4);
            m();
            return;
        }
        if (this.F) {
            float[] fArr2 = new float[2];
            BDLocation bDLocation5 = this.k;
            if (bDLocation5 != null) {
                Location.distanceBetween(bDLocation5.getLatitude(), this.k.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr2);
            }
            if (fArr2[0] > 10.0f) {
                this.k = bDLocation;
                if (!this.G) {
                    this.G = false;
                    com.baidu.location.a.a.a().a(bDLocation);
                }
            } else if (bDLocation.getUserIndoorState() > -1) {
                this.k = bDLocation;
                com.baidu.location.a.a.a().a(bDLocation);
            }
            m();
            return;
        }
        if (bDLocation.getLocType() == 167) {
            com.baidu.location.a.b.a().a(167, 8, "NetWork location failed because baidu location service can not caculate the location!");
        } else if (bDLocation.getLocType() == 161) {
            if (Build.VERSION.SDK_INT >= 19 && ((iC = com.baidu.location.g.k.c(com.baidu.location.f.getServiceContext())) == 0 || iC == 2)) {
                com.baidu.location.a.b.a().a(161, 1, "NetWork location successful, open gps will be better!");
            } else if (bDLocation.getRadius() >= 100.0f && bDLocation.getNetworkLocationType() != null && bDLocation.getNetworkLocationType().equals("cl") && (strH = com.baidu.location.e.i.a().h()) != null && !strH.equals("&wifio=1")) {
                com.baidu.location.a.b.a().a(161, 2, "NetWork location successful, open wifi will be better!");
            }
        }
        String strD = null;
        this.l = null;
        if (bDLocation.getLocType() == 161 && "cl".equals(bDLocation.getNetworkLocationType()) && (bDLocation2 = this.k) != null && bDLocation2.getLocType() == 161 && "wf".equals(this.k.getNetworkLocationType()) && System.currentTimeMillis() - this.u < 30000) {
            this.l = bDLocation;
            z = true;
        }
        com.baidu.location.a.a aVarA = com.baidu.location.a.a.a();
        if (z) {
            aVarA.a(this.k);
        } else {
            aVarA.a(bDLocation);
            this.u = System.currentTimeMillis();
        }
        if (!com.baidu.location.g.k.a(bDLocation)) {
            this.k = null;
        } else if (!z) {
            this.k = bDLocation;
        }
        int iA = com.baidu.location.g.k.a(f2112c, "ssid\":\"", "\"");
        if (iA != Integer.MIN_VALUE && (hVar = this.m) != null) {
            strD = hVar.d(iA);
        }
        this.j = strD;
        if (com.baidu.location.d.h.a().d() && bDLocation.getLocType() == 161 && "cl".equals(bDLocation.getNetworkLocationType()) && b(this.n)) {
            com.baidu.location.d.h.a().a(this.n, null, bDLocation3, h.b.IS_NOT_MIX_MODE, h.a.NO_NEED_TO_LOG);
            this.p = this.n;
        }
        if (com.baidu.location.d.h.a().d() && bDLocation.getLocType() == 161 && "wf".equals(bDLocation.getNetworkLocationType())) {
            com.baidu.location.d.h.a().a(null, this.m, bDLocation3, h.b.IS_NOT_MIX_MODE, h.a.NO_NEED_TO_LOG);
            this.o = this.m;
        }
        if (this.n != null) {
            com.baidu.location.d.a.a().a(f2112c, this.n, this.m, bDLocation3);
        }
        if (com.baidu.location.e.i.j()) {
            com.baidu.location.d.h.a().i();
            com.baidu.location.d.h.a().m();
        }
        m();
    }

    public void c(BDLocation bDLocation) {
        this.k = new BDLocation(bDLocation);
    }

    public void d() {
        this.q = true;
        this.r = false;
        this.H = true;
    }

    public void e() {
        this.r = false;
        this.s = false;
        this.F = false;
        this.G = true;
        j();
        this.H = false;
    }

    public String f() {
        return this.w;
    }

    public List<Poi> g() {
        return this.x;
    }

    public boolean h() {
        return this.i;
    }

    public void i() {
        if (!this.s) {
            com.baidu.location.b.b.a().d();
        } else {
            h(null);
            this.s = false;
        }
    }

    public void j() {
        this.k = null;
    }
}