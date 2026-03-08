package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ak extends j implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static byte[] f5620d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte f5621a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5622b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f5623c;

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
    }

    public ak() {
        this.f5621a = (byte) 0;
        this.f5622b = "";
        this.f5623c = null;
    }

    public ak(byte b2, String str, byte[] bArr) {
        this.f5621a = (byte) 0;
        this.f5622b = "";
        this.f5623c = null;
        this.f5621a = b2;
        this.f5622b = str;
        this.f5623c = bArr;
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5621a, 0);
        iVar.a(this.f5622b, 1);
        byte[] bArr = this.f5623c;
        if (bArr != null) {
            iVar.a(bArr, 2);
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5621a = hVar.a(this.f5621a, 0, true);
        this.f5622b = hVar.b(1, true);
        if (f5620d == null) {
            f5620d = new byte[]{0};
        }
        byte[] bArr = f5620d;
        this.f5623c = hVar.c(2, false);
    }
}