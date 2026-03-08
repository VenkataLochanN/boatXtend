package com.amap.api.mapcore.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: ByteJoinDataStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jd extends jj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ByteArrayOutputStream f1454a;

    public jd() {
        this.f1454a = new ByteArrayOutputStream();
    }

    public jd(jj jjVar) {
        super(jjVar);
        this.f1454a = new ByteArrayOutputStream();
    }

    @Override // com.amap.api.mapcore.util.jj
    protected byte[] a(byte[] bArr) {
        byte[] byteArray = this.f1454a.toByteArray();
        try {
            this.f1454a.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.f1454a = new ByteArrayOutputStream();
        return byteArray;
    }

    @Override // com.amap.api.mapcore.util.jj
    public void b(byte[] bArr) {
        try {
            this.f1454a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}