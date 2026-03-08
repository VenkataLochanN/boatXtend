package com.loc;

/* JADX INFO: compiled from: ADDNumEncryptProcessor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ai extends ak {
    ai() {
    }

    public ai(ak akVar) {
        super(akVar);
    }

    @Override // com.loc.ak
    protected final byte[] a(byte[] bArr) {
        return u.a(u.a(bArr) + "||1");
    }
}