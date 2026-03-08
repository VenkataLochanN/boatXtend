package com.amap.api.mapcore.util;

import com.amazon.identity.auth.device.dataobject.AppInfo;

/* JADX INFO: compiled from: LogJsonDataStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jg extends jj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StringBuilder f1460a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1461b;

    public jg() {
        this.f1460a = new StringBuilder();
        this.f1461b = true;
    }

    public jg(jj jjVar) {
        super(jjVar);
        this.f1460a = new StringBuilder();
        this.f1461b = true;
    }

    @Override // com.amap.api.mapcore.util.jj
    protected byte[] a(byte[] bArr) {
        byte[] bArrA = gt.a(this.f1460a.toString());
        c(bArrA);
        this.f1461b = true;
        StringBuilder sb = this.f1460a;
        sb.delete(0, sb.length());
        return bArrA;
    }

    @Override // com.amap.api.mapcore.util.jj
    public void b(byte[] bArr) {
        String strA = gt.a(bArr);
        if (this.f1461b) {
            this.f1461b = false;
        } else {
            this.f1460a.append(AppInfo.DELIM);
        }
        StringBuilder sb = this.f1460a;
        sb.append("{\"log\":\"");
        sb.append(strA);
        sb.append("\"}");
    }
}