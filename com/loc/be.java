package com.loc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: ByteJoinDataStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class be extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ByteArrayOutputStream f4852a;

    public be() {
        this.f4852a = new ByteArrayOutputStream();
    }

    public be(bk bkVar) {
        super(bkVar);
        this.f4852a = new ByteArrayOutputStream();
    }

    @Override // com.loc.bk
    protected final byte[] a(byte[] bArr) {
        byte[] byteArray = this.f4852a.toByteArray();
        try {
            this.f4852a.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.f4852a = new ByteArrayOutputStream();
        return byteArray;
    }

    @Override // com.loc.bk
    public final void b(byte[] bArr) {
        try {
            this.f4852a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}