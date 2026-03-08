package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class iq {
    public static final int DEFAULT_RETRY_TIMEOUT = 5000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1399a = 20000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1400b = 20000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Proxy f1401c = null;

    public byte[] getEntityBytes() {
        return null;
    }

    protected String getIPDNSName() {
        return "";
    }

    public abstract Map<String, String> getParams();

    public abstract Map<String, String> getRequestHead();

    public abstract String getURL();

    public boolean isIgnoreGZip() {
        return false;
    }

    public boolean isSupportIPV6() {
        return false;
    }

    String b() {
        return a(getURL());
    }

    private String a(String str) {
        Map<String, String> params;
        byte[] entityBytes = getEntityBytes();
        if (entityBytes == null || entityBytes.length == 0 || (params = getParams()) == null) {
            return str;
        }
        String strA = in.a(params);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("?");
        stringBuffer.append(strA);
        return stringBuffer.toString();
    }

    String c() {
        return a(getIPV6URL());
    }

    protected boolean isIPRequest() {
        return !TextUtils.isEmpty(getIPDNSName());
    }

    byte[] d() {
        byte[] entityBytes = getEntityBytes();
        if (entityBytes != null && entityBytes.length != 0) {
            return entityBytes;
        }
        String strA = in.a(getParams());
        return !TextUtils.isEmpty(strA) ? gt.a(strA) : entityBytes;
    }

    public String getIPV6URL() {
        return getURL();
    }

    public final void setConnectionTimeout(int i) {
        this.f1399a = i;
    }

    public final void setSoTimeout(int i) {
        this.f1400b = i;
    }

    public int getConntectionTimeout() {
        return this.f1399a;
    }

    public int getSoTimeout() {
        return this.f1400b;
    }

    public final void setProxy(Proxy proxy) {
        this.f1401c = proxy;
    }
}