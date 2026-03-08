package com.loc;

import com.amazon.identity.auth.device.dataobject.AppInfo;

/* JADX INFO: compiled from: LogJsonDataStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bh extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StringBuilder f4858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4859b;

    public bh() {
        this.f4858a = new StringBuilder();
        this.f4859b = true;
    }

    public bh(bk bkVar) {
        super(bkVar);
        this.f4858a = new StringBuilder();
        this.f4859b = true;
    }

    @Override // com.loc.bk
    protected final byte[] a(byte[] bArr) {
        byte[] bArrA = u.a(this.f4858a.toString());
        this.f4864d = bArrA;
        this.f4859b = true;
        StringBuilder sb = this.f4858a;
        sb.delete(0, sb.length());
        return bArrA;
    }

    @Override // com.loc.bk
    public final void b(byte[] bArr) {
        String strA = u.a(bArr);
        if (this.f4859b) {
            this.f4859b = false;
        } else {
            this.f4858a.append(AppInfo.DELIM);
        }
        StringBuilder sb = this.f4858a;
        sb.append("{\"log\":\"");
        sb.append(strA);
        sb.append("\"}");
    }
}