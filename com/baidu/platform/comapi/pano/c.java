package com.baidu.platform.comapi.pano;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f3915a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    PanoStateError f3916b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3917c;

    public c() {
    }

    public c(PanoStateError panoStateError) {
        this.f3916b = panoStateError;
    }

    public PanoStateError a() {
        return this.f3916b;
    }

    public void a(int i) {
        this.f3917c = i;
    }

    public void a(String str) {
        this.f3915a = str;
    }

    public String b() {
        return this.f3915a;
    }

    public int c() {
        return this.f3917c;
    }
}