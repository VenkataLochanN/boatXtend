package com.ido.ble.event.stat.one.faildata;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Long f4406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4407b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4408c;

    public c() {
    }

    public c(Long l, String str, String str2) {
        this.f4406a = l;
        this.f4407b = str;
        this.f4408c = str2;
    }

    public String a() {
        return this.f4407b;
    }

    public void a(Long l) {
        this.f4406a = l;
    }

    public void a(String str) {
        this.f4407b = str;
    }

    public Long b() {
        return this.f4406a;
    }

    public void b(String str) {
        this.f4408c = str;
    }

    public String c() {
        return this.f4408c;
    }

    public String toString() {
        return "FailLogInfo{detail='" + this.f4407b + "', time='" + this.f4408c + "'}";
    }
}