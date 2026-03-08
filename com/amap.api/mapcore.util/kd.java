package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.Map;

/* JADX INFO: compiled from: LocationRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class kd extends ik {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Map<String, String> f1523f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    String f1524g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    String f1525h;
    byte[] i;
    byte[] j;
    boolean k;
    String l;
    Map<String, String> m;
    boolean n;
    private String o;

    public kd(Context context, gs gsVar) {
        super(context, gsVar);
        this.f1523f = null;
        this.o = "";
        this.f1524g = "";
        this.f1525h = "";
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = null;
        this.n = false;
    }

    public final void a() {
        this.k = true;
    }

    public final void a(String str) {
        this.l = str;
    }

    public final void a(Map<String, String> map) {
        this.m = map;
    }

    public final void b(String str) {
        this.f1524g = str;
    }

    public final void b(Map<String, String> map) {
        this.f1523f = map;
    }

    public final void b(byte[] bArr) {
        this.i = bArr;
    }

    public final void c(String str) {
        this.f1525h = str;
    }

    @Override // com.amap.api.mapcore.util.ik
    public final byte[] e() {
        return this.i;
    }

    @Override // com.amap.api.mapcore.util.ik
    public final byte[] f() {
        return this.j;
    }

    @Override // com.amap.api.mapcore.util.iq
    protected final String getIPDNSName() {
        return this.o;
    }

    @Override // com.amap.api.mapcore.util.gp, com.amap.api.mapcore.util.iq
    public final String getIPV6URL() {
        return this.f1525h;
    }

    @Override // com.amap.api.mapcore.util.ik, com.amap.api.mapcore.util.iq
    public final Map<String, String> getParams() {
        return this.m;
    }

    @Override // com.amap.api.mapcore.util.iq
    public final Map<String, String> getRequestHead() {
        return this.f1523f;
    }

    @Override // com.amap.api.mapcore.util.iq
    public final String getURL() {
        return this.f1524g;
    }

    @Override // com.amap.api.mapcore.util.ik
    public final boolean h() {
        return this.k;
    }

    @Override // com.amap.api.mapcore.util.ik
    public final String j() {
        return this.l;
    }

    @Override // com.amap.api.mapcore.util.ik
    protected final boolean k() {
        return this.n;
    }

    public final void l() {
        this.n = true;
    }
}