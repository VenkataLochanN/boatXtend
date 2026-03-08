package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: CustomStyleTextureRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class dh extends ga<String, a> {

    /* JADX INFO: compiled from: CustomStyleTextureRequest.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f606a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f607b = -1;
    }

    @Override // com.amap.api.mapcore.util.ga
    protected String a() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.ga
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public a b(String str) throws fz {
        return null;
    }

    @Override // com.amap.api.mapcore.util.iq
    public boolean isSupportIPV6() {
        return true;
    }

    public void a(String str) {
        this.f1009g = str;
    }

    public dh(Context context, String str) {
        super(context, str);
        this.f1009g = "/map/styles";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.ga
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b(byte[] bArr) throws fz {
        a aVar = new a();
        aVar.f606a = bArr;
        return aVar;
    }

    @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        HashMap map = new HashMap(16);
        map.put("key", gi.f(this.f1008f));
        map.put("output", "bin");
        String strA = gl.a();
        String strA2 = gl.a(this.f1008f, strA, gt.c(map));
        map.put("ts", strA);
        map.put("scode", strA2);
        return map;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return this.f1009g;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getIPV6URL() {
        return er.a(getURL());
    }
}