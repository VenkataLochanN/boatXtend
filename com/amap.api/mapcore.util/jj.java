package com.amap.api.mapcore.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: UpdateDataStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class jj {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    jj f1465c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    byte[] f1466d = null;

    protected abstract byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException;

    public void b(byte[] bArr) {
    }

    jj() {
    }

    jj(jj jjVar) {
        this.f1465c = jjVar;
    }

    public byte[] a() throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        byte[] bArrA = a(this.f1466d);
        jj jjVar = this.f1465c;
        if (jjVar == null) {
            return bArrA;
        }
        jjVar.c(bArrA);
        return this.f1465c.a();
    }

    void c(byte[] bArr) {
        this.f1466d = bArr;
    }
}