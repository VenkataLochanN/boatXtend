package com.baidu.location.indoor;

import android.location.Location;
import android.os.Handler;
import com.baidu.location.BDLocation;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f2693a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BDLocation f2695c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2694b = 450;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f2696d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f2697e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f2698f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b f2699g = new b();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private b f2700h = new b();
    private b i = new b();
    private BDLocation j = null;
    private long k = -1;
    private boolean l = false;
    private Handler m = new Handler();
    private Runnable n = new q(this);
    private Runnable o = new r(this);

    public interface a {
        void a(BDLocation bDLocation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f2701a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double f2702b;

        public b() {
            this.f2701a = 0.0d;
            this.f2702b = 0.0d;
        }

        public b(double d2, double d3) {
            this.f2701a = d2;
            this.f2702b = d3;
        }

        public b(b bVar) {
            this.f2701a = bVar.f2701a;
            this.f2702b = bVar.f2702b;
        }

        public b a(double d2) {
            return p.this.new b(this.f2701a * d2, this.f2702b * d2);
        }

        public b a(b bVar) {
            return p.this.new b(this.f2701a - bVar.f2701a, this.f2702b - bVar.f2702b);
        }

        public b b(b bVar) {
            return p.this.new b(this.f2701a + bVar.f2701a, this.f2702b + bVar.f2702b);
        }

        public boolean b(double d2) {
            double dAbs = Math.abs(this.f2701a);
            double dAbs2 = Math.abs(this.f2702b);
            return dAbs > 0.0d && dAbs < d2 && dAbs2 > 0.0d && dAbs2 < d2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b a(b bVar) {
        b bVar2 = this.f2696d;
        if (bVar2 == null || bVar == null) {
            return null;
        }
        b bVarA = bVar2.a(bVar);
        this.i = this.i.b(bVarA);
        b bVarA2 = this.f2700h.a(this.f2698f);
        this.f2698f = new b(this.f2700h);
        this.f2700h = new b(bVarA);
        b bVarA3 = bVarA.a(0.2d);
        b bVarA4 = this.i.a(0.01d);
        return bVarA3.b(bVarA4).b(bVarA2.a(-0.02d));
    }

    public void a() {
        if (this.l) {
            this.l = false;
            this.m.removeCallbacks(this.o);
            b();
        }
    }

    public void a(long j) {
        this.f2694b = j;
    }

    public synchronized void a(BDLocation bDLocation) {
        double latitude = bDLocation.getLatitude();
        double longitude = bDLocation.getLongitude();
        this.f2695c = bDLocation;
        this.f2696d = new b(latitude, longitude);
        if (this.f2697e == null) {
            this.f2697e = new b(latitude, longitude);
        }
        if (this.j == null) {
            this.j = new BDLocation(bDLocation);
        } else {
            double latitude2 = this.j.getLatitude();
            double longitude2 = this.j.getLongitude();
            double latitude3 = bDLocation.getLatitude();
            double longitude3 = bDLocation.getLongitude();
            float[] fArr = new float[2];
            Location.distanceBetween(latitude2, longitude2, latitude3, longitude3, fArr);
            if (fArr[0] > 10.0f) {
                this.j.setLatitude(latitude3);
                this.j.setLongitude(longitude3);
            } else {
                this.j.setLatitude((latitude2 + latitude3) / 2.0d);
                this.j.setLongitude((longitude2 + longitude3) / 2.0d);
            }
        }
    }

    public void b() {
        this.k = -1L;
        this.f2697e = null;
        this.f2696d = null;
        this.f2698f = new b();
        this.f2699g = new b();
        this.f2700h = new b();
        this.i = new b();
        this.j = null;
    }

    public boolean c() {
        return this.l;
    }
}