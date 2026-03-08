package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AbstractCityStateImp.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class cd implements ch {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected az f415b;

    public cd(int i, az azVar) {
        this.f414a = i;
        this.f415b = azVar;
    }

    public int b() {
        return this.f414a;
    }

    public boolean a(cd cdVar) {
        return cdVar.b() == b();
    }

    public void b(cd cdVar) {
        bx.a(b() + " ==> " + cdVar.b() + "   " + getClass() + "==>" + cdVar.getClass());
    }

    public void c() {
        bx.a("Wrong call start()  State: " + b() + "  " + getClass());
    }

    public void d() {
        bx.a("Wrong call continueDownload()  State: " + b() + "  " + getClass());
    }

    public void e() {
        bx.a("Wrong call pause()  State: " + b() + "  " + getClass());
    }

    public void a() {
        bx.a("Wrong call delete()  State: " + b() + "  " + getClass());
    }

    public void a(int i) {
        bx.a("Wrong call fail()  State: " + b() + "  " + getClass());
    }

    public void f() {
        bx.a("Wrong call hasNew()  State: " + b() + "  " + getClass());
    }

    public void g() {
        bx.a("Wrong call complete()  State: " + b() + "  " + getClass());
    }
}