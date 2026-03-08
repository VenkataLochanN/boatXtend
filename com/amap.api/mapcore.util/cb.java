package com.amap.api.mapcore.util;

import java.util.Hashtable;
import java.util.Map;

/* JADX INFO: compiled from: OfflineDownloadRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class cb extends dp {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f413d;

    @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        return null;
    }

    @Override // com.amap.api.mapcore.util.iq
    public boolean isSupportIPV6() {
        return false;
    }

    public cb(String str) {
        this.f413d = str;
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        Hashtable hashtable = new Hashtable(32);
        hashtable.put("User-Agent", "MAC=channel:amapapi");
        return hashtable;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return this.f413d;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getIPV6URL() {
        return getURL();
    }
}