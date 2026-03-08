package com.loc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: UpdateDataStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class bk {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    bk f4863c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    byte[] f4864d = null;

    bk() {
    }

    bk(bk bkVar) {
        this.f4863c = bkVar;
    }

    public final byte[] a() throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        bk bkVar = this;
        while (true) {
            byte[] bArrA = bkVar.a(bkVar.f4864d);
            bkVar = bkVar.f4863c;
            if (bkVar == null) {
                return bArrA;
            }
            bkVar.f4864d = bArrA;
        }
    }

    protected abstract byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException;

    public void b(byte[] bArr) {
    }
}