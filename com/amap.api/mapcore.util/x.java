package com.amap.api.mapcore.util;

import com.amap.api.maps.model.BitmapDescriptor;

/* JADX INFO: compiled from: OverlayTextureItem.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BitmapDescriptor f1818b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1820d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1821e = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f1822f = 0.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f1823g = 1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f1824h = 1.0f;
    private boolean i = false;
    private float j = 0.0f;
    private float k = 0.0f;
    private float l = 0.0f;
    private float m = 0.0f;
    private int n = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1817a = ej.a();

    public boolean a() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public float b() {
        return this.l;
    }

    public void a(float f2) {
        this.l = f2;
    }

    public float c() {
        return this.m;
    }

    public void b(float f2) {
        this.m = f2;
    }

    public float d() {
        return this.j;
    }

    public void c(float f2) {
        this.j = f2;
    }

    public float e() {
        return this.k;
    }

    public void d(float f2) {
        this.k = f2;
    }

    public float f() {
        return this.f1822f;
    }

    public void e(float f2) {
        this.f1822f = f2;
    }

    public float g() {
        return this.f1821e;
    }

    public void f(float f2) {
        this.f1821e = f2;
    }

    public float h() {
        return this.f1823g;
    }

    public void g(float f2) {
        this.f1823g = f2;
    }

    public float i() {
        return this.f1824h;
    }

    public void h(float f2) {
        this.f1824h = f2;
    }

    public x(BitmapDescriptor bitmapDescriptor, int i) {
        this.f1820d = 1.0f;
        this.f1818b = bitmapDescriptor;
        this.f1819c = i;
        BitmapDescriptor bitmapDescriptor2 = this.f1818b;
        if (bitmapDescriptor2 != null) {
            this.f1820d = (float) ((((double) bitmapDescriptor2.getWidth()) * 1.0d) / ((double) this.f1818b.getHeight()));
        }
    }

    public BitmapDescriptor j() {
        return this.f1818b;
    }

    public int k() {
        return this.f1819c;
    }

    public float l() {
        return this.f1820d;
    }

    public void m() {
        this.n++;
    }

    public void n() {
        this.n--;
    }

    public int o() {
        return this.n;
    }

    public String p() {
        return this.f1817a;
    }

    public void a(int i) {
        this.f1819c = i;
    }
}