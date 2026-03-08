package com.baidu.location.indoor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
public class m {
    private int A;
    private long B;
    private int C;
    private int D;
    private double E;
    private double F;
    private double G;
    private double H;
    private double I;
    private double J;
    private double K;
    private int L;
    private float M;
    private int N;
    private int O;
    private double[] P;
    private boolean Q;
    private double R;
    private String S;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Timer f2595a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SensorEventListener f2596b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f2597c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SensorManager f2598d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2599e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2600f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Sensor f2601g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Sensor f2602h;
    private Sensor i;
    private final long j;
    private boolean k;
    private boolean l;
    private boolean m;
    private volatile int n;
    private int o;
    private float[] p;
    private float[] q;
    private double[] r;
    private int s;
    private double[] t;
    private int u;
    private int v;
    private int w;
    private double[] x;
    private int y;
    private double z;

    public interface a {
        void a(double d2, double d3, double d4, long j);
    }

    private m(Context context, int i) {
        this.j = 30L;
        this.k = true;
        this.l = false;
        this.m = false;
        this.n = 1;
        this.o = 1;
        this.p = new float[3];
        this.q = new float[]{0.0f, 0.0f, 0.0f};
        this.r = new double[]{0.0d, 0.0d, 0.0d};
        this.s = 31;
        this.t = new double[this.s];
        this.u = 0;
        this.x = new double[6];
        this.y = 0;
        this.B = 0L;
        this.C = 0;
        this.D = 0;
        this.E = 0.0d;
        this.F = 0.0d;
        this.G = 100.0d;
        this.H = 0.5d;
        this.I = this.H;
        this.J = 0.85d;
        this.K = 0.42d;
        this.L = -1;
        this.M = 0.0f;
        this.N = 20;
        this.O = 0;
        this.P = new double[this.N];
        this.Q = false;
        this.R = -1.0d;
        this.S = null;
        this.f2596b = new n(this);
        this.z = 1.6d;
        this.A = 440;
        try {
            this.f2598d = (SensorManager) context.getSystemService("sensor");
            this.f2600f = i;
            this.f2601g = this.f2598d.getDefaultSensor(1);
            this.f2602h = this.f2598d.getDefaultSensor(3);
            if (com.baidu.location.indoor.mapversion.a.b()) {
                this.i = this.f2598d.getDefaultSensor(4);
            }
            j();
        } catch (Exception unused) {
        }
    }

    public m(Context context, a aVar) {
        this(context, 1);
        this.f2597c = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double a(double d2, double d3, double d4) {
        double d5 = d3 - d2;
        if (d5 < -180.0d) {
            d5 += 360.0d;
        } else if (d5 > 180.0d) {
            d5 -= 360.0d;
        }
        return d2 + (d4 * d5);
    }

    private double a(double[] dArr) {
        int length = dArr.length;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (double d4 : dArr) {
            d3 += d4;
        }
        double d5 = d3 / ((double) length);
        for (int i = 0; i < length; i++) {
            d2 += (dArr[i] - d5) * (dArr[i] - d5);
        }
        return d2 / ((double) (length - 1));
    }

    private void a(double d2) {
        double[] dArr = this.x;
        int i = this.y;
        dArr[i % 6] = d2;
        this.y = i + 1;
        this.y %= 6;
    }

    private synchronized void a(int i) {
        this.o = i | this.o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float[] a(float f2, float f3, float f4) {
        float[] fArr = this.p;
        fArr[0] = (fArr[0] * 0.8f) + (f2 * 0.19999999f);
        fArr[1] = (fArr[1] * 0.8f) + (f3 * 0.19999999f);
        fArr[2] = (fArr[2] * 0.8f) + (0.19999999f * f4);
        return new float[]{f2 - fArr[0], f3 - fArr[1], f4 - fArr[2]};
    }

    static /* synthetic */ int b(m mVar) {
        int i = mVar.v + 1;
        mVar.v = i;
        return i;
    }

    private boolean b(double d2) {
        for (int i = 1; i <= 5; i++) {
            double[] dArr = this.x;
            int i2 = this.y;
            if (dArr[((((i2 - 1) - i) + 6) + 6) % 6] - dArr[((i2 - 1) + 6) % 6] > d2) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ int f(m mVar) {
        int i = mVar.O;
        mVar.O = i + 1;
        return i;
    }

    static /* synthetic */ int h(m mVar) {
        int i = mVar.w + 1;
        mVar.w = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        for (int i = 0; i < this.N; i++) {
            if (this.P[i] > 1.0E-7d) {
                return true;
            }
        }
        return false;
    }

    private void j() {
        int iIntValue;
        try {
            List<Sensor> sensorList = this.f2598d.getSensorList(-1);
            HashMap map = new HashMap();
            map.put(1, 0);
            map.put(10, 1);
            map.put(9, 2);
            map.put(4, 3);
            map.put(2, 4);
            map.put(11, 5);
            map.put(6, 6);
            if (Build.VERSION.SDK_INT >= 18) {
                map.put(14, 7);
                map.put(16, 8);
            }
            int size = map.size();
            char[] cArr = new char[size];
            for (int i = 0; i < size; i++) {
                cArr[i] = '0';
            }
            Iterator<Sensor> it = sensorList.iterator();
            while (it.hasNext()) {
                int type = it.next().getType();
                if (map.get(Integer.valueOf(type)) != null && (iIntValue = ((Integer) map.get(Integer.valueOf(type))).intValue()) < size) {
                    cArr[iIntValue] = '1';
                }
            }
            this.S = new String(cArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void k() {
        this.k = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ea A[PHI: r7
  0x00ea: PHI (r7v9 double) = (r7v7 double), (r7v8 double) binds: [B:42:0x00e8, B:45:0x00f1] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l() {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.m.l():void");
    }

    public void a() {
        if (this.f2599e) {
            return;
        }
        Sensor sensor = this.f2601g;
        if (sensor != null) {
            try {
                this.f2598d.registerListener(this.f2596b, sensor, this.f2600f);
            } catch (Exception unused) {
                this.k = false;
            }
            this.f2595a = new Timer("UpdateData", false);
            this.f2595a.schedule(new o(this), 500L, 30L);
            this.f2599e = true;
        }
        Sensor sensor2 = this.f2602h;
        if (sensor2 != null) {
            try {
                this.f2598d.registerListener(this.f2596b, sensor2, this.f2600f);
            } catch (Exception unused2) {
                this.k = false;
            }
        }
    }

    public void a(boolean z) {
        this.l = z;
        if (!z || this.m) {
            return;
        }
        k();
        this.m = true;
    }

    public void b() {
        if (this.f2599e) {
            this.f2599e = false;
            try {
                this.f2598d.unregisterListener(this.f2596b);
            } catch (Exception unused) {
            }
            this.f2595a.cancel();
            this.f2595a.purge();
            this.f2595a = null;
            this.m = false;
            if (com.baidu.location.indoor.mapversion.a.b()) {
                com.baidu.location.indoor.mapversion.a.a();
            }
        }
    }

    public synchronized int c() {
        if (this.v < 20) {
            return 1;
        }
        return this.o;
    }

    public synchronized int d() {
        if (this.v < 20) {
            return -1;
        }
        return this.C;
    }

    public double e() {
        return this.R;
    }

    public synchronized void f() {
        this.o = 0;
    }

    public boolean g() {
        return this.l;
    }

    protected String h() {
        return this.S;
    }
}