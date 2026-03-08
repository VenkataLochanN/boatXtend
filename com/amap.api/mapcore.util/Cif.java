package com.amap.api.mapcore.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: renamed from: com.amap.api.mapcore.util.if, reason: invalid class name */
/* JADX INFO: compiled from: RSAAESEncryptProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public class Cif extends id {
    public Cif() {
    }

    public Cif(id idVar) {
        super(idVar);
    }

    @Override // com.amap.api.mapcore.util.id
    protected byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        return gn.a(bArr);
    }
}