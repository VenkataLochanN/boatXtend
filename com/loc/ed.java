package com.loc;

import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: compiled from: HttpRequest.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ed extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Map<String, String> f5126a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Map<String, String> f5127b = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f5128f = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    byte[] f5129g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5130h = null;

    public final void a(String str) {
        this.f5128f = str;
    }

    public final void a(Map<String, String> map) {
        this.f5126a = map;
    }

    public final void a(byte[] bArr) {
        this.f5129g = bArr;
    }

    @Override // com.loc.av
    public final Map<String, String> b() {
        return this.f5126a;
    }

    public final void b(String str) {
        this.f5130h = str;
    }

    public final void b(Map<String, String> map) {
        this.f5127b = map;
    }

    @Override // com.loc.av
    public final Map<String, String> b_() {
        return this.f5127b;
    }

    @Override // com.loc.av
    public final String c() {
        return this.f5128f;
    }

    @Override // com.loc.q, com.loc.av
    public final String d() {
        return !TextUtils.isEmpty(this.f5130h) ? this.f5130h : super.d();
    }

    @Override // com.loc.av
    public final byte[] e() {
        return this.f5129g;
    }
}