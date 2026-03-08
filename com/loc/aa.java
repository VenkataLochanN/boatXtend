package com.loc;

import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: compiled from: LogUpdateRequest.java */
/* JADX INFO: loaded from: classes3.dex */
public final class aa extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[] f4706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4707b;

    public aa(byte[] bArr, String str) {
        this.f4707b = "1";
        this.f4706a = (byte[]) bArr.clone();
        this.f4707b = str;
    }

    @Override // com.loc.av
    public final Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("Content-Type", DfuBaseService.MIME_TYPE_ZIP);
        map.put("Content-Length", String.valueOf(this.f4706a.length));
        return map;
    }

    @Override // com.loc.av
    public final Map<String, String> b_() {
        return null;
    }

    @Override // com.loc.av
    public final String c() {
        String strC = u.c(v.f5329b);
        byte[] bArrA = u.a(v.f5328a);
        byte[] bArr = new byte[bArrA.length + 50];
        System.arraycopy(this.f4706a, 0, bArr, 0, 50);
        System.arraycopy(bArrA, 0, bArr, 50, bArrA.length);
        return String.format(strC, "1", this.f4707b, "1", "open", r.a(bArr));
    }

    @Override // com.loc.av
    public final byte[] e() {
        return this.f4706a;
    }
}