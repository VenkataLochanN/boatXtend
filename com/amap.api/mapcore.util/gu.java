package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: AuthRequest.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
class gu extends iq {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1143e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Map<String, String> f1142d = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Map<String, String> f1144f = new HashMap();

    gu() {
    }

    void a(Map<String, String> map) {
        this.f1142d.clear();
        this.f1142d.putAll(map);
    }

    void a(String str) {
        this.f1143e = str;
    }

    void b(Map<String, String> map) {
        this.f1144f.clear();
        this.f1144f.putAll(map);
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return this.f1143e;
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        return this.f1142d;
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        return this.f1144f;
    }
}