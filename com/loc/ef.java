package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: compiled from: LocationRequest.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ef extends ar {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Map<String, String> f5137f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    String f5138g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    String f5139h;
    byte[] i;
    byte[] j;
    boolean k;
    String l;
    Map<String, String> m;
    boolean n;
    private String o;

    public ef(Context context, t tVar) {
        super(context, tVar);
        this.f5137f = null;
        this.o = "";
        this.f5138g = "";
        this.f5139h = "";
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = null;
        this.n = false;
    }

    public final void a(String str) {
        this.f5138g = str;
    }

    @Override // com.loc.ar
    public final byte[] a_() {
        return this.i;
    }

    @Override // com.loc.av
    public final Map<String, String> b() {
        return this.f5137f;
    }

    public final void b(String str) {
        this.f5139h = str;
    }

    @Override // com.loc.ar, com.loc.av
    public final Map<String, String> b_() {
        return this.m;
    }

    @Override // com.loc.av
    public final String c() {
        return this.f5138g;
    }

    public final void c(String str) {
        if (TextUtils.isEmpty(str)) {
            this.o = "";
        } else {
            this.o = str;
        }
    }

    @Override // com.loc.q, com.loc.av
    public final String d() {
        return this.f5139h;
    }

    @Override // com.loc.ar
    public final byte[] f() {
        return this.j;
    }

    @Override // com.loc.av
    public final String h() {
        return this.o;
    }

    @Override // com.loc.ar
    public final boolean j() {
        return this.k;
    }

    @Override // com.loc.ar
    public final String k() {
        return this.l;
    }

    @Override // com.loc.ar
    protected final boolean l() {
        return this.n;
    }

    @Override // com.loc.av
    public final String p() {
        return "loc";
    }
}