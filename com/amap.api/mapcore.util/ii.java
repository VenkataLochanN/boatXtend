package com.amap.api.mapcore.util;

import java.util.Map;

/* JADX INFO: compiled from: ADIURequest.java */
/* JADX INFO: loaded from: classes.dex */
public class ii extends iq {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f1351d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map<String, String> f1352e;

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        return null;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return "https://adiu.amap.com/ws/device/adius";
    }

    public ii(byte[] bArr, Map<String, String> map) {
        this.f1351d = bArr;
        this.f1352e = map;
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        return this.f1352e;
    }

    @Override // com.amap.api.mapcore.util.iq
    public byte[] getEntityBytes() {
        return this.f1351d;
    }
}