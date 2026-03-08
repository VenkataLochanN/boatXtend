package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: compiled from: LogUpdateRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class hm extends gp {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f1250d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1251e;

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        return null;
    }

    public hm(byte[] bArr, String str) {
        this.f1251e = "1";
        this.f1250d = (byte[]) bArr.clone();
        this.f1251e = str;
    }

    private String a() {
        byte[] bArrA = gt.a(hj.f1230a);
        byte[] bArr = new byte[bArrA.length + 50];
        System.arraycopy(this.f1250d, 0, bArr, 0, 50);
        System.arraycopy(bArrA, 0, bArr, 50, bArrA.length);
        return gq.a(bArr);
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        HashMap map = new HashMap();
        map.put("Content-Type", DfuBaseService.MIME_TYPE_ZIP);
        map.put("Content-Length", String.valueOf(this.f1250d.length));
        return map;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return String.format(gt.c(hj.f1231b), "1", this.f1251e, "1", "open", a());
    }

    @Override // com.amap.api.mapcore.util.iq
    public byte[] getEntityBytes() {
        return this.f1250d;
    }
}