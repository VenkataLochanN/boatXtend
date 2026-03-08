package com.baidu.location.indoor;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.a.v;
import com.baidu.location.indoor.a;
import com.baidu.location.indoor.m;
import com.baidu.location.indoor.mapversion.a.a;
import com.baidu.location.indoor.mapversion.c.c;
import com.baidu.location.indoor.p;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.ido.common.utils.FileSizeUtil;
import com.ido.life.constants.Constants;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static g f2529g;
    private com.baidu.location.indoor.c<String> E;
    private com.baidu.location.indoor.c<String> G;
    private com.baidu.location.indoor.a Q;
    private p U;
    private p.a V;
    private com.baidu.location.indoor.mapversion.a.a W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2530a;
    private c ad;
    private e ae;
    private f af;
    private b ag;
    private m j;
    private i l;
    private m.a t;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2532c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2533d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2534e = 32;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f2536h = 3000;
    private volatile boolean i = true;
    private C0024g k = null;
    private long m = 0;
    private boolean n = false;
    private boolean o = false;
    private long p = 0;
    private long q = 0;
    private int r = 0;
    private String s = null;
    private int u = 0;
    private int v = 0;
    private String w = null;
    private String x = null;
    private l y = null;
    private String z = null;
    private String A = null;
    private String B = null;
    private int C = 0;
    private int D = 3;
    private int F = 20;
    private double H = 0.0d;
    private double I = 0.0d;
    private double J = 0.4d;
    private boolean K = false;
    private boolean L = true;
    private List<h> M = Collections.synchronizedList(new ArrayList());
    private int N = -1;
    private int O = 0;
    private int P = 0;
    private String R = null;
    private com.baidu.location.indoor.d S = null;
    private boolean T = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SimpleDateFormat f2531b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int X = 2;
    private BDLocation Y = null;
    private boolean Z = false;
    private boolean aa = false;
    private boolean ab = false;
    private boolean ac = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2535f = false;

    class a {
    }

    class b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ArrayList<Double> f2541e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private ArrayList<String> f2542f;
        private Map<String, Integer> i;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2537a = null;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Map<String, Double> f2543g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f2544h = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2538b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f2539c = null;

        public b() {
            this.f2541e = null;
            this.f2542f = null;
            this.i = null;
            this.f2541e = new ArrayList<>();
            this.f2542f = new ArrayList<>();
            this.i = new HashMap();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a(BDLocation bDLocation) {
            if (!bDLocation.getBuildingID().equals(this.f2539c)) {
                this.f2539c = bDLocation.getBuildingID();
                a();
            }
            if (b(bDLocation.getRetFields("p_floor")) != 0) {
                this.f2538b = 0;
                return 1;
            }
            try {
                if (this.f2542f.size() == 0) {
                    for (Map.Entry<String, Double> entry : this.f2543g.entrySet()) {
                        this.f2542f.add(entry.getKey());
                        this.f2541e.add(entry.getValue());
                    }
                } else {
                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<String> it = this.f2542f.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next());
                        arrayList2.add(Double.valueOf(0.0d));
                    }
                    HashMap map = new HashMap();
                    for (Map.Entry<String, Double> entry2 : this.f2543g.entrySet()) {
                        String key = entry2.getKey();
                        Double value = entry2.getValue();
                        map.put(key, value);
                        if (!this.f2542f.contains(key)) {
                            arrayList.add(key);
                            arrayList2.add(value);
                        }
                    }
                    Iterator<Double> it2 = this.f2543g.values().iterator();
                    double dDoubleValue = 0.0d;
                    while (it2.hasNext()) {
                        dDoubleValue += it2.next().doubleValue();
                    }
                    for (int i = 0; i < arrayList.size(); i++) {
                        arrayList2.set(i, map.containsKey(arrayList.get(i)) ? map.get(arrayList.get(i)) : Double.valueOf((1.0d - dDoubleValue) / ((double) (this.f2544h - map.size()))));
                    }
                    ArrayList<Double> arrayList3 = new ArrayList<>();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        arrayList3.add(Double.valueOf(0.0d));
                    }
                    for (int i3 = 0; i3 < this.f2542f.size(); i3++) {
                        Double d2 = this.f2541e.get(i3);
                        ArrayList<Double> arrayListA = a(arrayList, this.f2542f.get(i3));
                        for (int i4 = 0; i4 < arrayList.size(); i4++) {
                            arrayList3.set(i4, Double.valueOf(arrayList3.get(i4).doubleValue() + (d2.doubleValue() * arrayListA.get(i4).doubleValue() * ((Double) arrayList2.get(i4)).doubleValue())));
                        }
                    }
                    this.f2542f = arrayList;
                    this.f2541e = a(arrayList3);
                }
                double d3 = 0.0d;
                String str = null;
                for (int i5 = 0; i5 < this.f2542f.size(); i5++) {
                    if (this.f2541e.get(i5).doubleValue() > d3) {
                        double dDoubleValue2 = this.f2541e.get(i5).doubleValue();
                        str = this.f2542f.get(i5);
                        d3 = dDoubleValue2;
                    }
                }
                this.f2537a = str;
            } catch (Exception unused) {
                this.f2538b = 0;
            }
            this.f2538b = 1;
            return 0;
        }

        private int a(String str) {
            if (this.i.containsKey(str)) {
                return this.i.get(str).intValue();
            }
            int i = 1000;
            try {
                if (str.startsWith("F") || str.startsWith("f")) {
                    i = Integer.parseInt(str.substring(1)) - 1;
                } else if (str.startsWith(FileSizeUtil.UNIT_BIT) || str.startsWith("b")) {
                    i = -Integer.parseInt(str.substring(1));
                }
            } catch (Exception unused) {
            }
            this.i.put(str, Integer.valueOf(i));
            return i;
        }

        private ArrayList<Double> a(ArrayList<Double> arrayList) {
            ArrayList<Double> arrayList2 = new ArrayList<>();
            Double dValueOf = Double.valueOf(0.0d);
            Iterator<Double> it = arrayList.iterator();
            while (it.hasNext()) {
                dValueOf = Double.valueOf(dValueOf.doubleValue() + it.next().doubleValue());
            }
            Iterator<Double> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Double.valueOf(it2.next().doubleValue() / dValueOf.doubleValue()));
            }
            return arrayList2;
        }

        private ArrayList<Double> a(ArrayList<String> arrayList, String str) {
            ArrayList<Double> arrayList2 = new ArrayList<>();
            double[] dArr = {180.0d, 10.0d, 1.0d};
            int iA = a(str);
            Iterator<String> it = arrayList.iterator();
            if (iA == 1000) {
                while (it.hasNext()) {
                    arrayList2.add(Double.valueOf(it.next().equals(str) ? dArr[0] : dArr[2]));
                }
                return arrayList2;
            }
            while (it.hasNext()) {
                int iA2 = a(it.next());
                int i = iA2 == 1000 ? 2 : iA > iA2 ? iA - iA2 : iA2 - iA;
                if (i > 2) {
                    i = 2;
                }
                arrayList2.add(Double.valueOf(dArr[i]));
            }
            return arrayList2;
        }

        private void a() {
            this.f2541e.clear();
            this.f2542f.clear();
            this.i.clear();
        }

        private int b(String str) {
            try {
                String[] strArrSplit = str.split(";");
                if (strArrSplit.length <= 1) {
                    return 1;
                }
                this.f2544h = Integer.parseInt(strArrSplit[0]);
                this.f2543g = new HashMap();
                for (int i = 1; i < strArrSplit.length; i++) {
                    String[] strArrSplit2 = strArrSplit[i].split(":");
                    this.f2543g.put(strArrSplit2[0], Double.valueOf(Double.parseDouble(strArrSplit2[1])));
                }
                return 0;
            } catch (Exception unused) {
                return 1;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String b() {
            return this.f2537a;
        }
    }

    class c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2548d = -0.18181887f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f2549e = -0.90904963f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f2550f = -0.55321634f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private float f2551g = -0.05259979f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private float f2552h = 24.0f;
        private float i = 8.61f;
        private float j = 4.25f;
        private float k = 60.39f;
        private float l = 15.6f;
        private float m = 68.07f;
        private float n = 11.61f;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList<ArrayList<Float>> f2545a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double[] f2546b = null;

        public c() {
        }

        public double a(double d2, double d3, double d4, double d5) {
            double[] dArrA = a(d3, d4);
            double dAbs = Math.abs(d5 - dArrA[0]);
            return dAbs > dArrA[1] * 2.0d ? d2 + dAbs : d2;
        }

        public double[] a(double d2, double d3) {
            return com.baidu.location.b.a.a().a(d2, d3);
        }
    }

    public class d extends Handler {
        public d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (com.baidu.location.f.isServing) {
                int i = message.what;
                if (i == 21) {
                    g.this.a(message);
                    return;
                }
                if (i == 41) {
                    g.this.k();
                } else if (i != 801) {
                    super.dispatchMessage(message);
                } else {
                    g.this.a((BDLocation) message.obj);
                }
            }
        }
    }

    class e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private double f2555b = -1.0d;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f2556c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f2557d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f2558e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f2559f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private long f2560g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private long f2561h = 0;
        private long i = 0;
        private long j = 0;
        private long k = 0;
        private double l = 0.0d;
        private double m = 0.0d;
        private double n = 0.0d;
        private double o = 0.0d;
        private int p = 0;
        private int q = 0;
        private com.baidu.location.e.h r = null;
        private long s = 0;
        private int t = 0;
        private int u = 0;

        public e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.f2555b = -1.0d;
            this.f2556c = 0L;
            this.f2557d = 0L;
            this.f2559f = 0L;
            this.f2560g = 0L;
            this.f2561h = 0L;
            this.i = 0L;
            this.j = 0L;
            this.k = 0L;
            this.l = 0.0d;
            this.m = 0.0d;
            this.p = 0;
            this.q = 0;
            this.r = null;
            this.s = 0L;
            this.t = 0;
            this.u = 0;
            this.f2558e = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(double d2, double d3, double d4, long j) {
            this.j = j;
            this.u = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Location location, boolean z) {
            this.k = System.currentTimeMillis();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double d2 = this.l;
            if (d2 != 0.0d) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.m, d2, latitude, longitude, fArr);
                if (fArr[0] < 20.0f) {
                    this.p++;
                } else {
                    this.p = 0;
                }
                if (fArr[0] < 5.0f) {
                    this.q++;
                } else {
                    this.q = 0;
                }
            }
            this.l = longitude;
            this.m = latitude;
            if (location.hasSpeed() && location.getSpeed() > 3.0f) {
                this.f2561h = System.currentTimeMillis();
            }
            if (location.getAccuracy() >= 50.0f || z) {
                this.t = 0;
            } else {
                this.t++;
            }
            if (this.t <= 10 || System.currentTimeMillis() - this.f2556c <= 30000) {
                return;
            }
            g.this.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(double d2, double d3, double d4) {
            if (!g.this.ae.c()) {
                return true;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.f2559f;
            if (j != 0 && jCurrentTimeMillis - j > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                return true;
            }
            if (this.q >= 5 && d4 < 15.0d && jCurrentTimeMillis - this.f2556c > 20000) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.o, this.n, d3, d2, fArr);
                if (fArr[0] > 30.0f) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(BDLocation bDLocation, double d2, String str) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.i = jCurrentTimeMillis;
            this.f2555b = d2;
            this.n = bDLocation.getLongitude();
            this.o = bDLocation.getLatitude();
            if (str.equals("wifi")) {
                this.f2556c = jCurrentTimeMillis;
            }
            if (str.equals("gps")) {
                this.f2558e = jCurrentTimeMillis;
            }
            if (e()) {
                this.f2559f = jCurrentTimeMillis;
            }
            g gVar = g.this;
            gVar.f2533d = gVar.a(bDLocation.getLongitude(), bDLocation.getLatitude());
            if (g.this.f2533d || g.this.f2532c == 1) {
                this.f2560g = jCurrentTimeMillis;
            }
            long j = this.s;
            if (j != 0 && jCurrentTimeMillis - j > 30000 && jCurrentTimeMillis - this.j < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT && jCurrentTimeMillis - this.k < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                return false;
            }
            if (this.t > 10 && jCurrentTimeMillis - this.f2556c > 30000) {
                return false;
            }
            if (jCurrentTimeMillis - this.f2560g > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT && jCurrentTimeMillis - this.f2556c > 30000) {
                return false;
            }
            long j2 = this.f2559f;
            return j2 == 0 || jCurrentTimeMillis - j2 <= DateUtil.MINUTE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            System.currentTimeMillis();
            if (g.this.n || this.p < 3) {
                return false;
            }
            if (!com.baidu.location.e.i.a().h().contains("&wifio") && g.this.f2532c != 1) {
                return false;
            }
            this.u = 1;
            return true;
        }

        private boolean c() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.f2561h < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT && jCurrentTimeMillis - this.f2556c > 30000) {
                return false;
            }
            if (jCurrentTimeMillis - this.k >= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                return true;
            }
            long j = this.j;
            return j == 0 || jCurrentTimeMillis - j <= 16000 || jCurrentTimeMillis - this.f2556c <= 30000;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            com.baidu.location.e.h hVarQ = com.baidu.location.e.i.a().q();
            if (hVarQ.f2420a == null) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            com.baidu.location.e.h hVar = this.r;
            if (hVar == null || !hVarQ.b(hVar)) {
                if (jCurrentTimeMillis - this.s < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                    this.f2557d = jCurrentTimeMillis;
                }
                this.s = jCurrentTimeMillis;
                this.r = hVarQ;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() {
            if (this.u == 1 || !c() || this.f2555b > 25.0d || System.currentTimeMillis() - this.i > 30000) {
                return false;
            }
            this.f2559f = System.currentTimeMillis();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2562a = 10;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<a> f2564c = Collections.synchronizedList(new ArrayList());

        private class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public double f2565a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public double f2566b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public double f2567c;

            public a(double d2, double d3, double d4) {
                this.f2565a = d2;
                this.f2566b = d3;
                this.f2567c = d4;
            }
        }

        public f() {
        }

        public void a(BDLocation bDLocation) {
            this.f2564c.add(new a(bDLocation.getLongitude(), bDLocation.getLatitude(), g.this.ae.f2555b));
        }

        public String toString() {
            if (this.f2564c.size() == 0) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            double d2 = this.f2564c.get(0).f2565a;
            double d3 = this.f2564c.get(0).f2566b;
            stringBuffer.append(String.format("%.6f:%.6f:%.1f", Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(this.f2564c.get(0).f2567c)));
            int size = (this.f2564c.size() > this.f2562a ? this.f2564c.size() - this.f2562a : 0) + 1;
            while (size < this.f2564c.size()) {
                stringBuffer.append(String.format(";%.0f:%.0f:%.1f", Double.valueOf((this.f2564c.get(size).f2565a - d2) * 1000000.0d), Double.valueOf((this.f2564c.get(size).f2566b - d3) * 1000000.0d), Double.valueOf(this.f2564c.get(size).f2567c)));
                size++;
                d2 = d2;
            }
            return stringBuffer.toString();
        }
    }

    /* JADX INFO: renamed from: com.baidu.location.indoor.g$g, reason: collision with other inner class name */
    class C0024g extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile boolean f2570b = true;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f2571c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f2572d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f2573e = 0;

        C0024g() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f2570b) {
                if (g.this.f2532c != 1 || g.this.f2533d) {
                    g.this.f2536h = 3000L;
                } else {
                    g.this.f2536h = BootloaderScanner.TIMEOUT;
                }
                if (g.this.j.c() == 1) {
                    this.f2572d = System.currentTimeMillis();
                }
                boolean z = System.currentTimeMillis() - this.f2571c > 17500;
                if (System.currentTimeMillis() - this.f2572d < BootloaderScanner.TIMEOUT) {
                    if (System.currentTimeMillis() - this.f2571c > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                        z = true;
                    }
                    if (System.currentTimeMillis() - this.f2571c > g.this.f2536h) {
                        z = true;
                    }
                }
                if (z) {
                    com.baidu.location.e.i.a().i();
                    g.this.j.f();
                    this.f2571c = System.currentTimeMillis();
                    g.this.i = false;
                }
                if (com.baidu.location.e.i.a().r()) {
                    this.f2573e = 0L;
                } else {
                    this.f2573e++;
                    if (this.f2573e >= 10) {
                        this.f2570b = false;
                        g.this.d();
                        return;
                    }
                }
                if (g.this.n && g.this.ae != null && System.currentTimeMillis() - g.this.q > 30000 && System.currentTimeMillis() - g.this.ae.f2559f > 30000) {
                    g.a().d();
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException unused) {
                    this.f2570b = false;
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2574a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double f2575b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f2576c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2577d = 1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f2578e;

        public h(int i, double d2, double d3, double d4) {
            this.f2574a = i;
            this.f2575b = d2;
            this.f2576c = d3;
            this.f2578e = d4;
        }

        public String toString() {
            return this.f2576c == this.f2578e ? String.format("%d:%.1f:%.2f", Integer.valueOf(this.f2577d), Double.valueOf(this.f2576c), Double.valueOf(this.f2575b)) : String.format("%d:%.1f:%.2f:%.1f", Integer.valueOf(this.f2577d), Double.valueOf(this.f2576c), Double.valueOf(this.f2575b), Double.valueOf(this.f2578e));
        }
    }

    class i extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2581b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2582c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f2583d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f2584e = null;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f2585f = 0;
        private a q = null;
        private long r = 0;
        private long s = 0;

        public i() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.f2489h = com.baidu.location.g.k.e();
            if (g.this.x == null || g.this.y == null || !g.this.x.equals(g.this.y.a())) {
                this.f2583d = "&nd_idf=1&indoor_polygon=1" + this.f2583d;
            }
            this.i = 1;
            String strEncodeTp4 = Jni.encodeTp4(this.f2583d);
            this.f2583d = null;
            this.k.put("bloc", strEncodeTp4);
            this.r = System.currentTimeMillis();
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            if (!z || this.j == null) {
                g.v(g.this);
                g.this.X = 0;
                this.f2581b = false;
                if (g.this.r <= 40) {
                    return;
                } else {
                    g.this.d();
                }
            } else {
                try {
                    String str = this.j;
                    if (!g.this.n) {
                        this.f2581b = false;
                        return;
                    }
                    BDLocation bDLocation = new BDLocation(str);
                    if (bDLocation.getLocType() == 161 && bDLocation.getBuildingID() != null) {
                        g.this.Y = new BDLocation(bDLocation);
                    }
                    String indoorLocationSurpportBuidlingName = bDLocation.getIndoorLocationSurpportBuidlingName();
                    if (indoorLocationSurpportBuidlingName != null && !g.this.Q.a(indoorLocationSurpportBuidlingName)) {
                        g.this.Q.a(indoorLocationSurpportBuidlingName, (a.InterfaceC0023a) null);
                    }
                    if (g.this.S != null) {
                        g.this.S.a(new k(this));
                    }
                    com.baidu.location.a.n.a().b(true);
                    if (bDLocation.getBuildingName() != null) {
                        g.this.A = bDLocation.getBuildingName();
                    }
                    if (bDLocation.getFloor() != null) {
                        g.this.p = System.currentTimeMillis();
                        this.s = System.currentTimeMillis();
                        int i = (int) (this.s - this.r);
                        if (i > 10000) {
                            g.this.X = 0;
                        } else if (i < 3000) {
                            g.this.X = 2;
                        } else {
                            g.this.X = 1;
                        }
                        if (bDLocation.getFloor().contains("-a")) {
                            g.this.K = true;
                            bDLocation.setFloor(bDLocation.getFloor().split("-")[0]);
                        } else {
                            g.this.K = false;
                        }
                        g.this.E.add(bDLocation.getFloor());
                    }
                    Message messageObtainMessage = g.this.f2530a.obtainMessage(21);
                    messageObtainMessage.obj = bDLocation;
                    messageObtainMessage.sendToTarget();
                } catch (Exception unused) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
            this.f2581b = false;
        }

        public void b() {
            if (this.f2581b) {
                this.f2582c = true;
                return;
            }
            if (g.this.f2532c != 1 || g.this.f2533d || System.currentTimeMillis() - this.f2585f >= 30000 || System.currentTimeMillis() - g.this.ae.f2556c <= 30000) {
                StringBuffer stringBuffer = new StringBuffer(1024);
                String strH = com.baidu.location.e.b.a().f().h();
                String strF = com.baidu.location.e.e.a().f();
                g.this.J = 0.5d;
                com.baidu.location.e.h hVarQ = com.baidu.location.e.i.a().q();
                String strA = g.this.a(hVarQ);
                if (strA == null) {
                    strA = hVarQ.a(g.this.f2534e, true, false);
                }
                if (strA == null || strA.length() < 10) {
                    return;
                }
                String str = this.f2584e;
                if (str == null || !str.equals(strA)) {
                    this.f2584e = strA;
                    this.f2581b = true;
                    stringBuffer.append(strH);
                    if (strF != null) {
                        stringBuffer.append(strF);
                    }
                    stringBuffer.append("&coor=gcj02");
                    stringBuffer.append("&lt=1");
                    stringBuffer.append(strA);
                    if (g.this.j != null && g.this.O <= 2 && g.this.j.h() != null) {
                        stringBuffer.append("&idsl=" + g.this.j.h());
                    }
                    int size = g.this.M.size();
                    stringBuffer.append(g.this.a(size));
                    g.this.N = size;
                    g.z(g.this);
                    stringBuffer.append("&drsi=" + g.this.O);
                    stringBuffer.append("&drc=" + g.this.u);
                    if (g.this.H != 0.0d && g.this.I != 0.0d) {
                        stringBuffer.append("&lst_idl=" + String.format(Locale.CHINA, "%.5f:%.5f", Double.valueOf(g.this.H), Double.valueOf(g.this.I)));
                    }
                    g.this.u = 0;
                    stringBuffer.append("&idpfv=1");
                    stringBuffer.append("&iflxy=" + g.this.af.toString());
                    g.this.af.f2564c.clear();
                    if (g.this.j != null && g.this.j.g()) {
                        stringBuffer.append("&pdr2=1");
                    }
                    if (g.this.S != null && g.this.S.e() != null && g.this.S.g()) {
                        stringBuffer.append("&bleand=");
                        stringBuffer.append(g.this.S.e());
                        stringBuffer.append("&bleand_et=");
                        stringBuffer.append(g.this.S.f());
                    }
                    g.D(g.this);
                    if (g.this.R != null) {
                        stringBuffer.append(g.this.R);
                        g.this.R = null;
                    }
                    String strD = com.baidu.location.a.a.a().d();
                    if (strD != null) {
                        stringBuffer.append(strD);
                    }
                    stringBuffer.append(com.baidu.location.g.b.a().a(true));
                    this.f2583d = stringBuffer.toString();
                    ExecutorService executorServiceB = v.a().b();
                    if (executorServiceB != null) {
                        a(executorServiceB, com.baidu.location.g.k.f2505f);
                    } else {
                        c(com.baidu.location.g.k.f2505f);
                    }
                    this.f2585f = System.currentTimeMillis();
                }
            }
        }

        public synchronized void c() {
            if (this.f2581b) {
                return;
            }
            if (this.f2582c) {
                this.f2582c = false;
                b();
            }
        }
    }

    private g() {
        this.f2530a = null;
        this.j = null;
        this.l = null;
        this.E = null;
        this.G = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.f2530a = new d();
        try {
            com.baidu.location.indoor.mapversion.c.a.a(com.baidu.location.f.getServiceContext());
        } catch (Exception unused) {
        }
        try {
            com.baidu.location.indoor.mapversion.c.c.a(com.baidu.location.f.getServiceContext());
        } catch (Exception unused2) {
        }
        this.U = new p();
        this.U.a(1000L);
        this.V = new com.baidu.location.indoor.h(this);
        this.t = new com.baidu.location.indoor.i(this);
        this.j = new m(com.baidu.location.f.getServiceContext(), this.t);
        this.l = new i();
        this.E = new com.baidu.location.indoor.c<>(this.D);
        this.G = new com.baidu.location.indoor.c<>(this.F);
        this.Q = new com.baidu.location.indoor.a(com.baidu.location.f.getServiceContext());
        this.ad = new c();
        i();
        this.ae = new e();
        this.af = new f();
        this.ag = new b();
    }

    static /* synthetic */ int D(g gVar) {
        int i2 = gVar.P;
        gVar.P = i2 + 1;
        return i2;
    }

    public static synchronized g a() {
        if (f2529g == null) {
            f2529g = new g();
        }
        return f2529g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i2) {
        if (this.M.size() == 0) {
            return "&dr=0:0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&dr=");
        this.M.get(0).f2577d = 1;
        sb.append(this.M.get(0).toString());
        int i3 = this.M.get(0).f2574a;
        for (int i4 = 1; i4 < this.M.size() && i4 <= i2; i4++) {
            this.M.get(i4).f2577d = this.M.get(i4).f2574a - i3;
            sb.append(";");
            sb.append(this.M.get(i4).toString());
            i3 = this.M.get(i4).f2574a;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(com.baidu.location.e.h hVar) {
        int iA = hVar.a();
        if (iA <= this.f2534e) {
            return hVar.a(this.f2534e, true, true) + "&aprk=0";
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < iA; i2++) {
            String lowerCase = hVar.f2420a.get(i2).BSSID.replaceAll(":", "").toLowerCase();
            com.baidu.location.indoor.a aVar = this.Q;
            if (aVar == null || !aVar.b(lowerCase)) {
                arrayList2.add(hVar.f2420a.get(i2));
            } else {
                arrayList.add(hVar.f2420a.get(i2));
            }
        }
        String str = arrayList.size() > 0 ? "&aprk=3" : "";
        if (str.equals("")) {
            com.baidu.location.indoor.a aVar2 = this.Q;
            str = (aVar2 == null || !aVar2.b()) ? "&aprk=1" : "&aprk=2";
        }
        arrayList.addAll(arrayList2);
        hVar.f2420a = arrayList;
        return hVar.a(this.f2534e, true, true) + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        m mVar;
        l lVar;
        if (this.n) {
            this.o = false;
            BDLocation bDLocation = (BDLocation) message.obj;
            if (bDLocation.getLocType() == 161) {
                m();
                if (bDLocation.getIndoorSurpportPolygon() != null && bDLocation.getIndoorLocationSurpportBuidlingID() != null && ((lVar = this.y) == null || !lVar.a().equals(bDLocation.getBuildingID()))) {
                    String[] strArrSplit = bDLocation.getIndoorSurpportPolygon().split("\\|");
                    Location[] locationArr = new Location[strArrSplit.length];
                    for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                        String[] strArrSplit2 = strArrSplit[i2].split(AppInfo.DELIM);
                        Location location = new Location("gps");
                        location.setLatitude(Double.valueOf(strArrSplit2[1]).doubleValue());
                        location.setLongitude(Double.valueOf(strArrSplit2[0]).doubleValue());
                        locationArr[i2] = location;
                    }
                    this.y = new l(bDLocation.getIndoorLocationSurpportBuidlingID(), locationArr);
                }
                if (this.L && this.S != null) {
                    if ((((bDLocation.getIndoorLocationSource() >> 2) & 1) == 1) && this.S.a()) {
                        this.L = false;
                        this.S.b();
                    }
                }
                this.r = 0;
                if (bDLocation.getBuildingID() != null) {
                    this.o = true;
                    bDLocation.setIndoorLocMode(true);
                    if (bDLocation.getRetFields("tp") == null || !bDLocation.getRetFields("tp").equalsIgnoreCase("ble")) {
                        this.T = false;
                    } else {
                        bDLocation.setRadius(8.0f);
                        bDLocation.setNetworkLocationType("ble");
                        this.T = true;
                    }
                    String retFields = bDLocation.getRetFields("pdr2");
                    if (retFields != null && retFields.equals("1") && (mVar = this.j) != null) {
                        mVar.a(true);
                    }
                    this.x = bDLocation.getBuildingID();
                    this.z = bDLocation.getBuildingName();
                    this.B = bDLocation.getNetworkLocationType();
                    this.C = bDLocation.isParkAvailable();
                    this.ag.a(bDLocation);
                    if (!bDLocation.getFloor().equals(l())) {
                        return;
                    }
                    if (this.w == null) {
                        this.w = bDLocation.getFloor();
                    }
                    com.baidu.location.indoor.mapversion.c.a.a().a(bDLocation.getLongitude(), bDLocation.getLatitude());
                    a(bDLocation.getBuildingName(), bDLocation.getFloor());
                    if (!bDLocation.getFloor().equals(l())) {
                        return;
                    }
                    if (!bDLocation.getFloor().equalsIgnoreCase(this.w) && this.ab) {
                        this.ae.a();
                        com.baidu.location.indoor.mapversion.b.a.c();
                        this.ac = com.baidu.location.indoor.mapversion.b.a.a(bDLocation.getFloor());
                    }
                    this.w = bDLocation.getFloor();
                    m mVar2 = this.j;
                    if (mVar2 != null && mVar2.e() >= 0.0d && bDLocation.getDirection() <= 0.0f) {
                        bDLocation.setDirection((float) this.j.e());
                    }
                    double[] dArrA = com.baidu.location.indoor.mapversion.b.a.a(bDLocation);
                    if (dArrA != null && dArrA[0] != -1.0d && dArrA[0] == 0.0d) {
                        bDLocation.setLongitude(dArrA[1]);
                        bDLocation.setLatitude(dArrA[2]);
                        bDLocation.setFusionLocInfo("res", dArrA);
                        bDLocation.setRadius((float) dArrA[5]);
                        bDLocation.setDirection((float) dArrA[6]);
                        bDLocation.setSpeed((float) dArrA[8]);
                        if (!this.ae.a(bDLocation, dArrA[5], "wifi")) {
                            d();
                            return;
                        }
                    }
                    this.I = bDLocation.getLatitude();
                    this.H = bDLocation.getLongitude();
                }
            } else if (bDLocation.getLocType() == 63) {
                this.r++;
                if (this.r <= 10) {
                    return;
                } else {
                    d();
                }
            } else {
                this.r = 0;
            }
            if (this.o) {
                if (bDLocation.getTime() == null) {
                    bDLocation.setTime(this.f2531b.format(new Date()));
                }
                BDLocation bDLocation2 = new BDLocation(bDLocation);
                bDLocation2.setNetworkLocationType(bDLocation2.getNetworkLocationType() + "2");
                p pVar = this.U;
                if (pVar == null || !pVar.c()) {
                    a(bDLocation2, 21);
                } else {
                    this.U.a(bDLocation2);
                }
            }
            this.l.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BDLocation bDLocation) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BDLocation bDLocation, int i2) {
        if (bDLocation == null) {
            return;
        }
        if (bDLocation.getNetworkLocationType().startsWith("vps")) {
            if (bDLocation.getLongitude() == -1.0d && bDLocation.getLatitude() == -1.0d) {
                bDLocation.setUserIndoorState(-1);
            } else {
                bDLocation.setUserIndoorState(1);
            }
            bDLocation.setIndoorNetworkState(this.X);
            com.baidu.location.a.a.a().a(bDLocation);
            return;
        }
        if (this.Y != null) {
            if (bDLocation.getAddrStr() == null && this.Y.getAddrStr() != null) {
                bDLocation.setAddr(this.Y.getAddress());
                bDLocation.setAddrStr(this.Y.getAddrStr());
            }
            if (bDLocation.getPoiList() == null && this.Y.getPoiList() != null) {
                bDLocation.setPoiList(this.Y.getPoiList());
            }
            if (bDLocation.getLocationDescribe() == null && this.Y.getLocationDescribe() != null) {
                bDLocation.setLocationDescribe(this.Y.getLocationDescribe());
            }
            if (bDLocation.getNrlResult() == null) {
                bDLocation.setNrlData(this.Y.getNrlResult());
            }
        }
        if (bDLocation == null) {
            return;
        }
        bDLocation.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis())));
        if (bDLocation.getNetworkLocationType().contains("2")) {
            String networkLocationType = bDLocation.getNetworkLocationType();
            bDLocation.setNetworkLocationType(networkLocationType.substring(0, networkLocationType.length() - 1));
            bDLocation.setUserIndoorState(1);
            bDLocation.setIndoorNetworkState(this.X);
            com.baidu.location.a.a.a().a(bDLocation);
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            bDLocation2.setRadius(this.T ? 8.0f : 15.0f);
            Message messageObtainMessage = this.f2530a.obtainMessage(Constants.EventConstants.TYPE_LANGUAGE_DIFFERENT);
            messageObtainMessage.obj = bDLocation2;
            messageObtainMessage.sendToTarget();
        }
    }

    private void a(String str, String str2) {
        String str3 = this.z;
        if (str3 != null && str3.equals(str) && this.ab) {
            return;
        }
        com.baidu.location.indoor.mapversion.c.a aVarA = com.baidu.location.indoor.mapversion.c.a.a();
        aVarA.a(CoordinateType.GCJ02);
        aVarA.a(str, new j(this, str, str2));
    }

    static /* synthetic */ int g(g gVar) {
        int i2 = gVar.u;
        gVar.u = i2 + 1;
        return i2;
    }

    private void i() {
    }

    private void j() {
        this.E.clear();
        this.G.clear();
        this.p = 0L;
        this.r = 0;
        this.C = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.L = true;
        this.J = 0.4d;
        this.T = false;
        this.H = 0.0d;
        this.I = 0.0d;
        this.K = false;
        this.O = 0;
        this.u = 0;
        this.s = null;
        this.q = 0L;
        this.ae.a();
        com.baidu.location.indoor.mapversion.b.a.c();
        if (this.ab) {
            com.baidu.location.indoor.mapversion.c.a.a().b();
        }
        this.ac = false;
        this.ab = false;
        com.baidu.location.a.n.a().b(false);
        com.baidu.location.indoor.d dVar = this.S;
        if (dVar != null) {
            dVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.n) {
            this.i = true;
            this.ae.d();
            this.l.b();
            this.m = System.currentTimeMillis();
        }
    }

    private String l() {
        if (this.ag.f2538b == 1 && this.ag.f2537a != null) {
            return this.ag.b();
        }
        HashMap map = new HashMap();
        int size = this.E.size();
        String str = null;
        int iIntValue = -1;
        String str2 = "";
        for (int i2 = 0; i2 < size; i2++) {
            try {
                String str3 = this.E.get(i2);
                str2 = str2 + str3 + "|";
                map.put(str3, map.containsKey(str3) ? Integer.valueOf(((Integer) map.get(str3)).intValue() + 1) : 1);
            } catch (Exception unused) {
                return this.w;
            }
        }
        for (String str4 : map.keySet()) {
            if (((Integer) map.get(str4)).intValue() > iIntValue) {
                iIntValue = ((Integer) map.get(str4)).intValue();
                str = str4;
            }
        }
        return str;
    }

    private void m() {
        for (int i2 = this.N; i2 >= 0 && this.M.size() > 0; i2--) {
            this.M.remove(0);
        }
        this.N = -1;
    }

    static /* synthetic */ int v(g gVar) {
        int i2 = gVar.r;
        gVar.r = i2 + 1;
        return i2;
    }

    static /* synthetic */ int z(g gVar) {
        int i2 = gVar.O;
        gVar.O = i2 + 1;
        return i2;
    }

    public boolean a(double d2, double d3) {
        Map<String, c.b> mapD;
        com.baidu.location.indoor.mapversion.c.c cVarA = com.baidu.location.indoor.mapversion.c.c.a();
        if (!cVarA.c() || !cVarA.b() || (mapD = cVarA.d()) == null) {
            return false;
        }
        String str = null;
        Iterator<String> it = mapD.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            c.b bVar = mapD.get(it.next());
            if (d2 > bVar.f2688e && d2 < bVar.f2686c && d3 > bVar.f2689f && d3 < bVar.f2687d) {
                String str2 = bVar.f2685b;
                str = bVar.f2684a;
                String str3 = bVar.f2690g;
                break;
            }
        }
        return str != null;
    }

    public boolean a(Location location, ArrayList<ArrayList<Float>> arrayList) {
        String str;
        if (arrayList.size() == 0 || !com.baidu.location.e.e.a().j()) {
            return false;
        }
        if (!this.n && location.getSpeed() > 3.0f) {
            return false;
        }
        double[] dArrCoorEncrypt = Jni.coorEncrypt(location.getLongitude(), location.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
        double d2 = dArrCoorEncrypt[0];
        double d3 = dArrCoorEncrypt[1];
        double accuracy = location.getAccuracy();
        double bearing = location.getBearing();
        double altitude = location.getAltitude();
        double speed = location.getSpeed();
        boolean z = a(d2, d3) || this.f2532c == 1;
        if (!this.n && !z) {
            return false;
        }
        try {
            this.ae.a(location, z);
            if (this.ae.b()) {
                c();
                return true;
            }
            if (!e()) {
                return false;
            }
            if (this.ae.a(d2, d3, accuracy)) {
                com.baidu.location.indoor.mapversion.b.a.c();
            }
            double[] dArrA = com.baidu.location.indoor.mapversion.b.a.a(d2, d3, this.ad.a(accuracy, d2, d3, altitude), bearing, speed);
            if (dArrA == null) {
                return false;
            }
            try {
                if (dArrA[0] == -1.0d) {
                    return false;
                }
                if (dArrA[0] != 0.0d) {
                    return false;
                }
                BDLocation bDLocation = new BDLocation();
                bDLocation.setAltitude(altitude);
                bDLocation.setLatitude(dArrA[2]);
                bDLocation.setLongitude(dArrA[1]);
                if (this.T) {
                    bDLocation.setRadius(8.0f);
                } else {
                    bDLocation.setRadius(15.0f);
                }
                bDLocation.setDirection((float) bearing);
                bDLocation.setSpeed((float) speed);
                bDLocation.setLocType(161);
                bDLocation.setNetworkLocationType("gps");
                if (System.currentTimeMillis() - this.ae.f2556c < 20000) {
                    bDLocation.setFloor(this.w);
                    bDLocation.setBuildingName(this.z);
                    str = this.x;
                } else {
                    str = null;
                    bDLocation.setFloor(null);
                    bDLocation.setBuildingName(null);
                }
                bDLocation.setBuildingID(str);
                bDLocation.setIndoorLocMode(true);
                this.I = bDLocation.getLatitude();
                this.H = bDLocation.getLongitude();
                bDLocation.setFusionLocInfo("res", dArrA);
                bDLocation.setRadius((float) dArrA[5]);
                bDLocation.setDirection((float) dArrA[6]);
                bDLocation.setSpeed((float) dArrA[8]);
                bDLocation.setTime(this.f2531b.format(new Date()));
                BDLocation bDLocation2 = new BDLocation(bDLocation);
                bDLocation2.setNetworkLocationType(bDLocation2.getNetworkLocationType() + "2");
                if (this.U == null || !this.U.c()) {
                    a(bDLocation2, 21);
                } else {
                    this.U.a(bDLocation2);
                }
                if (this.ae.a(bDLocation, dArrA[5], "gps")) {
                    return true;
                }
                d();
                return true;
            } catch (Exception unused) {
                return false;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public boolean a(Bundle bundle) {
        if (bundle == null || this.W == null) {
            return false;
        }
        a.d dVar = new a.d();
        dVar.b(bundle.getString("bid")).c(bundle.getString(AuthorizationResponseParser.CODE));
        dVar.a(bundle.getDouble("fov")).a(bundle.getFloatArray(CommonDialog.EXTRA_GRAVITY));
        dVar.a(bundle.getString("image"));
        dVar.a(bundle.getBoolean("force_online"));
        this.W.a(dVar);
        return true;
    }

    public synchronized void b() {
        if (this.n) {
            this.E.clear();
        }
    }

    public boolean b(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.f2532c = bundle.getInt(HeartRateMonitoringActivity.MODE);
        return true;
    }

    public synchronized void c() {
        if (this.n) {
            return;
        }
        com.baidu.location.indoor.mapversion.b.a.b();
        this.p = System.currentTimeMillis();
        this.q = System.currentTimeMillis();
        this.j.a();
        this.k = new C0024g();
        this.k.start();
        this.o = false;
        this.n = true;
        this.S = com.baidu.location.indoor.d.a(com.baidu.location.f.getServiceContext());
        this.O = 0;
        this.u = 0;
        com.baidu.location.a.n.a().b(true);
    }

    public synchronized void d() {
        if (this.n) {
            this.n = false;
            this.j.b();
            if (this.U != null && this.U.c()) {
                this.U.a();
            }
            if (this.Q != null) {
                this.Q.c();
            }
            if (this.S != null) {
                this.S.d();
            }
            if (this.k != null) {
                this.k.f2570b = false;
                this.k.interrupt();
                this.k = null;
            }
            j();
            this.o = false;
            com.baidu.location.a.a.a().c();
        }
    }

    public boolean e() {
        return this.n;
    }

    public boolean f() {
        return this.n && this.ae.e();
    }

    public String g() {
        return this.w;
    }

    public String h() {
        return this.x;
    }
}