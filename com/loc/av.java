package com.loc;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class av {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f4806c = 20000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f4807d = 20000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Proxy f4808e = null;

    private String a(String str) {
        Map<String, String> mapB_;
        byte[] bArrE = e();
        if (bArrE == null || bArrE.length == 0 || (mapB_ = b_()) == null) {
            return str;
        }
        String strA = at.a(mapB_);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("?");
        stringBuffer.append(strA);
        return stringBuffer.toString();
    }

    public final void a(int i) {
        this.f4806c = i;
    }

    public final void a(Proxy proxy) {
        this.f4808e = proxy;
    }

    public abstract Map<String, String> b();

    public final void b(int i) {
        this.f4807d = i;
    }

    public abstract Map<String, String> b_();

    public abstract String c();

    public String d() {
        return c();
    }

    public byte[] e() {
        return null;
    }

    protected String h() {
        return "";
    }

    public boolean i() {
        return false;
    }

    final String m() {
        return a(c());
    }

    final String n() {
        return a(d());
    }

    protected final boolean o() {
        return !TextUtils.isEmpty(h());
    }

    public String p() {
        return "";
    }
}