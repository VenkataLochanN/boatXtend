package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: ADDNumEncryptProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public class ib extends id {
    ib() {
    }

    public ib(id idVar) {
        super(idVar);
    }

    @Override // com.amap.api.mapcore.util.id
    protected byte[] a(byte[] bArr) {
        return gt.a(gt.a(bArr) + "||1");
    }
}