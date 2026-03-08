package com.baidu.mapsdkplatform.comapi.map;

import android.os.Bundle;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.baidu.mapapi.map.WinRound;
import com.baidu.mapapi.model.inner.Point;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
public class ab {
    private static final String t = ab.class.getSimpleName();
    public double m;
    public double n;
    public int o;
    public String p;
    public float q;
    public boolean r;
    public int s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3518a = 12.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3519b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3520c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f3521d = 1.2958162E7d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f3522e = 4825907.0d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f3525h = 0;
    public long i = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3523f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3524g = -1;
    public WinRound j = new WinRound();
    public a k = new a();
    public boolean l = false;

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f3526a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f3527b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3528c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3529d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Point f3530e = new Point(0, 0);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Point f3531f = new Point(0, 0);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Point f3532g = new Point(0, 0);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Point f3533h = new Point(0, 0);

        public a() {
        }
    }

    public Bundle a(e eVar) {
        int i;
        if (this.f3518a < eVar.f3562b) {
            this.f3518a = eVar.f3562b;
        }
        if (this.f3518a > eVar.f3561a) {
            if (this.f3518a == 1096.0f || e.f3560d == 26.0f) {
                this.f3518a = 26.0f;
                e.f3560d = 26.0f;
            } else {
                this.f3518a = eVar.f3561a;
            }
        }
        while (true) {
            i = this.f3519b;
            if (i >= 0) {
                break;
            }
            this.f3519b = i + SpatialRelationUtil.A_CIRCLE_DEGREE;
        }
        this.f3519b = i % SpatialRelationUtil.A_CIRCLE_DEGREE;
        if (this.f3520c > 0) {
            this.f3520c = 0;
        }
        if (this.f3520c < -45) {
            this.f3520c = -45;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble(FirebaseAnalytics.Param.LEVEL, this.f3518a);
        bundle.putDouble("rotation", this.f3519b);
        bundle.putDouble("overlooking", this.f3520c);
        bundle.putDouble("centerptx", this.f3521d);
        bundle.putDouble("centerpty", this.f3522e);
        bundle.putInt("left", this.j.left);
        bundle.putInt("right", this.j.right);
        bundle.putInt("top", this.j.top);
        bundle.putInt("bottom", this.j.bottom);
        int i2 = this.f3523f;
        if (i2 >= 0 && this.f3524g >= 0 && i2 <= this.j.right && this.f3524g <= this.j.bottom && this.j.right > 0 && this.j.bottom > 0) {
            int i3 = (this.j.right - this.j.left) / 2;
            int i4 = (this.j.bottom - this.j.top) / 2;
            int i5 = this.f3523f - i3;
            int i6 = this.f3524g - i4;
            this.f3525h = i5;
            this.i = -i6;
            bundle.putLong("xoffset", this.f3525h);
            bundle.putLong("yoffset", this.i);
        }
        bundle.putInt("lbx", this.k.f3530e.x);
        bundle.putInt("lby", this.k.f3530e.y);
        bundle.putInt("ltx", this.k.f3531f.x);
        bundle.putInt("lty", this.k.f3531f.y);
        bundle.putInt("rtx", this.k.f3532g.x);
        bundle.putInt("rty", this.k.f3532g.y);
        bundle.putInt("rbx", this.k.f3533h.x);
        bundle.putInt("rby", this.k.f3533h.y);
        bundle.putInt("bfpp", this.l ? 1 : 0);
        bundle.putInt("animation", 1);
        bundle.putInt("animatime", this.o);
        bundle.putString("panoid", this.p);
        bundle.putInt("autolink", 0);
        bundle.putFloat("siangle", this.q);
        bundle.putInt("isbirdeye", this.r ? 1 : 0);
        bundle.putInt("ssext", this.s);
        return bundle;
    }

    public void a(Bundle bundle) {
        this.f3518a = (float) bundle.getDouble(FirebaseAnalytics.Param.LEVEL);
        this.f3519b = (int) bundle.getDouble("rotation");
        this.f3520c = (int) bundle.getDouble("overlooking");
        this.f3521d = bundle.getDouble("centerptx");
        this.f3522e = bundle.getDouble("centerpty");
        this.j.left = bundle.getInt("left");
        this.j.right = bundle.getInt("right");
        this.j.top = bundle.getInt("top");
        this.j.bottom = bundle.getInt("bottom");
        this.f3525h = bundle.getLong("xoffset");
        this.i = bundle.getLong("yoffset");
        if (this.j.right != 0 && this.j.bottom != 0) {
            int i = (this.j.right - this.j.left) / 2;
            int i2 = (this.j.bottom - this.j.top) / 2;
            int i3 = (int) this.f3525h;
            int i4 = (int) (-this.i);
            this.f3523f = i3 + i;
            this.f3524g = i4 + i2;
        }
        this.k.f3526a = bundle.getLong("gleft");
        this.k.f3527b = bundle.getLong("gright");
        this.k.f3528c = bundle.getLong("gtop");
        this.k.f3529d = bundle.getLong("gbottom");
        if (this.k.f3526a <= -20037508) {
            this.k.f3526a = -20037508L;
        }
        if (this.k.f3527b >= 20037508) {
            this.k.f3527b = 20037508L;
        }
        if (this.k.f3528c >= 20037508) {
            this.k.f3528c = 20037508L;
        }
        if (this.k.f3529d <= -20037508) {
            this.k.f3529d = -20037508L;
        }
        this.k.f3530e.x = bundle.getInt("lbx");
        this.k.f3530e.y = bundle.getInt("lby");
        this.k.f3531f.x = bundle.getInt("ltx");
        this.k.f3531f.y = bundle.getInt("lty");
        this.k.f3532g.x = bundle.getInt("rtx");
        this.k.f3532g.y = bundle.getInt("rty");
        this.k.f3533h.x = bundle.getInt("rbx");
        this.k.f3533h.y = bundle.getInt("rby");
        this.l = bundle.getInt("bfpp") == 1;
        this.m = bundle.getDouble("adapterzoomunit");
        this.n = bundle.getDouble("zoomunit");
        this.p = bundle.getString("panoid");
        this.q = bundle.getFloat("siangle");
        this.r = bundle.getInt("isbirdeye") != 0;
        this.s = bundle.getInt("ssext");
    }
}