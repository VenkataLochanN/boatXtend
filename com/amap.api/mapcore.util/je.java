package com.amap.api.mapcore.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: EncryptRsaDataStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class je extends jj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private id f1455a;

    public je() {
        this.f1455a = new Cif();
    }

    public je(jj jjVar) {
        super(jjVar);
        this.f1455a = new Cif();
    }

    @Override // com.amap.api.mapcore.util.jj
    protected byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        return this.f1455a.b(bArr);
    }
}