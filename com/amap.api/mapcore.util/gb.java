package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: AbstractBasicLbsRestHandler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class gb<T, V> extends ga<T, V> {
    @Override // com.amap.api.mapcore.util.ga
    protected abstract String a();

    @Override // com.amap.api.mapcore.util.ga
    protected abstract V b(String str) throws fz;

    @Override // com.amap.api.mapcore.util.ga
    protected V f() {
        return null;
    }

    @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        return null;
    }

    public gb(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.mapcore.util.iq
    public byte[] getEntityBytes() {
        try {
            return a().getBytes("utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.mapcore.util.ga, com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        HashMap map = new HashMap(16);
        map.put("Content-Type", " application/json");
        map.put("Accept-Encoding", "gzip");
        map.put("User-Agent", "AMAP SDK Android Trace 7.6.0");
        map.put("x-INFO", gl.b(this.f1008f));
        map.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", "7.6.0", "trace"));
        map.put("logversion", "2.1");
        return map;
    }
}