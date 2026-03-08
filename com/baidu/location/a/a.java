package com.baidu.location.a;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static long f2046c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f2047d = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static a f2048f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ArrayList<C0015a> f2052g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2053h = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2049a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2050b = false;
    private BDLocation i = null;
    private BDLocation j = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2051e = 0;
    private BDLocation k = null;
    private boolean l = false;
    private boolean m = false;
    private b n = null;

    /* JADX INFO: renamed from: com.baidu.location.a.a$a, reason: collision with other inner class name */
    private class C0015a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2054a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Messenger f2055b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public LocationClientOption f2056c = new LocationClientOption();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2057d = 0;

        public C0015a(Message message) {
            this.f2054a = null;
            this.f2055b = null;
            this.f2055b = message.replyTo;
            this.f2054a = message.getData().getString("packName");
            this.f2056c.prodName = message.getData().getString("prodName");
            com.baidu.location.g.b.a().a(this.f2056c.prodName, this.f2054a);
            this.f2056c.coorType = message.getData().getString("coorType");
            this.f2056c.addrType = message.getData().getString("addrType");
            this.f2056c.enableSimulateGps = message.getData().getBoolean("enableSimulateGps", false);
            com.baidu.location.g.k.m = com.baidu.location.g.k.m || this.f2056c.enableSimulateGps;
            if (!com.baidu.location.g.k.f2506g.equals("all")) {
                com.baidu.location.g.k.f2506g = this.f2056c.addrType;
            }
            this.f2056c.openGps = message.getData().getBoolean("openGPS");
            this.f2056c.scanSpan = message.getData().getInt("scanSpan");
            this.f2056c.timeOut = message.getData().getInt("timeOut");
            this.f2056c.priority = message.getData().getInt("priority");
            this.f2056c.location_change_notify = message.getData().getBoolean("location_change_notify");
            this.f2056c.mIsNeedDeviceDirect = message.getData().getBoolean("needDirect", false);
            this.f2056c.isNeedAltitude = message.getData().getBoolean("isneedaltitude", false);
            this.f2056c.isNeedNewVersionRgc = message.getData().getBoolean("isneednewrgc", false);
            com.baidu.location.g.k.i = com.baidu.location.g.k.i || this.f2056c.isNeedNewVersionRgc;
            com.baidu.location.g.k.f2507h = com.baidu.location.g.k.f2507h || message.getData().getBoolean("isneedaptag", false);
            com.baidu.location.g.k.j = com.baidu.location.g.k.j || message.getData().getBoolean("isneedaptagd", false);
            com.baidu.location.g.k.S = message.getData().getFloat("autoNotifyLocSensitivity", 0.5f);
            int i = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
            if (i < com.baidu.location.g.k.ag) {
                com.baidu.location.g.k.ag = i;
            }
            int i2 = message.getData().getInt("autoNotifyMaxInterval", 0);
            if (i2 >= com.baidu.location.g.k.X) {
                com.baidu.location.g.k.X = i2;
            }
            int i3 = message.getData().getInt("autoNotifyMinDistance", 0);
            if (i3 >= com.baidu.location.g.k.Z) {
                com.baidu.location.g.k.Z = i3;
            }
            int i4 = message.getData().getInt("autoNotifyMinTimeInterval", 0);
            if (i4 >= com.baidu.location.g.k.Y) {
                com.baidu.location.g.k.Y = i4;
            }
            if (this.f2056c.mIsNeedDeviceDirect || this.f2056c.isNeedAltitude) {
                n.a().a(this.f2056c.mIsNeedDeviceDirect);
                n.a().b();
            }
            a.this.f2050b = a.this.f2050b || this.f2056c.isNeedAltitude;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i) {
            Message messageObtain = Message.obtain((Handler) null, i);
            try {
                if (this.f2055b != null) {
                    this.f2055b.send(messageObtain);
                }
                this.f2057d = 0;
            } catch (Exception e2) {
                if (e2 instanceof DeadObjectException) {
                    this.f2057d++;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, Bundle bundle) {
            Message messageObtain = Message.obtain((Handler) null, i);
            messageObtain.setData(bundle);
            try {
                if (this.f2055b != null) {
                    this.f2055b.send(messageObtain);
                }
                this.f2057d = 0;
            } catch (Exception e2) {
                if (e2 instanceof DeadObjectException) {
                    this.f2057d++;
                }
                e2.printStackTrace();
            }
        }

        private void a(int i, String str, BDLocation bDLocation) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            bundle.setClassLoader(BDLocation.class.getClassLoader());
            Message messageObtain = Message.obtain((Handler) null, i);
            messageObtain.setData(bundle);
            try {
                if (this.f2055b != null) {
                    this.f2055b.send(messageObtain);
                }
                this.f2057d = 0;
            } catch (Exception e2) {
                if (e2 instanceof DeadObjectException) {
                    this.f2057d++;
                }
            }
        }

        public void a() {
            a(111);
        }

        public void a(BDLocation bDLocation) {
            a(bDLocation, 21);
        }

        public void a(BDLocation bDLocation, int i) {
            String str;
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (com.baidu.location.indoor.g.a().e()) {
                bDLocation2.setIndoorLocMode(true);
            }
            if (i == 21) {
                a(27, "locStr", bDLocation2);
            }
            if (this.f2056c.coorType != null && !this.f2056c.coorType.equals(CoordinateType.GCJ02)) {
                double longitude = bDLocation2.getLongitude();
                double latitude = bDLocation2.getLatitude();
                if (longitude != Double.MIN_VALUE && latitude != Double.MIN_VALUE) {
                    if ((bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals(CoordinateType.GCJ02)) || bDLocation2.getCoorType() == null) {
                        double[] dArrCoorEncrypt = Jni.coorEncrypt(longitude, latitude, this.f2056c.coorType);
                        bDLocation2.setLongitude(dArrCoorEncrypt[0]);
                        bDLocation2.setLatitude(dArrCoorEncrypt[1]);
                        str = this.f2056c.coorType;
                    } else if (bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals(CoordinateType.WGS84)) {
                        double[] dArrCoorEncrypt2 = Jni.coorEncrypt(longitude, latitude, this.f2056c.coorType);
                        bDLocation2.setLongitude(dArrCoorEncrypt2[0]);
                        bDLocation2.setLatitude(dArrCoorEncrypt2[1]);
                        if (!this.f2056c.coorType.equals("bd09ll")) {
                            str = "wgs84mc";
                        }
                    }
                    bDLocation2.setCoorType(str);
                }
            }
            a(i, "locStr", bDLocation2);
        }

        public void b() {
            if (this.f2056c.location_change_notify) {
                a(com.baidu.location.g.k.f2501b ? 54 : 55);
            }
        }
    }

    private class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f2059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2060b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2061c;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2061c) {
                return;
            }
            this.f2060b++;
            this.f2059a.m = false;
        }
    }

    private a() {
        this.f2052g = null;
        this.f2052g = new ArrayList<>();
    }

    private C0015a a(Messenger messenger) {
        ArrayList<C0015a> arrayList = this.f2052g;
        if (arrayList == null) {
            return null;
        }
        for (C0015a c0015a : arrayList) {
            if (c0015a.f2055b.equals(messenger)) {
                return c0015a;
            }
        }
        return null;
    }

    public static a a() {
        if (f2048f == null) {
            f2048f = new a();
        }
        return f2048f;
    }

    private void a(C0015a c0015a) {
        int i;
        if (c0015a == null) {
            return;
        }
        if (a(c0015a.f2055b) != null) {
            i = 14;
        } else {
            this.f2052g.add(c0015a);
            i = 13;
        }
        c0015a.a(i);
    }

    private void b(String str) {
        Intent intent = new Intent("com.baidu.location.flp.log");
        intent.setPackage("com.baidu.baidulocationdemo");
        intent.putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, str);
        intent.putExtra("pack", com.baidu.location.g.b.f2460e);
        intent.putExtra("tag", "state");
        com.baidu.location.f.getServiceContext().sendBroadcast(intent);
    }

    private void f() {
        g();
        e();
    }

    private void g() {
        boolean z = false;
        boolean z2 = false;
        for (C0015a c0015a : this.f2052g) {
            if (c0015a.f2056c.openGps) {
                z2 = true;
            }
            if (c0015a.f2056c.location_change_notify) {
                z = true;
            }
        }
        com.baidu.location.g.k.f2500a = z;
        if (this.f2053h != z2) {
            this.f2053h = z2;
            com.baidu.location.e.e.a().a(this.f2053h);
        }
    }

    public void a(Bundle bundle, int i) {
        Iterator<C0015a> it = this.f2052g.iterator();
        while (it.hasNext()) {
            try {
                C0015a next = it.next();
                next.a(i, bundle);
                if (next.f2057d > 4) {
                    it.remove();
                }
            } catch (Exception unused) {
                return;
            }
        }
    }

    public void a(Message message) {
        if (message == null || message.replyTo == null) {
            return;
        }
        f2046c = System.currentTimeMillis();
        this.f2049a = true;
        com.baidu.location.e.i.a().b();
        a(new C0015a(message));
        f();
        if (this.l) {
            b("start");
            this.f2051e = 0;
        }
    }

    public void a(BDLocation bDLocation) {
        b(bDLocation);
    }

    public void a(String str) {
        c(new BDLocation(str));
    }

    public void a(boolean z) {
        this.f2049a = z;
        f2047d = z ? 1 : 0;
    }

    public void b() {
        this.f2052g.clear();
        this.i = null;
        f();
    }

    public void b(Message message) {
        C0015a c0015aA = a(message.replyTo);
        if (c0015aA != null) {
            this.f2052g.remove(c0015aA);
        }
        n.a().c();
        f();
        if (this.l) {
            b("stop");
            this.f2051e = 0;
        }
    }

    public void b(BDLocation bDLocation) {
        BDLocation bDLocation2;
        try {
            if (bDLocation == null || bDLocation.getLocType() != 161 || j.a().b()) {
                if (!bDLocation.hasAltitude() && this.f2050b && (bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66)) {
                    double d2 = com.baidu.location.b.a.a().a(bDLocation.getLongitude(), bDLocation.getLatitude())[0];
                    com.baidu.location.b.a.a();
                    if (d2 < 9999.0d) {
                        bDLocation.setAltitude(d2);
                    }
                }
                if (bDLocation.getLocType() == 61) {
                    bDLocation.setGpsAccuracyStatus(com.baidu.location.b.a.a().a(bDLocation));
                }
                Iterator<C0015a> it = this.f2052g.iterator();
                while (it.hasNext()) {
                    C0015a next = it.next();
                    next.a(bDLocation);
                    if (next.f2057d > 4) {
                        it.remove();
                    }
                }
            } else {
                if (this.j == null) {
                    this.j = new BDLocation();
                    this.j.setLocType(505);
                }
                Iterator<C0015a> it2 = this.f2052g.iterator();
                while (it2.hasNext()) {
                    C0015a next2 = it2.next();
                    next2.a(this.j);
                    if (next2.f2057d > 4) {
                        it2.remove();
                    }
                }
            }
        } catch (Exception unused) {
        }
        boolean z = l.f2149g;
        if (z) {
            l.f2149g = false;
        }
        if (com.baidu.location.g.k.X >= 10000) {
            if (bDLocation.getLocType() == 61 || bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66) {
                BDLocation bDLocation3 = this.i;
                if (bDLocation3 != null) {
                    float[] fArr = new float[1];
                    Location.distanceBetween(bDLocation3.getLatitude(), this.i.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                    if (fArr[0] <= com.baidu.location.g.k.Z && !z) {
                        return;
                    }
                    this.i = null;
                    bDLocation2 = new BDLocation(bDLocation);
                } else {
                    bDLocation2 = new BDLocation(bDLocation);
                }
                this.i = bDLocation2;
            }
        }
    }

    public void c() {
        Iterator<C0015a> it = this.f2052g.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public void c(BDLocation bDLocation) {
        Address addressA = l.c().a(bDLocation);
        String strF = l.c().f();
        List<Poi> listG = l.c().g();
        if (addressA != null) {
            bDLocation.setAddr(addressA);
        }
        if (strF != null) {
            bDLocation.setLocationDescribe(strF);
        }
        if (listG != null) {
            bDLocation.setPoiList(listG);
        }
        if (com.baidu.location.indoor.g.a().f() && com.baidu.location.indoor.g.a().g() != null) {
            bDLocation.setFloor(com.baidu.location.indoor.g.a().g());
            bDLocation.setIndoorLocMode(true);
            if (com.baidu.location.indoor.g.a().h() != null) {
                bDLocation.setBuildingID(com.baidu.location.indoor.g.a().h());
            }
        }
        a(bDLocation);
        l.c().c(bDLocation);
    }

    public boolean c(Message message) {
        C0015a c0015aA = a(message.replyTo);
        if (c0015aA == null) {
            return false;
        }
        int i = c0015aA.f2056c.scanSpan;
        c0015aA.f2056c.scanSpan = message.getData().getInt("scanSpan", c0015aA.f2056c.scanSpan);
        if (c0015aA.f2056c.scanSpan < 1000) {
            n.a().c();
            this.f2049a = false;
        } else {
            this.f2049a = true;
        }
        if (c0015aA.f2056c.scanSpan > 999 && i < 1000) {
            if (c0015aA.f2056c.mIsNeedDeviceDirect || c0015aA.f2056c.isNeedAltitude) {
                n.a().a(c0015aA.f2056c.mIsNeedDeviceDirect);
                n.a().b();
            }
            this.f2050b = this.f2050b || c0015aA.f2056c.isNeedAltitude;
            z = true;
        }
        c0015aA.f2056c.openGps = message.getData().getBoolean("openGPS", c0015aA.f2056c.openGps);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = c0015aA.f2056c;
        if (string == null || string.equals("")) {
            string = c0015aA.f2056c.coorType;
        }
        locationClientOption.coorType = string;
        String string2 = message.getData().getString("addrType");
        LocationClientOption locationClientOption2 = c0015aA.f2056c;
        if (string2 == null || string2.equals("")) {
            string2 = c0015aA.f2056c.addrType;
        }
        locationClientOption2.addrType = string2;
        if (!com.baidu.location.g.k.f2506g.equals(c0015aA.f2056c.addrType)) {
            l.c().j();
        }
        c0015aA.f2056c.timeOut = message.getData().getInt("timeOut", c0015aA.f2056c.timeOut);
        c0015aA.f2056c.location_change_notify = message.getData().getBoolean("location_change_notify", c0015aA.f2056c.location_change_notify);
        c0015aA.f2056c.priority = message.getData().getInt("priority", c0015aA.f2056c.priority);
        int i2 = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
        if (i2 < com.baidu.location.g.k.ag) {
            com.baidu.location.g.k.ag = i2;
        }
        f();
        return z;
    }

    public int d(Message message) {
        C0015a c0015aA;
        if (message == null || message.replyTo == null || (c0015aA = a(message.replyTo)) == null || c0015aA.f2056c == null) {
            return 1;
        }
        return c0015aA.f2056c.priority;
    }

    public String d() {
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.f2052g.isEmpty()) {
            return "&prod=" + com.baidu.location.g.b.f2461f + ":" + com.baidu.location.g.b.f2460e;
        }
        C0015a c0015a = this.f2052g.get(0);
        if (c0015a.f2056c.prodName != null) {
            stringBuffer.append(c0015a.f2056c.prodName);
        }
        if (c0015a.f2054a != null) {
            stringBuffer.append(":");
            stringBuffer.append(c0015a.f2054a);
            stringBuffer.append("|");
        }
        String string = stringBuffer.toString();
        if (string == null || string.equals("")) {
            return null;
        }
        return "&prod=" + string;
    }

    public int e(Message message) {
        C0015a c0015aA;
        if (message == null || message.replyTo == null || (c0015aA = a(message.replyTo)) == null || c0015aA.f2056c == null) {
            return 1000;
        }
        return c0015aA.f2056c.scanSpan;
    }

    public void e() {
        Iterator<C0015a> it = this.f2052g.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }
}